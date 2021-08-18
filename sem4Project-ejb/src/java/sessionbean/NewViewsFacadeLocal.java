/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.NewViews;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface NewViewsFacadeLocal {

    void create(NewViews newViews);

    void edit(NewViews newViews);

    void remove(NewViews newViews);

    NewViews find(Object id);

    List<NewViews> findAll();

    List<NewViews> findRange(int[] range);

    int count();
    
}
