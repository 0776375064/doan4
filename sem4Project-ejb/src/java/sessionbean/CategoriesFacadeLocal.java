/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.Categories;
import entities.News;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface CategoriesFacadeLocal {

    void create(Categories categories);

    void edit(Categories categories);

    void remove(Categories categories);

    Categories find(Object id);

    List<Categories> findAll();

    List<Categories> findRange(int[] range);

    int count();

    public List<News> getNewsByCategory(int categoryID);

    public int countSongSoftOf(int categoryID);

    public int getNewsCategory(int categoryID);
    
}
