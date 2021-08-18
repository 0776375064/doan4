/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Categories;
import entities.News;
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
public class CategoriesFacade extends AbstractFacade<Categories> implements CategoriesFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriesFacade() {
        super(Categories.class);
    }

    @Override
    public List<News> getNewsByCategory(int categoryID) {
        Query query = em.createQuery("SELECT p FROM News p WHERE p.categoryID = :categoryID ORDER BY p.newID DESC");
        query.setParameter("categoryID", CategoriesFacade.this.find(categoryID));
        return query.getResultList();
    }

    @Override
    public int countSongSoftOf(int categoryID) {
        Query query = em.createQuery("SELECT i FROM News i WHERE i.categoryID = :categoryID");
        Categories sl = CategoriesFacade.this.find(categoryID);
        query.setParameter("categoryID", sl);
        return query.getResultList().size();
    }
    @Override
    public int getNewsCategory(int categoryID) {
        Query query = em.createQuery("SELECT b FROM News b WHERE b.categoryID = :categoryID ");
        query.setParameter("categoryID", CategoriesFacade.this.find(categoryID));
        return query.getResultList().size();
    }
}
