/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Songs;
import entities.TypeOfSong;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface TypeOfSongFacadeLocal {

    void create(TypeOfSong typeOfSong);

    void edit(TypeOfSong typeOfSong);

    void remove(TypeOfSong typeOfSong);

    TypeOfSong find(Object id);

    List<TypeOfSong> findAll();

    List<TypeOfSong> findRange(int[] range);

    int count();

    public int countSongSoftOf(int typeID);

    public List<TypeOfSong> getLatest();

    public List<Songs> getListSongInType(int typeID);
    
}
