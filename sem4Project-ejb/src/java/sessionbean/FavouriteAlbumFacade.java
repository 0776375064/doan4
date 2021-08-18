/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbean;

import entities.FavouriteAlbum;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
public class FavouriteAlbumFacade extends AbstractFacade<FavouriteAlbum> implements FavouriteAlbumFacadeLocal {

    @PersistenceContext(unitName = "sem4Test-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FavouriteAlbumFacade() {
        super(FavouriteAlbum.class);
    }

    @Override
    public List listIDAlbum() {
        String sql = "SELECT SongID FROM FavouriteAlbum group by SongID";
        Query query = em.createNativeQuery(sql);
        List ListId = new ArrayList();
        ListId = query.getResultList();
        return ListId;
    }

    public List listSong(String idSong) {
        String sql = "select Songs.SongID,Songs.SongName,Songs.Path,Songs.Price,Songs.ArtistID,Songs.Thumbnail,Artists.Fullname  "
                + "from Songs  "
                + "full join Artists on Songs.ArtistID = Artists.ArtistID  "
                + "where Songs.SongID   in "
                + "(select FavouriteAlbum.SongID  "
                + "from FavouriteAlbum where FavouriteAlbum.AccountID = ?)";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, idSong);
        List<Object[]> authors = query.getResultList();
        List list = new ArrayList();
        String tam = null;
        int aa = 0;
        for (Object[] a : authors) {
            aa++;
            tam = "[" + a[0] + "/" + a[1] + "/" + a[2] + "/" + a[3] + "/" + a[4] + "/" + a[5] + "/" + a[6] + "]";
            list.add(tam);
        }

        return list;
    }

    @Override
    public List listSongAll(String id) {
        return listSong(id);
    }
    
    @Override
     public List<Integer> myFavorite(int accountID) {
        String sql = "SELECT SongID FROM FavouriteAlbum WHERE AccountID = ? group by SongID";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, accountID);
        return query.getResultList();
    }

}
