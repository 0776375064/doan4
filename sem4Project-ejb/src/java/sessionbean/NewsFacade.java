/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Comment;
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
public class NewsFacade extends AbstractFacade<News> implements NewsFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsFacade() {
        super(News.class);
    }

    // find product
    @Override
    public List<News> findNews(String name) {
        Query query = em.createQuery("SELECT p FROM News p WHERE p.title LIKE :title");
        query.setParameter("title", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<News> findByNames(String s) {
        Query query = em.createQuery("SELECT p FROM News p WHERE p.title LIKE :title");
        query.setParameter("title", "%" + s + "%");
        return query.getResultList();
    }

    @Override
    public int countLikeNews(int newID) {
        Query query = em.createQuery("SELECT i FROM NewsLike i WHERE i.newID = :newID");
        News news = NewsFacade.this.find(newID);
        query.setParameter("newID", news);
        return query.getResultList().size();
    }

    @Override
    public List<Comment> getComment(int newID) {
        Query query = em.createQuery("SELECT i FROM Comment i WHERE i.newID = :newID ORDER BY i.commentID DESC");
        News news = NewsFacade.this.find(newID);
        query.setParameter("newID", news);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getStatistic(String fdate, String tdate) {
        Query query = em.createNativeQuery("select views from News where NewID=?");
        query.setParameter(1, fdate);
        query.setParameter(2, tdate);
        return query.getResultList();
    }

    @Override
    public List<News> getLatest() {
        Query query = em.createQuery("SELECT p FROM News p ORDER BY p.newID DESC");
        return query.getResultList();
    }

    @Override
    public int checkLike(int newID, int userID) {
        Query query = em.createQuery("SELECT i FROM NewsLike i WHERE i.newID = :newID AND i.accountID = :accountID");
        News news = NewsFacade.this.find(newID);
        query.setParameter("newID", news);
        query.setParameter("accountID", userID);
        return query.getResultList().size();
    }
    @Override
    public int deleteLike(int newID, int userID) {
        Query query = em.createQuery("DELETE FROM NewsLike i WHERE i.newID = :newID AND i.accountID = :accountID");
        News news = NewsFacade.this.find(newID);
        query.setParameter("newID", news);
        query.setParameter("accountID", userID);
        return query.executeUpdate();
    }
     @Override
    public int countNewsInDay(String date) {
        try {
            Query query = em.createNativeQuery("SELECT Views FROM News WHERE CONVERT(varchar, CreatedAt) = ?1");
            
            query.setParameter(1, date);
            //query.setParameter(2, date);
            return (int) query.getSingleResult();
        } catch (Exception ex) {
            return 0;
        }
    }
    
     @Override
    public List<String> getDateNews(String fdate, String tdate) {
       Query query = em.createNativeQuery("SELECT CONVERT(varchar, CreatedAt) as 'date' FROM News o WHERE CreatedAt BETWEEN ?1 and ?2 GROUP BY  CONVERT(varchar, CreatedAt)");
      // Query query = em.createNativeQuery("SELECT Title FROM News o WHERE CreatedAt BETWEEN ?1 and ?2 GROUP BY  CONVERT(varchar, CreatedAt)");
         //Query query = em.createNativeQuery("SELECT Title FROM News WHERE CONVERT(varchar, CreatedAt) = ?1");
        query.setParameter(1, fdate);
        query.setParameter(2, tdate);
        return query.getResultList();
    }

}
