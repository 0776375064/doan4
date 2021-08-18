/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Albums;
import entities.Artists;
import entities.Songs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface ArtistsFacadeLocal {

    void create(Artists artists);

    void edit(Artists artists);

    void remove(Artists artists);

    Artists find(Object id);

    List<Artists> findAll();

    List<Artists> findRange(int[] range);

    int count();

    public int countPage();

    public List<Artists> Pagination(int pageNumber);

    public List<Albums> getAlbums(int artistID);

    public int countAlbums(int artistID);

    public int countSongs(int artistID);

    public int countArtistInSongs(int artistID);

    public List<Songs> getSongs(int artistID);

    public List<Artists> findByNames(String s);

    public List listArtistsLikeTop();

    public int countFollower(int artistID);
    
}
