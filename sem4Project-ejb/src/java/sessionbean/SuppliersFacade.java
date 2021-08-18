/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Songs;
import entities.Suppliers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
public class SuppliersFacade extends AbstractFacade<Suppliers> implements SuppliersFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuppliersFacade() {
        super(Suppliers.class);
    }
    
    @Override
    public int countSongInSup(int typeID) {
        Query query = em.createQuery("SELECT i FROM Songs i WHERE i.supplierID = :supplierID");
        Suppliers sl = SuppliersFacade.this.find(typeID);
        query.setParameter("supplierID", sl);
        return query.getResultList().size();
    }
    
    @Override
    public List<Songs> getSongInSup(int typeID) {
        Query query = em.createQuery("SELECT i FROM Songs i WHERE i.supplierID = :supplierID");
        Suppliers sl = SuppliersFacade.this.find(typeID);
        query.setParameter("supplierID", sl);
        return query.getResultList();
    }
}
