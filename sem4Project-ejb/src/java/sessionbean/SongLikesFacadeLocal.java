/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.SongLikes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface SongLikesFacadeLocal {

    void create(SongLikes songLikes);

    void edit(SongLikes songLikes);

    void remove(SongLikes songLikes);

    SongLikes find(Object id);

    List<SongLikes> findAll();

    List<SongLikes> findRange(int[] range);

    int count();
    
}
