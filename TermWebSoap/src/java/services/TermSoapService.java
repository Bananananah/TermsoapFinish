/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Todaycase;

/**
 *
 * @author nickk
 */
@WebService(serviceName = "TermSoapService")
public class TermSoapService {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TermWebSoapPU");

    /**
     * Web service operation
     * @param yearnum
     * @return 
     */
    @WebMethod(operationName = "findByYear")
    public Todaycase findByYear(@WebParam(name = "yearnum") int yearnum) {
        EntityManager em = emf.createEntityManager();
        Todaycase ye = em.find(Todaycase.class, yearnum);
        return ye;
    }

    private void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
