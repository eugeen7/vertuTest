/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.cntrls;

import com.vertu.vrts.dao.EstObjFacade;
import com.vertu.vrts.dao.InsContrFacade;
import com.vertu.vrts.dao.PlcBldFacade;
import com.vertu.vrts.dao.PrptTypeFacade;
import com.vertu.vrts.dao.YearConstFacade;
import com.vertu.vrts.data.EstObj;
import com.vertu.vrts.data.InsContr;
import com.vertu.vrts.data.PlcBld;
import com.vertu.vrts.data.PrptType;
import com.vertu.vrts.data.YearConst;
import com.vertu.vrts.utils.InsVldtBean;
import com.vertu.vrts.utils.JsfUtils;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author SidorovEI
 */
//Работа с договорами
@Named
@SessionScoped
public class InsCntrBean implements Serializable {

    public InsCntrBean() {
    }

    //Договора
    //Спписок договоров
    private List<InsContr> insContrLst;
    //Выбранный из списка договор
    private InsContr insContr;
    //Договор: новый 1, редактирование 2
    private InsContr neInsContr;
    //Фильтрация списка договоров    
    private List<InsContr> fltrInsContrLst;
    //Состояние договора: 1 - новый, 2 - редактирование, 3 - сохранённый
    private Integer insContrState;
    private final Integer INS_IS_NEW = 1;
    private final Integer INS_IS_OPEN = 2;
    private final Integer INS_IS_SAVED = 3;
    private final String INS_VLDT_PASS = "INS_CONTR_VLDT_PASS";
    private final String EST_OBJ_PASS = "EST_OBJ_VLDT_PASS";

    //Недвижимость
    //Список объектов недвижимости
    private List<EstObj> estObjLst;
    //Текущий (выбранный) объект недвижимости
    private EstObj currEstObj;
    //Объект недвижимости: новый 1, редактирование 2
    private EstObj neEstObj;
    //Состояние объекта недвижимости: 0 - выбран, 1 - новый, 2 - редактирование
    //private int estObjState;

    //Справочники
    //Справочник Коэффициент за площадь
    private List<PlcBld> plcBldLst;
    private PlcBld plcBld;
    private Long plcBldIdx;
    //Справочник Коэффициент за тип недвижимости
    private List<PrptType> prptTypeLst;
    private PrptType prptType;
    private Long prptTypeIdx;
    //Справочник Коэффициент за год постройки
    private List<YearConst> yearConstLst;
    private YearConst yearConst;
    private Long yearConstIdx;

    @Inject
    private JsfUtils jsfUtils;
    @Inject
    private InsVldtBean insVldtBean;
    @Inject
    private ClientCntrBean clientCntrBean;

    @EJB
    private InsContrFacade insContrFacade;

    @EJB
    private EstObjFacade estObjFacade;

    @EJB
    private PlcBldFacade plcBldFacade;

    @EJB
    private PrptTypeFacade prptTypeFacade;

    @EJB
    private YearConstFacade yearConstFacade;

    public List<InsContr> getInsContrLst() {
        if ((insContrLst == null) || (insContrLst.isEmpty())) {
            refresheInsConstrLst();
        }
        return insContrLst;
    }

    public void setInsContrLst(List<InsContr> insContrLst) {
        this.insContrLst = insContrLst;
    }

    public InsContr getInsContr() {
        return insContr;
    }

    public void setInsContr(InsContr insContr) {
        this.insContr = insContr;
    }

    public InsContr getNeInsContr() {
        return neInsContr;
    }

    public void setNewInsContr(InsContr neInsContr) {
        this.neInsContr = neInsContr;
    }

    public List<InsContr> getFltrInsContrLst() {
        return fltrInsContrLst;
    }

    public void setFltrInsContrLst(List<InsContr> fltrInsContrLst) {
        this.fltrInsContrLst = fltrInsContrLst;
    }

    public List<EstObj> getEstObjLst() {
        return estObjLst;
    }

    public void setEstObjLst(List<EstObj> estObjLst) {
        this.estObjLst = estObjLst;
    }

    public EstObj getCurrEstObj() {
        return currEstObj;
    }

    public void setCurrEstObj(EstObj currEstObj) {
        this.currEstObj = currEstObj;
    }

    public EstObj getNeEstObj() {
        return neEstObj;
    }

    public void setNeEstObj(EstObj neEstObj) {
        this.neEstObj = neEstObj;
    }

    public List<PlcBld> getPlcBldLst() {
        if ((plcBldLst == null) || (plcBldLst.isEmpty())) {
            refreshePlcBldLst();
        }
        return plcBldLst;
    }

    public void setPlcBldLst(List<PlcBld> plcBldLst) {
        this.plcBldLst = plcBldLst;
    }

    public PlcBld getPlcBld() {
        return plcBld;
    }

    public void setPlcBld(PlcBld plcBld) {
        this.plcBld = plcBld;
    }

    public List<PrptType> getPrptTypeLst() {
        if ((prptTypeLst == null) || (prptTypeLst.isEmpty())) {
            refreshePrptTypeLst();
        }
        return prptTypeLst;
    }

    public void setPrptTypeLst(List<PrptType> prptTypeLst) {
        this.prptTypeLst = prptTypeLst;
    }

    public PrptType getPrptType() {
        return prptType;
    }

    public void setPrptType(PrptType prptType) {
        this.prptType = prptType;
    }

    public Long getPrptTypeIdx() {
        return prptTypeIdx;
    }

    public void setPrptTypeIdx(Long prptTypeIdx) {
        this.prptTypeIdx = prptTypeIdx;
        prptType = null;
        if (this.prptTypeIdx != null) {
            for (PrptType pt : prptTypeLst) {
                if (pt.getPrptpId().equals(this.prptTypeIdx)) {
                    prptType = pt;
                    return;
                }
            }
        }
    }

    public List<YearConst> getYearConstLst() {
        if ((yearConstLst == null) || (yearConstLst.isEmpty())) {
            refresheYearConstLst();
        }
        return yearConstLst;
    }

    public void setYearConstLst(List<YearConst> yearConstLst) {
        this.yearConstLst = yearConstLst;
    }

    public YearConst getYearConst() {
        return yearConst;
    }

    public void setYearConst(YearConst yearConst) {
        this.yearConst = yearConst;
    }

    public Long getYearConstIdx() {
        return yearConstIdx;
    }

    public void setYearConstIdx(Long yearConstIdx) {
        this.yearConstIdx = yearConstIdx;
        yearConst = null;
        if (this.yearConstIdx != null) {
            for (YearConst yc : getYearConstLst()) {
                if (yc.getYcnstId().equals(this.yearConstIdx)) {
                    yearConst = yc;
                    return;
                }
            }
        }
    }

    public Long getPlcBldIdx() {
        return plcBldIdx;
    }

    public void setPlcBldIdx(Long plcBldIdx) {
        this.plcBldIdx = plcBldIdx;
        plcBld = null;
        if (this.plcBldIdx != null) {
            for (PlcBld pb : getPlcBldLst()) {
                if (pb.getPlcbldId().equals(this.plcBldIdx)) {
                    plcBld = pb;
                    return;
                }
            }
        }
    }

    public Integer getInsContrState() {
        return insContrState;
    }

    public void setInsContrState(Integer insContrState) {
        this.insContrState = insContrState;
        asgnInsState();
    }

    public JsfUtils getJsfUtils() {
        return jsfUtils;
    }

    public InsVldtBean getInsVldtBean() {
        return insVldtBean;
    }

    //Обновить список
    public void refresheInsConstrLst() {
        insContrLst = insContrFacade.findAll();
    }

    //Подготовка договора (новый, редактирование)
    private void asgnInsState() {
        if ((this.insContrState.equals(INS_IS_NEW))) {
            neInsContr = new InsContr();
            neInsContr.setCntrDate(jsfUtils.getCurrDate());
            neEstObj = new EstObj();
            insContr = null;
            currEstObj = null;
            clientCntrBean.setPlcHldr(null);
            //Новый страхователь ПРОВЕРИТЬ
            //clientCntrBean.newPlcHldr();
            //Обновление справочников
            refreshePlcBldLst();
            refreshePrptTypeLst();
            refresheYearConstLst();
            setPlcBldIdx(null);
            //plcBld = null;
            setYearConstIdx(null);
            //yearConst = null;
            setPrptTypeIdx(null);
        } else if ((this.insContrState.equals(INS_IS_OPEN))) {
            if (insContr != null) {
                neInsContr = new InsContr();
                fillNeInsContr();
                neEstObj = new EstObj();
                fillNeEstObj();
                //clientCntrBean.newPlcHldr();
                fillnePlcHldr();
//                clientCntrBean.setPlcHldr(insContr.getPlchldrFk());
//                clientCntrBean.fillNePlcHldr(insContr.getPlchldrFk());
            } else {
                jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Договор для редактирования не найден", "Договор для редактирования не найден"));
            }
        }
    }

    private void fillNeInsContr() {
        neInsContr.setCntrCmnt(insContr.getCntrCmnt());
        neInsContr.setCntrDate(insContr.getCntrDate());
        neInsContr.setCntrDateCalc(insContr.getCntrDateCalc());
        neInsContr.setCntrDateEnd(insContr.getCntrDateEnd());
        neInsContr.setCntrDateStrt(insContr.getCntrDateStrt());
        neInsContr.setEstobjFk(insContr.getEstobjFk());
        neInsContr.setInsBonus(insContr.getInsBonus());
        neInsContr.setInsNum(insContr.getInsNum());
        neInsContr.setInsSrNum(insContr.getInsSrNum());
        neInsContr.setInsSum(insContr.getInsSum());
        neInsContr.setInscntrId(insContr.getInscntrId());
        neInsContr.setPlcbldFk(insContr.getPlcbldFk());
        neInsContr.setPlcbldVal(insContr.getPlcbldVal());
        neInsContr.setPlchldrFk(insContr.getPlchldrFk());
        neInsContr.setPrptpFk(insContr.getPrptpFk());
        //neInsContr.setVersnum(insContr.getVersnum());
        neInsContr.setYcnstFk(insContr.getYcnstFk());
        neInsContr.setYcnstVal(insContr.getYcnstVal());
        //setPrptTypeIdx(insContr.getPrptpFk().getPrptpId());

        setPlcBldIdx(insContr.getPlcbldFk().getPlcbldId());
        setPrptTypeIdx(insContr.getPrptpFk().getPrptpId());
        //setYearConst(insContr.getYcnstFk());
        setYearConstIdx(insContr.getYcnstFk().getYcnstId());
    }

    private void fillNeEstObj() {
        if (insContr.getEstobjFk() != null) {
            setCurrEstObj(insContr.getEstobjFk());
            neEstObj.setAprtNum(currEstObj.getAprtNum());
            neEstObj.setBuildNum(currEstObj.getBuildNum());
            neEstObj.setBuilding(currEstObj.getBuilding());
            neEstObj.setCorpus(currEstObj.getCorpus());
            neEstObj.setCountry(currEstObj.getCountry());
            neEstObj.setDistrict(currEstObj.getDistrict());
            neEstObj.setEstobjId(currEstObj.getEstobjId());
            neEstObj.setLocPoint(currEstObj.getLocPoint());
            neEstObj.setOblast(currEstObj.getOblast());
            neEstObj.setStreet(currEstObj.getStreet());
            neEstObj.setZipCode(currEstObj.getZipCode());
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Объект недвижимости не найден", "Объект недвижимости не найден"));
        }
    }

    private void fillnePlcHldr() {
        if (insContr.getPlchldrFk() != null) {
            clientCntrBean.setPlcHldr(insContr.getPlchldrFk());
            //clientCntrBean.fillNePlcHldr(insContr.getPlchldrFk());
//        } else {
//            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "Страхователь не найден", "Страхователь не найден"));
        }
    }

    //Заполнение договора при сохранении
    private void filInsContr() {
        insContr.setCntrCmnt(neInsContr.getCntrCmnt());
        insContr.setCntrDate(neInsContr.getCntrDate());
        insContr.setCntrDateCalc(neInsContr.getCntrDateCalc());
        insContr.setCntrDateEnd(neInsContr.getCntrDateEnd());
        insContr.setCntrDateStrt(neInsContr.getCntrDateStrt());
        insContr.setEstobjFk(currEstObj);
        insContr.setInsBonus(neInsContr.getInsBonus());
        insContr.setInsNum(neInsContr.getInsNum());
        insContr.setInsSrNum(neInsContr.getInsSrNum());
        insContr.setInsSum(neInsContr.getInsSum());
//        if (neInsContr.getInscntrId() != null) {
//            insContr.setInscntrId(neInsContr.getInscntrId());
//        }
        insContr.setPlcbldFk(plcBld);
        insContr.setPlcbldVal(neInsContr.getPlcbldVal());
        insContr.setPlchldrFk(clientCntrBean.getPlcHldr());
        insContr.setPrptpFk(prptType);
//insContr.setVersnum(neInsContr.getVersnum()); //Не нужно
        insContr.setYcnstFk(yearConst);
        insContr.setYcnstVal(neInsContr.getYcnstVal());
    }

//    private boolean chkFillNeEstObjTmp() {
//        //boolean filllVldtNeEstObj = false;
//        if (neEstObj != null) {
//            String vldtResult = insVldtBean.vldtNeEstObjTmp(neEstObj);
//            if (vldtResult.equals(EST_OBJ_PASS)) {
//                return fillEstObj();
//            } else {
//                jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//                        vldtResult, vldtResult));
//                return false;
//            }
//        } else {
//            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "Объект недвижимости не найден", "Объект недвижимости не найден"));
//            return false;
//        }
//        //return filllVldtNeEstObj;
//    }
    private boolean chkDefPlcBld() {
//        if ((neInsContr.getPlcbldVal() != null) && (neInsContr.getPlcbldVal().doubleValue() > 0.0D)) {
//            plcBld = plcBldFacade.findPlcBldByVal(neInsContr.getPlcbldVal());
        if (plcBld != null) {
            return true;
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Соответствующая значению запись в справочнике Площадь объекта не найдена",
                    "Соответствующая значению запись в справочнике Площадь объекта не найдена"));
            return false;
        }
//        } else {
//            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "Площадь объекта не указана, или указана не верно", "Площадь объекта не указана, или указана не верно"));
//            return false;
//        }
    }

    private boolean chkDefPrptp() {
        if (prptType == null) {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Тип объекта не найден: не указан, или указан не верно",
                    "Тип объекта не найден: не указан, или указан не верно"));
            return false;
        } else {
            return true;
        }
    }

    private boolean chkDefYcnst() {
//        if ((neInsContr.getYcnstVal() != null) && (neInsContr.getYcnstVal() > 0)) {
//            yearConst = yearConstFacade.findYearConstByVal(neInsContr.getYcnstVal());
        if (yearConst != null) {
            return true;
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Соответствующая значению запись в справочнике Год постройки объекта не найдена",
                    "Соответствующая значению запись в справочнике Год постройки объекта не найдена"));
            return false;
        }
//        } else {
//            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "Год постройки объекта не указана, или указана не верно", "Год постройки объекта не указана, или указана не верно"));
//            return false;
//        }
    }

    private boolean chkDefPlchldr() {
        return clientCntrBean.getPlcHldr() != null;
    }

    private boolean fillEstObj() {
        if (currEstObj != null) {
            currEstObj.setAprtNum(neEstObj.getAprtNum());
            currEstObj.setBuildNum(neEstObj.getBuildNum());
            currEstObj.setBuilding(neEstObj.getBuilding());
            currEstObj.setCorpus(neEstObj.getCorpus());
            currEstObj.setCountry(neEstObj.getCountry());
            currEstObj.setDistrict(neEstObj.getDistrict());
//            if (neEstObj.getEstobjId() != null) {
//                currEstObj.setEstobjId(neEstObj.getEstobjId());
//            }
            currEstObj.setLocPoint(neEstObj.getLocPoint());
            currEstObj.setOblast(neEstObj.getOblast());
            currEstObj.setStreet(neEstObj.getStreet());
            //currEstObj.setVersnum(neEstObj.getVersnum());
            currEstObj.setZipCode(neEstObj.getZipCode());
            return true;
        } else {
            return false;
        }
    }

    public void refreshePlcBldLst() {
        plcBldLst = plcBldFacade.findAll();
    }

    public void refreshePrptTypeLst() {
        prptTypeLst = prptTypeFacade.findAll();
    }

    public void refresheYearConstLst() {
        yearConstLst = yearConstFacade.findAll();
    }

    //Сохраненить договор страхования
    public void saveInsConstr() {
        if (insVldtBean.vldNeInsContr(neInsContr, insContrState)) {
            if (insVldtBean.vldtNeEstObj(neEstObj)) {
                if (chkDefPlchldr()) {
                    if (chkInsContrSpr()) {
                        if ((this.insContrState.equals(INS_IS_NEW))) {
                            estObjFacade.create(neEstObj);
                            neEstObj = estObjFacade.find(neEstObj.getEstobjId());
                            //Проверить neEstObj
                            neInsContr.setEstobjFk(neEstObj);
                            neInsContr.setPlcbldFk(plcBld);
                            neInsContr.setPlchldrFk(clientCntrBean.getPlcHldr());
                            neInsContr.setPrptpFk(prptType);
                            neInsContr.setYcnstFk(yearConst);
                            insContrFacade.create(neInsContr);
                            insContr = neInsContr;
                            insContrLst = insContrFacade.findAll();
                            insContrState = INS_IS_OPEN;
                            refresheInsConstrLst();
                        } else if ((this.insContrState.equals(INS_IS_OPEN))) {
                            currEstObj = estObjFacade.find(neEstObj.getEstobjId());
                            if (fillEstObj()) {
                                estObjFacade.edit(currEstObj);
                                insContr = insContrFacade.find(neInsContr.getInscntrId());
                                if (insContr != null) {
                                    filInsContr();
                                    insContrFacade.edit(insContr);
                                    refresheInsConstrLst();
                                } else {
                                    jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                                            "Ошибка сохранения договора: сохраняемый объект не найден в БД", "Ошибка сохранения договора: сохраняемый объект не найден в БД"));
                                }
                            }
                        }
                    }
                } else {
                    jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Страхователь не указан", "Страхователь не указан"));
                }
            } else {
                jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        insVldtBean.getVldtEstObjResult(), insVldtBean.getVldtEstObjResult()));
            }
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    insVldtBean.getVldInsContrResult(), insVldtBean.getVldInsContrResult()));
        }
        insContrState = 2;
    }

    private boolean chkInsContrSpr() {
        return (chkDefPlcBld() && chkDefPrptp() && chkDefYcnst());
    }

    public void calcBonus() {        
        if (insVldtBean.vldtCalcBonus(neInsContr, insContrState)) {
            if (chkInsContrSpr()) {
                BigDecimal calcBonusResult;
                BigDecimal days;
                days = new BigDecimal(Math.round(((double) (neInsContr.getCntrDateEnd().getTime() - neInsContr.getCntrDateStrt().getTime()) / 1000 / 3600 / 24)));
                calcBonusResult = (neInsContr.getInsSum().divide(days, 2, RoundingMode.HALF_UP)).multiply(prptType.getPrpKf()).multiply(yearConst.getYcntKf()).multiply(plcBld.getPlcKf());
                if ((calcBonusResult != null) && (calcBonusResult.compareTo( BigDecimal.ZERO) > 0)) {
                    neInsContr.setInsBonus(calcBonusResult.setScale(2, BigDecimal.ROUND_HALF_UP));
                    neInsContr.setCntrDateCalc(new Date());
                }
            }
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    insVldtBean.getVldtCalcInsContr(), insVldtBean.getVldtCalcInsContr()));
        }
    }

}
