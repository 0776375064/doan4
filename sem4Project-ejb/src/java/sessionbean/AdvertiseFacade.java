/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Advertise;
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
public class AdvertiseFacade extends AbstractFacade<Advertise> implements AdvertiseFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdvertiseFacade() {
        super(Advertise.class);
    }
    
    @Override
    public List<Advertise> getLatest(){
        Query query = em.createQuery("SELECT i FROM Advertise i ORDER BY i.advertiseID DESC");
        return query.getResultList();
    }
    
}
