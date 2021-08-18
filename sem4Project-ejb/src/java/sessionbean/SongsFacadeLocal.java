/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.SongReview;
import entities.Songs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface SongsFacadeLocal {

    void create(Songs songs);

    void edit(Songs songs);

    void remove(Songs songs);

    Songs find(Object id);

    List<Songs> findAll();

    List<Songs> findRange(int[] range);

    int count();

    public List<Songs> Pagination(int pageNumber);

    public int countPage();

    public int countOrders(int SongID);

    public int countLikeSong(int songID);

    public List<Songs> findByNames(String s);

    public List<Object[]> getStatistic(String fdate, String tdate);

    public List<SongReview> getSongReview(int songID);

    public List<String> getDateOrder(String fdate, String tdate);

    public int countOrdersInDay(String date);

    public List listSongAll();

    public List listIDSongOrder(String AccID);

    public List listSongAllIDArtistID(String ArtistID);

    public String albumIdEnd();

    public List<Songs> getLatestSonsg();

    public List<Songs> getSong(int id);

    public List listAllSongAndArtist(String idArtists);

    
}
