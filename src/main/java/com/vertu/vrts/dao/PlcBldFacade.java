/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.dao;

import com.vertu.vrts.data.PlcBld;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author SidorovEI
 */
@Stateless
public class PlcBldFacade extends AbstractFacade<PlcBld> {

    @PersistenceContext(unitName = "vertuTSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlcBldFacade() {
        super(PlcBld.class);
    }
    
    //Поиск объекта в справочнике коэф. площадей
    public PlcBld findPlcBldByVal(BigDecimal plcVal) {
        try {
            Query q = em.createQuery("SELECT p from PlcBld as p WHERE :plv >= p.minVal AND :plv <= p.maxVal", PlcBld.class);
            q.setParameter("plv", plcVal);
            q.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
            List<PlcBld> plcBldRes = q.getResultList();
            if (plcBldRes.isEmpty()) {
                return null;
            } else {
                return plcBldRes.get(0);
            }
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
