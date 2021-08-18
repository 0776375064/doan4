/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.NewsLike;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface NewsLikeFacadeLocal {

    void create(NewsLike newsLike);

    void edit(NewsLike newsLike);

    void remove(NewsLike newsLike);

    NewsLike find(Object id);

    List<NewsLike> findAll();

    List<NewsLike> findRange(int[] range);

    int count();
    
}
