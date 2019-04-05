/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.utils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author SidorovEI
 */
@Named
@SessionScoped
public class JsfUtils implements Serializable {

    public JsfUtils() {
    }
    
    
    private TimeZone curTz;
    private Calendar now;
    private Date currDate;
    private Date nextDayDate;
    
    
    
    public FacesContext getCurrFacesContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context;
    }

    public HttpServletRequest httpServletRequest() {
        HttpServletRequest httprequest = (HttpServletRequest) getCurrFacesContext().getExternalContext().getRequest();
        return httprequest;
    }

    public TimeZone getCurTz() {
        curTz = now.getTimeZone();
        return curTz;
    }

    public Date getCurrDate() {
        return new Date();
    }

    public Date getNextDayDate() {
        return new Date(getCurrDate().getTime() + 1000 * 3600 * 24);
    }


    @PostConstruct
    private void init() {
        now = Calendar.getInstance();
    }

}
