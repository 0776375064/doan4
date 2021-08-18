/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Accounts;
import entities.Advertise;
import entities.Categories;
import entities.News;
import entities.Songs;
import entities.TypeOfSong;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import sessionbean.AccountsFacadeLocal;
import sessionbean.AdvertiseFacadeLocal;
import sessionbean.AlbumsFacadeLocal;
import sessionbean.ArtistsFacadeLocal;
import sessionbean.CategoriesFacadeLocal;
import sessionbean.FeedbacksFacadeLocal;
import sessionbean.NewsFacadeLocal;
import sessionbean.OrdersFacadeLocal;
import sessionbean.SongLanguageFacadeLocal;
import sessionbean.SongTrackingFacadeLocal;
import sessionbean.SongsFacadeLocal;
import sessionbean.SuppliersFacadeLocal;
import sessionbean.TypeOfSongFacadeLocal;

/**
 *
 * @author asus
 */
@Named(value = "controller")
@RequestScoped
public class Controller {

    @EJB
    private TypeOfSongFacadeLocal typeOfSongFacade1;

    @EJB
    private AdvertiseFacadeLocal advertiseFacade;

    @EJB
    private FeedbacksFacadeLocal feedbacksFacade;

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private SongTrackingFacadeLocal songTrackingFacade;

    @EJB
    private NewsFacadeLocal newsFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private SuppliersFacadeLocal suppliersFacade;

    @EJB
    private TypeOfSongFacadeLocal typeOfSongFacade;

    @EJB
    private SongLanguageFacadeLocal songLanguageFacade;

    @EJB
    private SongsFacadeLocal songsFacade;

    @EJB
    private AlbumsFacadeLocal albumsFacade;

    /**
     * Creates a new instance of Controller
     */
    public Controller() {
    }

    // count track in album
    public int countTrack(int albumID) {
        return albumsFacade.countSongInAlbum(albumID);
    }

    // count like of song
    public int countLikeofSong(int songID) {
        return songsFacade.countLikeSong(songID);
    }

    // show top music index
    public List<Songs> TopSongs() {
        List<Songs> list = new ArrayList();
        list = songsFacade.getLatestSonsg();
        return list;
    }

    // get info song
    public Songs songInfo(int songID) {
        Songs obj = songsFacade.find(songID);
        return obj;
    }

    // count total song in language
    public int countSongInLang(int langID) {
        return songLanguageFacade.countSongInLanguage(langID);
    }

    // count total song soft of
    public int countSongInSoft(int typeID) {
        return typeOfSongFacade.countSongSoftOf(typeID);
    }

    // count song in supplier
    public int countSongInSupplier(int supID) {
        return suppliersFacade.countSongInSup(supID);
    }
    
    // count song of artist
    public int countSongOfArtist(int id){
        return artistsFacade.getSongs(id).size();
    }

    // convert time int to string format
    public String convertToTime(Integer timestamp) {
        int dates = (int) (new Date().getTime() / 1000);
        int diff = dates - timestamp;
        String out = "";
        if (diff <= 60) {
            out = "Just a moment";
        } else if (diff <= 3600) {
            int minute = (int) diff / 60;
            if (minute <= 1) {
                out = "1 minute ago";
            } else {
                out = minute + " minutes ago";
            }
        } else if ((diff <= 86400) && (diff > 3600)) {
            int hour = (int) diff / 3600;
            if (hour <= 1) {
                out = "1 hour ago";
            } else {
                out = hour + " hours ago";
            }
        } else if ((diff >= 86400) && (diff < 604800)) {
            int day = (int) diff / 86400;
            if (day <= 1) {
                out = "1 day ago";
            } else {
                out = day + " days ago";
            }
        } else if ((diff >= 604800) && (diff < 2592000)) {
            int week = (int) diff / 604800;
            if (week <= 1) {
                out = "1 week ago";
            } else {
                out = week + " weeks ago";
            }
        } else if ((diff >= 2592000) && (diff < 31092000)) {
            int month = (int) diff / 2592000;
            if (month <= 1) {
                out = "1 month ago";
            } else {
                out = month + " months ago";
            }
        } else if ((diff >= 31092000) && (diff < 1200000000)) {
            int year = (int) diff / 31092000;
            if (year <= 1) {
                out = "1 year ago";
            } else {
                out = year + " years ago";
            }
        }
        return out;
    }

    // get category
    public List<Categories> listCategories() {
        return categoriesFacade.findAll();
    }

    // get list news
    public List<News> listNews() {
        return newsFacade.getLatest();
    }

    // get top music today
    public List<Object[]> topMusicInday(int display) {
        return songTrackingFacade.getTopToday(display);
    }
    // get top music today

    public List<Object[]> topMusicInWeek(int display) {
        return songTrackingFacade.getTopWeek(display);
    }
    // get top music today

    public List<Object[]> topMusicInMonth(int display) {
        return songTrackingFacade.getTopMonth(display);
    }

    public boolean alreadyBought(String accountID, String songID) {
        String list = String.valueOf(songsFacade.listIDSongOrder(accountID));
        if (list.indexOf(songID) != -1) {
            return true;
        } else {
            return false;
        }
    }

    public List<Advertise> listAdvertise() {
        return advertiseFacade.getLatest();
    }

    public Accounts account(String accountID) {
        int id = Integer.parseInt(accountID);
        Accounts obj = accountsFacade.find(id);
        return obj;
    }

    public int countOrder() {
        return ordersFacade.count();
    }

    public int countSong() {
        return songsFacade.count();
    }

    public int countAlbum() {
        return albumsFacade.count();
    }

    public int countArtist() {
        return artistsFacade.count();
    }

    public int countAccount() {
        return accountsFacade.count();
    }

    public int countCategory() {
        return categoriesFacade.count();
    }

    public int countNew() {
        return newsFacade.count();
    }

    public int countFeedback() {
        return feedbacksFacade.count();
    }

    // count album of artist
    public int countAlbums(int artisttID) {
        return albumsFacade.countAlbums(artisttID);
    }

    // format date
    public String formatDate(Date s) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dates = formatter.format(s);
        return dates;
    }

    // list genre
    public List<TypeOfSong> listGenre() {
        List<TypeOfSong> list = typeOfSongFacade.getLatest();
        return list;
    }

    // sub string
    public String subWord(String myString) {
        String[] arr = myString.split("\\s+");
        int N = 1;
        if (arr.length > 50) {
            N = 50; // NUMBER OF WORDS THAT YOU NEED

        } else {
            N = 1;
        }
        String nWords = "";

        // concatenating number of words that you required
        for (int i = 0; i < N; i++) {
            nWords = nWords + " " + arr[i];
        }
        return nWords;
    }
    
    // check if follow
    public boolean checkIfFollow(int accountID, int artistID){
        if(accountsFacade.checkArtistFollow(accountID, artistID) == 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    // artist count followers
    public int countFollowers(int artistID){
        return artistsFacade.countFollower(artistID);
    }
    
    public List<Object> myListSong(int accountID){
        return accountsFacade.mySongBought(accountID);
    }
}
