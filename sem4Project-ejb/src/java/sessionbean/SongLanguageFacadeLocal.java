/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.SongLanguage;
import entities.Songs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface SongLanguageFacadeLocal {

    void create(SongLanguage songLanguage);

    void edit(SongLanguage songLanguage);

    void remove(SongLanguage songLanguage);

    SongLanguage find(Object id);

    List<SongLanguage> findAll();

    List<SongLanguage> findRange(int[] range);

    int count();

    public int countSongInLanguage(int langID);

    public List<Songs> getSongInLanguage(int langID);
    
}
