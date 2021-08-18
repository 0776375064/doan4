/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Stories;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface StoriesFacadeLocal {

    void create(Stories stories);

    void edit(Stories stories);

    void remove(Stories stories);

    Stories find(Object id);

    List<Stories> findAll();

    List<Stories> findRange(int[] range);

    int count();

    public List<Stories> getList(int accountID);
    
}
