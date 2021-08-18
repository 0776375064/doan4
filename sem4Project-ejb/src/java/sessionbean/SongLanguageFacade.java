/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.SongLanguage;
import entities.Songs;
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
public class SongLanguageFacade extends AbstractFacade<SongLanguage> implements SongLanguageFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SongLanguageFacade() {
        super(SongLanguage.class);
    }

    @Override
    public int countSongInLanguage(int langID) {
        Query query = em.createQuery("SELECT i FROM Songs i WHERE i.langID = :langID");
        SongLanguage sl = SongLanguageFacade.this.find(langID);
        query.setParameter("langID", sl);
        return query.getResultList().size();
    }

    @Override
    public List<Songs> getSongInLanguage(int langID) {
        Query query = em.createQuery("SELECT i FROM Songs i WHERE i.langID = :langID");
        SongLanguage sl = SongLanguageFacade.this.find(langID);
        query.setParameter("langID", sl);
        return query.getResultList();
    }

}
