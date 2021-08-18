/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Songs;
import entities.Suppliers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface SuppliersFacadeLocal {

    void create(Suppliers suppliers);

    void edit(Suppliers suppliers);

    void remove(Suppliers suppliers);

    Suppliers find(Object id);

    List<Suppliers> findAll();

    List<Suppliers> findRange(int[] range);

    int count();

    public int countSongInSup(int typeID);

    public List<Songs> getSongInSup(int typeID);
    
}
