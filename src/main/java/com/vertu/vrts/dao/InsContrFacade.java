/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vertu.vrts.dao;

import com.vertu.vrts.data.InsContr;
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
public class InsContrFacade extends AbstractFacade<InsContr> {

    @PersistenceContext(unitName = "vertuTSPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InsContrFacade() {
        super(InsContr.class);
    }

    public Long findPlcBldByVal(Integer insNumber) {
        try {
            Query q = em.createQuery("SELECT COUNT(i.inscntrId) from InsContr as i WHERE i.insNum = :num");
            q.setParameter("num", insNumber);
//            q.setHint("javax.persistence.cache.retrieveMode", "BYPASS");
            return (Long) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
