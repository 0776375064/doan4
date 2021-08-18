/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.FavouriteAlbum;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author asus
 */
@Local
public interface FavouriteAlbumFacadeLocal {

    void create(FavouriteAlbum favouriteAlbum);

    void edit(FavouriteAlbum favouriteAlbum);

    void remove(FavouriteAlbum favouriteAlbum);

    FavouriteAlbum find(Object id);

    List<FavouriteAlbum> findAll();

    List<FavouriteAlbum> findRange(int[] range);

    int count();

    List listIDAlbum();

    public List listSongAll(String id);

    public List<Integer> myFavorite(int accountID);


}
