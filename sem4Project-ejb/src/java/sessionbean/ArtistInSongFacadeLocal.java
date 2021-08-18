/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.ArtistInSong;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface ArtistInSongFacadeLocal {

    void create(ArtistInSong artistInSong);

    void edit(ArtistInSong artistInSong);

    void remove(ArtistInSong artistInSong);

    ArtistInSong find(Object id);

    List<ArtistInSong> findAll();

    List<ArtistInSong> findRange(int[] range);

    int count();

    public List<ArtistInSong> getAllSongs(int artistID);
    
}
