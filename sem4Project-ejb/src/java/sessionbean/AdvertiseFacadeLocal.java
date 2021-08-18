/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Advertise;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface AdvertiseFacadeLocal {

    void create(Advertise advertise);

    void edit(Advertise advertise);

    void remove(Advertise advertise);

    Advertise find(Object id);

    List<Advertise> findAll();

    List<Advertise> findRange(int[] range);

    int count();

    public List<Advertise> getLatest();
    
}
