/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.SongReview;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface SongReviewFacadeLocal {

    void create(SongReview songReview);

    void edit(SongReview songReview);

    void remove(SongReview songReview);

    SongReview find(Object id);

    List<SongReview> findAll();

    List<SongReview> findRange(int[] range);

    int count();
    
}
