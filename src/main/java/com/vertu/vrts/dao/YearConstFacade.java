/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.dao;

import com.vertu.vrts.data.YearConst;
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
public class YearConstFacade extends AbstractFacade<YearConst> {

    @PersistenceContext(unitName = "vertuTSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public YearConstFacade() {
        super(YearConst.class);
    }

    public YearConst findYearConstByVal(Integer yearVal) {
        try {
            Query q = em.createQuery("SELECT y from YearConst as y WHERE :plv >= y.minVal AND :plv <= y.maxVal", YearConst.class);
            q.setParameter("plv", yearVal);
            q.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
            List<YearConst> yearValRes = q.getResultList();
            if (yearValRes.isEmpty()) {
                return null;
            } else {
                return yearValRes.get(0);
            }
        } catch (NoResultException e) {
            return null;
        }
    }

}
