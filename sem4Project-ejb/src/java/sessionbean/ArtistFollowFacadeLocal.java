/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.ArtistFollow;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface ArtistFollowFacadeLocal {

    void create(ArtistFollow artistFollow);

    void edit(ArtistFollow artistFollow);

    void remove(ArtistFollow artistFollow);

    ArtistFollow find(Object id);

    List<ArtistFollow> findAll();

    List<ArtistFollow> findRange(int[] range);

    int count();
    
}
