/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Accounts;
import entities.Stories;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
public class StoriesFacade extends AbstractFacade<Stories> implements StoriesFacadeLocal {

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StoriesFacade() {
        super(Stories.class);
    }
    @Override
    public List<Stories> getList(int accountID) {
        Query query = em.createQuery("SELECT i FROM Stories i WHERE i.accountID = :accountID ORDER BY i.storyID DESC");
        Accounts account = accountsFacade.find(accountID);
        query.setParameter("accountID", account);
        return query.getResultList();
    }
}
