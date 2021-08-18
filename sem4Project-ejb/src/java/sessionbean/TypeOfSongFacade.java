/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Songs;
import entities.TypeOfSong;
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
public class TypeOfSongFacade extends AbstractFacade<TypeOfSong> implements TypeOfSongFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeOfSongFacade() {
        super(TypeOfSong.class);
    }

    @Override
    public int countSongSoftOf(int typeID) {
        Query query = em.createQuery("SELECT i FROM Songs i WHERE i.typeID = :typeID");
        TypeOfSong sl = TypeOfSongFacade.this.find(typeID);
        query.setParameter("typeID", sl);
        return query.getResultList().size();
    }

    @Override
    public List<TypeOfSong> getLatest() {
        Query query = em.createQuery("SELECT i FROM TypeOfSong i ORDER BY i.typeID DESC");
        return query.getResultList();
    }

    @Override
    public List<Songs> getListSongInType(int typeID) {
        Query query = em.createQuery("SELECT i FROM Songs i WHERE i.typeID = :typeID ORDER BY i.songID DESC");
        TypeOfSong sl = TypeOfSongFacade.this.find(typeID);
        query.setParameter("typeID", sl);
        return query.getResultList();
    }
}
