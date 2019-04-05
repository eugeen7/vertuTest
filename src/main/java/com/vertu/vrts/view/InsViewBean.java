/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.view;

import com.vertu.vrts.cntrls.ClientCntrBean;
import com.vertu.vrts.cntrls.InsCntrBean;
import com.vertu.vrts.utils.JsfUtils;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
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
public class InsViewBean implements Serializable {

    public InsViewBean() {
    }

    //Отображение элементов на странице
    private Boolean insPnlVisible;
    private Boolean enInsVisible;
    private Boolean plcHldrVisible;
    private Boolean nePlcHldrVisible;

    //События на странице
    //Новый договор
    private final Integer INS_NEW = 1;
    //Открыть договор
    private final Integer INS_OPEN = 2;
    //Сохранить договор
    private final Integer INS_SAVE = 3;
    //К списку договоров
    private final Integer INS_LIST = 4;

    

    @Inject
    private JsfUtils jsfUtils;
    @Inject
    private InsCntrBean insCntrBean;
    @Inject
    ClientCntrBean clientCntrBean;

    public Boolean getInsPnlVisible() {
        return insPnlVisible;
    }

    public Boolean getEnInsVisible() {
        return enInsVisible;
    }

    public Boolean getPlcHldrVisible() {
        return plcHldrVisible;
    }

    public Boolean getNePlcHldrVisible() {
        return nePlcHldrVisible;
    }

//    public JsfUtils getJsfUtils() {
//        return jsfUtils;
//    }
    
    public InsCntrBean getInsCntrBean() {
        return insCntrBean;
    }

    public ClientCntrBean getClientCntrBean() {
        return clientCntrBean;
    }

    //
    //Создать договор страхования
    public void createInsConstr() {
        insPnlVisible = false;
        enInsVisible = true;
        insCntrBean.setInsContrState(INS_NEW);
    }

    //Редактировать договор страхования
    public void editInsConstr() {
        insPnlVisible = false;
        enInsVisible = true;
        insCntrBean.setInsContrState(INS_OPEN);
    }

    //Отменить изменения в договоре страхования (возврат к списку)
    public void cancelInsConstr() {
        insPnlVisible = true;
        enInsVisible = false;
        insCntrBean.setInsContrState(INS_LIST);
    }

    

    @PostConstruct
    private void init() {
        insPnlVisible = true;
        enInsVisible = false;
        plcHldrVisible = false;
        nePlcHldrVisible = false;
    }

}
