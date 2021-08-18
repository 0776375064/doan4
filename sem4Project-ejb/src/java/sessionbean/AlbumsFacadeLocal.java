/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Albums;
import entities.Songs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface AlbumsFacadeLocal {

    void create(Albums albums);

    void edit(Albums albums);

    void remove(Albums albums);

    Albums find(Object id);

    List<Albums> findAll();

    List<Albums> findRange(int[] range);

    int count();

    public List<Albums> getAlbumses(int artistID, int pageNumber);

    public int countAlbumsPage(int artistID);

    public int countSongInAlbum(int albumID);

    public List<Songs> getSongs(int albumID);

    public int countAlbums(int artistID);
    
}
