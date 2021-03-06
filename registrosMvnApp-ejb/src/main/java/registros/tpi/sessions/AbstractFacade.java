/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registros.tpi.sessions;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author manuel
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

     public boolean create(T entity) {
        boolean salida = false;
        T e = this.crear(entity);
            if(e != null) {
                salida = true;
            }
        return salida;
    }
    
    public T crear(T entity){
        T salida = null;
        try {
            EntityManager em = getEntityManager();
            if(em != null && entity != null) {
                em.persist(entity);
                salida = entity;
            }
        } catch(Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return salida;
    }

      
    
    public T editar(T entity) {
         T salida = null;
        try {
            EntityManager em = this.getEntityManager();
            if(em!=null && entity != null) {
                em.merge(entity);
                salida = entity;
            }
        }catch(Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return salida; 

       //getEntityManager().merge(entity);
    }
    
    public boolean edit(T entity) {
        boolean salida =false;
        T ed= this.editar(entity);
        
        if(ed!=null){
            salida = true;
        }
        return salida;
    }

   
    
    public boolean remove(T entity) {
        boolean salida = false;
//        getEntityManager().remove(getEntityManager().merge(entity));
        try {
            EntityManager em = this.getEntityManager();
            if(em != null && entity != null) {
                em.remove(em.merge(entity));
                salida=true;    
            }
        }catch(Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return salida;
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
         List salida = new ArrayList();
        try {
            EntityManager em = this.getEntityManager();
            if(em != null){
                javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                cq.select(cq.from(entityClass));
                salida = em.createQuery(cq).getResultList();
            }
        } catch(Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return salida;
       
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
