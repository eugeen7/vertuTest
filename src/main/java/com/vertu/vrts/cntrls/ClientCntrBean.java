/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.cntrls;

import com.vertu.vrts.dao.PlcHldrFacade;
import com.vertu.vrts.data.PlcHldr;
import com.vertu.vrts.utils.InsVldtBean;
import com.vertu.vrts.utils.JsfUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author SidorovEI
 */
@Named
@SessionScoped
public class ClientCntrBean implements Serializable {

    public ClientCntrBean() {
    }

    //Клиент
    //Список клиентов
    private List<PlcHldr> plcHldrLst;
    private List<PlcHldr> fltrPlcHldrLst;
    //Выбранный клиент
    private PlcHldr plcHldr;
    //Клиент 1 - новый, 2 - редактирование данных клиента
    private PlcHldr nePlcHldr;
    //Состояние Клиента: 0 - выбран, 1 - новый, 2 - редактирование
    private Integer plcHldrState;
    private final Integer NEW_PLC_HLDR = 1;
    private final Integer EDIT_PLC_HLDR = 2;

    private Integer neClients;

    @Inject
    private JsfUtils jsfUtils;
    @Inject
    private InsVldtBean vldtBean;

    @EJB
    private PlcHldrFacade plcHldrFacade;

    public List<PlcHldr> getPlcHldrLst() {
        if ((plcHldrLst == null) || (plcHldrLst.isEmpty())) {
            refreshePlcHldrLst();
        }
        return plcHldrLst;
    }

    public void setPlcHldrLst(List<PlcHldr> plcHldrLst) {
        this.plcHldrLst = plcHldrLst;
    }

    public List<PlcHldr> getFltrPlcHldrLst() {
        return fltrPlcHldrLst;
    }

    public void setFltrPlcHldrLst(List<PlcHldr> fltrPlcHldrLst) {
        this.fltrPlcHldrLst = fltrPlcHldrLst;
    }

    public PlcHldr getPlcHldr() {
        return plcHldr;
    }

    public void setPlcHldr(PlcHldr plcHldr) {
        this.plcHldr = plcHldr;
    }

    public PlcHldr getNePlcHldr() {
        return nePlcHldr;
    }

    public void setNePlcHldr(PlcHldr nePlcHldr) {
        this.nePlcHldr = nePlcHldr;
    }

    public Integer getPlcHldrState() {
        return plcHldrState;
    }

    public void setPlcHldrState(Integer plcHldrState) {
        this.plcHldrState = plcHldrState;
    }

    public InsVldtBean getVldtBean() {
        return vldtBean;
    }

    public Integer getNeClients() {
        return neClients;
    }

    public JsfUtils getJsfUtils() {
        return jsfUtils;
    }

    public Integer getNEW_PLC_HLDR() {
        return NEW_PLC_HLDR;
    }

    public Integer getEDIT_PLC_HLDR() {
        return EDIT_PLC_HLDR;
    }

    //
    public void newPlcHldr() {
        plcHldr = null;
        nePlcHldr = new PlcHldr();
        plcHldrState = NEW_PLC_HLDR;
    }

    public void editPlcHldr() {
        nePlcHldr = new PlcHldr();
        fillNePlcHldr();
        plcHldrState = EDIT_PLC_HLDR;
    }

    public void fillNePlcHldr() {
        if (chkPlcHldr()) {
            if (chkNePlcHldr()) {
                nePlcHldr.setBirthday(plcHldr.getBirthday());
                nePlcHldr.setFio(plcHldr.getFio());
                nePlcHldr.setFirstname(plcHldr.getFirstname());
                nePlcHldr.setPlchldrId(plcHldr.getPlchldrId());
                nePlcHldr.setPsprtNum(plcHldr.getPsprtNum());
                nePlcHldr.setPsprtSrl(plcHldr.getPsprtSrl());
                nePlcHldr.setSecondname(plcHldr.getSecondname());
                nePlcHldr.setSurename(plcHldr.getSurename());
            } else {
                jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Объект Страхователь не создан", "Объект Страхователь не создан"));
            }
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Страхователь не найден", "Страхователь не найден"));
        }
    }

    public void fillPlcHldr() {
        if (chkPlcHldr()) {
            if (chkNePlcHldr()) {
                plcHldr.setBirthday(nePlcHldr.getBirthday());
                plcHldr.setFio(nePlcHldr.getFio());
                plcHldr.setFirstname(nePlcHldr.getFirstname());
                //plcHldr.setPlchldrId(nePlcHldr.getPlchldrId());
                plcHldr.setPsprtNum(nePlcHldr.getPsprtNum());
                plcHldr.setPsprtSrl(nePlcHldr.getPsprtSrl());
                plcHldr.setSecondname(nePlcHldr.getSecondname());
                plcHldr.setSurename(nePlcHldr.getSurename());
            } else {
                jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Объект Страхователь не создан", "Объект Страхователь не создан"));
            }
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Страхователь не найден", "Страхователь не найден"));
        }
    }

    public void savePlcHldr() {
        if (vldtBean.vldtNePlcHldr(nePlcHldr, plcHldrState)) {
            if (plcHldrState.equals(NEW_PLC_HLDR)) {
                plcHldrFacade.create(nePlcHldr);
                refreshePlcHldrLst();
                plcHldr = plcHldrFacade.find(nePlcHldr.getPlchldrId());
                plcHldrState = EDIT_PLC_HLDR;
            } else if (plcHldrState.equals(EDIT_PLC_HLDR)) {
                plcHldr = plcHldrFacade.find(nePlcHldr.getPlchldrId());
                fillPlcHldr();
                plcHldrFacade.edit(plcHldr);
                refreshePlcHldrLst();
            }
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    vldtBean.getVldtPlcHldrResult(), vldtBean.getVldtPlcHldrResult()));
        }
    }

    public void refreshePlcHldrLst() {
        plcHldrLst = plcHldrFacade.findAll();
    }

    private boolean chkNePlcHldr() {
        return nePlcHldr != null;
    }

    private boolean chkPlcHldr() {
        return plcHldr != null;
    }

    //Открыть список клиентов (поиск, выбор)
    public void openClientsDlg() {
        Map<String, Object> options = new HashMap<>();
        //options.put("modal", true);
        options.put("width", 1024);
        options.put("height", 400);
        options.put("resizable", false);
        options.put("draggable", false);
        options.put("modal", true);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("closable", false);
        PrimeFaces.current().dialog().openDynamic("clients", options, null);
    }

    public void closeDlg() {
        PrimeFaces.current().dialog().closeDynamic(null);
    }

    public void editClientData() {
        //closeDlg();
        //openNeClientDlg();        
        neClients = 2;
    }

    public void openClientsDlgFrom() {
        //closeDlg();
        //openClientsDlg();
        neClients = 1;
    }

    public void newClientDlg() {
        newPlcHldr();
        editClientData();
        openClientsDlg();
    }

    public void createNewClientDlg() {
        newPlcHldr();
        editClientData();
        //openClientsDlg();
    }

    public void editClientDlg() {
        if (getPlcHldr() != null) {
            editPlcHldr();
            editClientData();
            openClientsDlg();
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Страхователь не выбран", "Страхователь не выбран"));
        }
    }

    public void editExistClientDlg() {
        if (getPlcHldr() != null) {
            editPlcHldr();
            editClientData();
            //openClientsDlg();
        } else {
            jsfUtils.getCurrFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Страхователь не выбран", "Страхователь не выбран"));
        }
    }

    public void findClientDlg() {
        plcHldrState = 0;
        openClientsDlgFrom();
        openClientsDlg();
    }

    @PostConstruct
    private void init() {
        neClients = 1;
    }

}
