/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.SongTracking;
import entities.Songs;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class SongTrackingFacade extends AbstractFacade<SongTracking> implements SongTrackingFacadeLocal {

    @EJB
    private SongsFacadeLocal songsFacade;

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SongTrackingFacade() {
        super(SongTracking.class);
    }

    @Override
    public Object[] createTracking(int songID, String date) {
        try {
            Query query = em.createNativeQuery("SELECT d.SongID, d.DateTracking, d.Listens, d.SongChartID FROM SongTracking d WHERE d.SongID = ?1 and d.DateTracking = ?2");
            query.setParameter(1, songID);
            query.setParameter(2, date);
            return (Object[]) query.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<String> getDateTracking() {
        try {
            Query query = em.createNativeQuery("SELECT TOP 7 CAST(d.DateTracking as Varchar(50)) FROM SongTracking d GROUP BY d.DateTracking ORDER BY d.DateTracking DESC");
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    // top 3
    @Override
    public List<SongTracking> getTop3(String strDate) {
        Query query = em.createQuery("SELECT p FROM SongTracking p WHERE p.dateTracking = :dateTracking ORDER BY p.listens DESC");
        query.setParameter("dateTracking", strDate);
        query.setMaxResults(3);
        return query.getResultList();
    }

    // top 3
    @Override
    public List<SongTracking> getTop10(String strDate) {
        Query query = em.createQuery("SELECT p FROM SongTracking p WHERE p.dateTracking = :dateTracking ORDER BY p.listens DESC");
        query.setParameter("dateTracking", strDate);
        query.setMaxResults(10);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getTopToday(int display) {
        try {
            if (display == 0) {
                Query query = em.createNativeQuery("SELECT TOP 5 s.SongID, s.Listens FROM SongTracking s WHERE s.DateTracking = CAST( GETDATE() AS Date ) ORDER BY s.Listens DESC");
                return query.getResultList();
            } else {
                Query query = em.createNativeQuery("SELECT TOP 30 s.SongID, s.Listens FROM SongTracking s WHERE s.DateTracking = CAST( GETDATE() AS Date ) ORDER BY s.Listens DESC");
                return query.getResultList();
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Object[]> getTopWeek(int display) {
        try {
            if (display == 0) {
                Query query = em.createNativeQuery("SELECT TOP 5 s.SongID, DATEPART(WEEK,s.DateTracking) Week, sum(s.Listens) as 'listen' FROM SongTracking s \n"
                        + "WHERE DATEPART(WEEK,s.DateTracking) = DATEPART(WEEK,GETDATE())\n"
                        + "GROUP BY s.SongID, DATEPART(WEEK,s.DateTracking)\n"
                        + "ORDER BY sum(s.Listens) DESC");
                return query.getResultList();
            } else {
                Query query = em.createNativeQuery("SELECT TOP 30 s.SongID, DATEPART(WEEK,s.DateTracking) Week, sum(s.Listens) as 'listen' FROM SongTracking s \n"
                        + "WHERE DATEPART(WEEK,s.DateTracking) = DATEPART(WEEK,GETDATE())\n"
                        + "GROUP BY s.SongID, DATEPART(WEEK,s.DateTracking)\n"
                        + "ORDER BY sum(s.Listens) DESC");
                return query.getResultList();
            }
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public List<Object[]> getTopMonth(int display) {
        try {
            if (display == 0) {
                Query query = em.createNativeQuery("SELECT TOP 5 s.SongID, DATEPART(Month,s.DateTracking) Month, sum(s.Listens) as 'listen' FROM SongTracking s \n"
                        + "WHERE DATEPART(Month,s.DateTracking) =  DATEPART(Month,GETDATE())\n"
                        + "GROUP BY s.SongID, DATEPART(Month,s.DateTracking)\n"
                        + "ORDER BY sum(s.Listens) DESC");
                return query.getResultList();
            } else {
                Query query = em.createNativeQuery("SELECT TOP 30 s.SongID, DATEPART(Month,s.DateTracking) Month, sum(s.Listens) as 'listen' FROM SongTracking s \n"
                        + "WHERE DATEPART(Month,s.DateTracking) =  DATEPART(Month,GETDATE())\n"
                        + "GROUP BY s.SongID, DATEPART(Month,s.DateTracking)\n"
                        + "ORDER BY sum(s.Listens) DESC");
                return query.getResultList();
            }
        } catch (Exception ex) {
            return null;
        }
    }

    // statistic top 50 songs by date
    @Override
    public List<Object[]> getTop50Songs(String startDateString, String enDate) {
        try {
            Query query = em.createNativeQuery("SELECT TOP 50 t.SongID, SUM(t.Listens) as 'listeners' FROM SongTracking t "
                    + "WHERE t.DateTracking BETWEEN ?1 AND ?2 GROUP BY t.SongID ORDER  BY SUM(t.Listens) DESC");
            query.setParameter(1, startDateString);
            query.setParameter(2, enDate);
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    
    @Override
    public List<Object[]> getListenerofSong(int id, String startDate, String endDate){
        try {
            Query query = em.createNativeQuery("SELECT * FROM SongTracking t WHERE t.SongID = ?1 AND t.DateTracking "
                    + "BETWEEN ?2 AND ?3 ORDER BY t.SongChartID DESC");
            query.setParameter(1, id);
            query.setParameter(2, startDate);
            query.setParameter(3, endDate);
            return query.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
}
