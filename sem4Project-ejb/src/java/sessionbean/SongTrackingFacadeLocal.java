/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.SongTracking;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface SongTrackingFacadeLocal {

    void create(SongTracking songTracking);

    void edit(SongTracking songTracking);

    void remove(SongTracking songTracking);

    SongTracking find(Object id);

    List<SongTracking> findAll();

    List<SongTracking> findRange(int[] range);

    int count();

    public Object[] createTracking(int songID, String date);

    public List<String> getDateTracking();

    public List<SongTracking> getTop3(String strDate);

    public List<SongTracking> getTop10(String strDate);

    public List<Object[]> getTopToday(int display);

    public List<Object[]> getTopWeek(int display);

    public List<Object[]> getTopMonth(int display);

    public List<Object[]> getTop50Songs(String startDateString, String enDate);

    public List<Object[]> getListenerofSong(int id, String startDate, String endDate);

    
}
