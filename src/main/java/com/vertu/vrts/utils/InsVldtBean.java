/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.utils;

import com.vertu.vrts.dao.InsContrFacade;
import com.vertu.vrts.data.EstObj;
import com.vertu.vrts.data.InsContr;
import com.vertu.vrts.data.PlcHldr;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author SidorovEI
 */
@Named
@SessionScoped
public class InsVldtBean implements Serializable {

    public InsVldtBean() {
    }

    Calendar f;
    Calendar v;

    private final Integer INS_NEW = 1;
    //Открыть договор
    private final Integer INS_OPEN = 2;
    private final Integer NEW_PLC_HLDR = 1;
    private final Integer EDIT_PLC_HLDR = 2;

    private String vldInsContrResult;
    private String vldtEstObjResult;
    private String vldtPlcHldrResult;
    private String vldtCalcInsContr;

    @EJB
    private InsContrFacade contrFacade;

    public String getVldInsContrResult() {
        return vldInsContrResult;
    }

    public void setVldInsContrResult(String vldInsContrResult) {
        if ((this.vldInsContrResult != null) && ((!"".equals(this.vldInsContrResult)) && (!"INS_CONTR_VLDT_PASS".equals(this.vldInsContrResult)))) {
            this.vldInsContrResult = this.vldInsContrResult + "; " + vldInsContrResult;
        } else {
            this.vldInsContrResult = vldInsContrResult;
        }
    }

    public String getVldtEstObjResult() {
        return vldtEstObjResult;
    }

    public void setVldtEstObjResult(String vldtEstObjResult) {
        if ((this.vldtEstObjResult != null) && ((!"".equals(this.vldtEstObjResult)) && (!"EST_OBJ_VLDT_PASS".equals(this.vldtEstObjResult)))) {
            this.vldtEstObjResult = this.vldtEstObjResult + "; " + vldtEstObjResult;
        } else {
            this.vldtEstObjResult = vldtEstObjResult;
        }
    }

    public String getVldtPlcHldrResult() {
        return vldtPlcHldrResult;
    }

    public void setVldtPlcHldrResult(String vldtPlcHldrResult) {
        if ((this.vldtPlcHldrResult != null) && ((!"".equals(this.vldtPlcHldrResult)) && (!"PLC_HLDR_VLDT_PASS".equals(this.vldtPlcHldrResult)))) {
            this.vldtPlcHldrResult = this.vldtPlcHldrResult + "; " + vldtPlcHldrResult;
        } else {
            this.vldtPlcHldrResult = vldtPlcHldrResult;
        }
    }

    public String getVldtCalcInsContr() {
        return vldtCalcInsContr;
    }

    public void setVldtCalcInsContr(String vldtCalcInsContr) {
        if ((this.vldtCalcInsContr != null) && ((!"".equals(this.vldtCalcInsContr)) && (!"CALC_INS_CONTR_PASS".equals(this.vldtCalcInsContr)))) {
            this.vldtCalcInsContr = this.vldtCalcInsContr + "; " + vldtCalcInsContr;
        } else {
            this.vldtCalcInsContr = vldtCalcInsContr;
        }
    }

    public boolean filterByDate(Object value, Object filter, Locale locale) {
        if (filter == null) {
            return true;
        }

        if (value == null) {
            return false;
        }

//        f.setTime((Date) filter);
//        v.setTime((Date) value);
//        boolean sameDay = v.get(Calendar.YEAR) == f.get(Calendar.YEAR)
//                && v.get(Calendar.DAY_OF_YEAR) == f.get(Calendar.DAY_OF_YEAR);
//        return sameDay;
        return cmpTwoDates(((Date) filter), ((Date) value));
    }

    private boolean cmpTwoDates(Date fakt, Date val) {
        f.setTime((Date) fakt);
        v.setTime((Date) val);
        return v.get(Calendar.YEAR) == f.get(Calendar.YEAR)
                && v.get(Calendar.MONTH) == f.get(Calendar.MONTH)
                && v.get(Calendar.DAY_OF_YEAR) == f.get(Calendar.DAY_OF_YEAR);
    }

    public boolean chkInsDate(Date val) {
        f.setTime(new Date());
        v.setTime((Date) val);
        return v.get(Calendar.YEAR) >= f.get(Calendar.YEAR)
                && v.get(Calendar.MONTH) >= f.get(Calendar.MONTH)
                && v.get(Calendar.DAY_OF_YEAR) >= f.get(Calendar.DAY_OF_YEAR);
    }

    private boolean chkInsDateEnd(Date strtIsn, Date endIns) {
        if (strtIsn == null) {
            return false;
        } else {
            if (chkInsDate(strtIsn)) {
                f.setTime((Date) strtIsn);
                v.setTime((Date) endIns);
                return v.get(Calendar.YEAR) >= f.get(Calendar.YEAR)
                        && v.get(Calendar.MONTH) >= f.get(Calendar.MONTH)
                        && v.get(Calendar.DAY_OF_YEAR) > f.get(Calendar.DAY_OF_YEAR);
            } else {
                return false;
            }
        }
    }

    public Boolean vldNeInsContr(InsContr contr, Integer neStatus) {
        vldInsContrResult = "";
        setVldInsContrResult("INS_CONTR_VLDT_PASS");
        if (contr == null) {
            setVldInsContrResult("Дговор не создан");
        } else {
            if (contr.getCntrDate() == null) {
                setVldInsContrResult("Неуказана дата договора");
            }
            if (contr.getCntrDateCalc() == null) {
                setVldInsContrResult("Не выполнен расчёт договора");
            }
            if (contr.getCntrDateEnd() == null) {
                setVldInsContrResult("Не вуказана дата окончания договора");
            } else {
                if (!chkInsDateEnd(contr.getCntrDateStrt(), contr.getCntrDateEnd())) {
                    setVldInsContrResult("Дата окончания договора должна быть больше даты начала договора");
                }
            }
            if (contr.getCntrDateStrt() == null) {
                setVldInsContrResult("Не указана дата начала договора");
            } else {
                if (neStatus.equals(INS_NEW)) {
                    if (!chkInsDate(contr.getCntrDateStrt())) {
                        setVldInsContrResult("Не верно указана дата начала договора. Дата начала договора должна быть не меньше текущей");
                    }
                }
            }
//            if (contr.getEstobjFk() == null) {
//                setVldInsContrResult("Не указан объект недвижимости договора");
//            }
            if ((contr.getInsBonus() == null) || (contr.getInsBonus().equals(BigDecimal.ZERO))) {
                setVldInsContrResult("Не выполнен расчёт договора");
            }
            if (contr.getInsNum() == null) {
                setVldInsContrResult("Не заполнен номер договора");
            } else {
                Long insNumExist = 0L;
                if (neStatus.equals(INS_NEW)) {
                    insNumExist = contrFacade.findPlcBldByVal(contr.getInsNum());
                }
                if ((insNumExist != null) && (insNumExist > 0)) {
                    setVldInsContrResult("Номер договора " + contr.getInsNum() + " уже существует");
                } else {
                    if ((contr.getInsNum() < 100000) || (contr.getInsNum() > 999999)) {
                        setVldInsContrResult("Номер договора должен быть в пределах: от 100000 до 999999");
                    } else {
                        if ((contr.getInsNum() > 0) && (contr.getInsNum() < 10)) {
                            contr.setInsSrNum("00000-" + contr.getInsNum());
                        }
                        if ((contr.getInsNum() >= 10) && (contr.getInsNum() < 100)) {
                            contr.setInsSrNum("0000-" + contr.getInsNum());
                        }
                        if ((contr.getInsNum() >= 100) && (contr.getInsNum() < 1000)) {
                            contr.setInsSrNum("000-" + contr.getInsNum());
                        }
                        if ((contr.getInsNum() >= 1000) && (contr.getInsNum() < 10000)) {
                            contr.setInsSrNum("00-" + contr.getInsNum());
                        }
                        if ((contr.getInsNum() >= 10000) && (contr.getInsNum() < 100000)) {
                            contr.setInsSrNum("0-" + contr.getInsNum());
                        }
                        if ((contr.getInsNum() >= 100000) && (contr.getInsNum() < 1000000)) {
                            contr.setInsSrNum(contr.getInsNum().toString());
                        }
                    }
                }
            }

            if ((contr.getInsSum() == null) || (contr.getInsSum().equals(BigDecimal.ZERO))) {
                setVldInsContrResult("Не заполнен сумма договора");
            }
//            if (contr.getPlcbldFk() == null) {
//                setVldInsContrResult("Не заполнена площадь помещения в договоре");
//            }
//            if ((contr.getPlcbldVal() == null) || (contr.getPlcbldVal().equals(BigDecimal.ZERO))) {
//                setVldInsContrResult("Не заполнена площадь помещения");
//            }
//            if (contr.getPlcbldFk() == null) {
//                setVldInsContrResult("Не заполнена площадь помещения");
//            }
//            if (contr.getPlchldrFk() == null) {
//                setVldInsContrResult("Не указан страхователь в договоре");
//            }
//            if (contr.getPrptpFk() == null) {
//                setVldInsContrResult("Не указан тип недвижимости в договоре");
//            }
//            if (contr.getYcnstFk() == null) {
//                setVldInsContrResult("Не указан год постройки в договоре");
//            }
//            if ((contr.getYcnstVal() == null) || (contr.getYcnstVal() == 0)) {
//                setVldInsContrResult("Не указан год постройки");
//            } else {
//                if ((contr.getYcnstVal() < 1000) || (contr.getYcnstVal() > 9999)) {
//                    setVldInsContrResult("Год постройки должен быть в пределах: от 1000 до 9999");
//                }
//            }
        }
        return vldInsContrResult.equals("INS_CONTR_VLDT_PASS");
    }

    public Boolean vldtNeEstObj(EstObj estObjTmp) {
        vldtEstObjResult = "";
        setVldtEstObjResult("EST_OBJ_VLDT_PASS");
        if (estObjTmp == null) {
            setVldtEstObjResult("Объект недвижимости не создан");
        } else {
            if ((estObjTmp.getAprtNum() == null) || ("".equals(estObjTmp.getAprtNum()))) {
                setVldtEstObjResult("Квартира обязательна");
            }
            if (estObjTmp.getBuildNum() == null) {
                setVldtEstObjResult("№ Дома обязателен");
            }
            if (estObjTmp.getCountry() == null) {
                setVldtEstObjResult("Страна обязательна");
            }
            if (estObjTmp.getLocPoint() == null) {
                setVldtEstObjResult("Населённый пункт обязателен");
            }
            if (estObjTmp.getOblast() == null) {
                setVldtEstObjResult("Область, край, или республика, должны быть указаны");
            }
            if (estObjTmp.getStreet() == null) {
                setVldtEstObjResult("Улица обязательна");
            }
        }
        return getVldtEstObjResult().equals("EST_OBJ_VLDT_PASS");
    }

    public Boolean vldtNePlcHldr(PlcHldr plcHldr, Integer plcHldrState) {
        vldtPlcHldrResult = "";
        setVldtPlcHldrResult("PLC_HLDR_VLDT_PASS");
        if (plcHldr == null) {
            setVldtPlcHldrResult("Страхователь объекта недвижимости не создан");
        } else {
            if (plcHldr.getBirthday() == null) {
                setVldtPlcHldrResult("Дата дня рождения обязательна");
            }
            if (plcHldr.getFirstname() == null) {
                setVldtPlcHldrResult("Имя страхователя обязательно");
            }
            if (plcHldrState.equals(EDIT_PLC_HLDR)) {
                if (plcHldr.getPlchldrId() == null) {
                    setVldtPlcHldrResult("Ошибка идентификатора страхователя");
                }
            }
            if (plcHldr.getPsprtNum() == null) {
                setVldtPlcHldrResult("№ паспорта страхователя обязателен");
            }
            if (plcHldr.getPsprtSrl() == null) {
                setVldtPlcHldrResult("Серия паспорта страхователя обязательна");
            }
            if (plcHldr.getSecondname() == null) {
                setVldtPlcHldrResult("Отчество страхователя обязательно");
            }
            if (plcHldr.getSurename() == null) {
                setVldtPlcHldrResult("Фамилия страхователя обязательна");
            }
        }
        return getVldtPlcHldrResult().equals("PLC_HLDR_VLDT_PASS");
    }

    public Boolean vldtCalcBonus(InsContr contr, Integer calcMode) {
        vldtCalcInsContr = "";
        setVldtCalcInsContr("CALC_INS_CONTR_PASS");
        if (contr == null) {
            setVldtCalcInsContr("Объект договора не создан");
        } else {
            if ((contr.getInsSum() == null) || (contr.getInsSum() == BigDecimal.ZERO)) {
                setVldtCalcInsContr("Сумма страхования должна быть больше нуля");
            }
            if (contr.getCntrDateStrt() == null) {
                setVldtCalcInsContr("Не указана дата начала договора");
            } else {
                if (calcMode.equals(INS_NEW)) {
                    if (!chkInsDate(contr.getCntrDateStrt())) {
                        setVldInsContrResult("Не верно указана дата начала договора. Дата начала договора должна быть не меньше текущей");
                    }
                }
            }
            if (contr.getCntrDateEnd() == null) {
                setVldtCalcInsContr("Не вуказана дата окончания договора");
            } else {
                if (!chkInsDateEnd(contr.getCntrDateStrt(), contr.getCntrDateEnd())) {
                    setVldtCalcInsContr("Дата окончания договора должна быть больше даты начала договора");
                }
            }
        }
        return getVldtCalcInsContr().equals("CALC_INS_CONTR_PASS");
    }

    @PostConstruct
    private void init() {
        f = Calendar.getInstance();
        v = Calendar.getInstance();
    }

}
