/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.dao;

import com.vertu.vrts.data.EstObj;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author SidorovEI
 */
@Stateless
public class EstObjFacade extends AbstractFacade<EstObj> {

    @PersistenceContext(unitName = "vertuTSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstObjFacade() {
        super(EstObj.class);
    }
    
}
