/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Accounts;
import entities.FavouriteAlbum;
import entities.SongLikes;
import entities.SongReview;
import entities.SongTracking;
import entities.Songs;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;
import sessionbean.AccountsFacadeLocal;
import sessionbean.FavouriteAlbumFacadeLocal;
import sessionbean.SongLikesFacadeLocal;
import sessionbean.SongReviewFacadeLocal;
import sessionbean.SongTrackingFacadeLocal;
import sessionbean.SongsFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/player")
public class Player extends HttpServlet {

    @EJB
    private FavouriteAlbumFacadeLocal favouriteAlbumFacade;

    @EJB
    private SongTrackingFacadeLocal songTrackingFacade;

    @EJB
    private SongReviewFacadeLocal songReviewFacade;

    @EJB
    private SongLikesFacadeLocal songLikesFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private SongsFacadeLocal songsFacade;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String app = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        HttpSession session = request.getSession(true);
        if (todo.equals("listen")) {
            // current date
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(date);
            // get if of song
            int id = Integer.parseInt(request.getParameter("id")); // id song
            Songs obj = songsFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            JSONArray ja = new JSONArray();
            if (session.getAttribute("accountID") == null) {
                json.put("msg", "login");
            } else if (obj == null) {
                json.put("msg", "error");
            } else if (obj.getPrice() != 0) {
                String accountString = session.getAttribute("accountID").toString();
                if (alreadyBought(accountString, request.getParameter("id"))) {
                    Object[] songTrack = songTrackingFacade.createTracking(id, strDate);
                    if (songTrack == null) {
                        //System.out.println("No row in database...........");
                        SongTracking objTracking = new SongTracking();
                        objTracking.setSongID(obj);
                        objTracking.setDateTracking(strDate);
                        objTracking.setListens(1);
                        songTrackingFacade.create(objTracking);
                    } else {
                        SongTracking songTracking = songTrackingFacade.find(songTrack[3]);
                        songTracking.setListens(songTracking.getListens() + 1);
                        songTrackingFacade.edit(songTracking);
                    }
                    obj.setViews(obj.getViews() + 1);
                    songsFacade.edit(obj);
                    json.put("msg", "success");
                    json.put("name", obj.getSongName());
                    json.put("thumbnail", obj.getThumbnail());
                    json.put("artist", obj.getArtistID().getArtistID());
                    json.put("artistName", obj.getArtistID().getNickname());
                    json.put("path", obj.getPath());
                    json.put("lyrics", obj.getLyrics());
                    json.put("like", songsFacade.countLikeSong(id));
                    json.put("cmt", songsFacade.getSongReview(id).size());
                    json.put("view", obj.getViews());
                    if (session != null) {
                        int userID = Integer.parseInt(session.getAttribute("accountID").toString());
                        if (accountsFacade.checkSongLike(userID, id) == 1) {
                            json.put("slike", "yes");
                        } else {
                            json.put("slike", "not");
                        }
                    }
                } else {
                    System.out.println("chua mua");
                    json.put("msg", "bought");
                }

            } else {
                /// spng tracking
                //System.out.println("Date " + strDate);
                Object[] songTrack = songTrackingFacade.createTracking(id, strDate);
                if (songTrack == null) {
                    //System.out.println("No row in database...........");
                    SongTracking objTracking = new SongTracking();
                    objTracking.setSongID(obj);
                    objTracking.setDateTracking(strDate);
                    objTracking.setListens(1);
                    songTrackingFacade.create(objTracking);
                } else {
                    SongTracking songTracking = songTrackingFacade.find(songTrack[3]);
                    songTracking.setListens(songTracking.getListens() + 1);
                    songTrackingFacade.edit(songTracking);
                }
                obj.setViews(obj.getViews() + 1);
                songsFacade.edit(obj);
                json.put("msg", "success");
                json.put("name", obj.getSongName());
                json.put("thumbnail", obj.getThumbnail());
                json.put("artist", obj.getArtistID().getArtistID());
                json.put("artistName", obj.getArtistID().getNickname());
                json.put("path", obj.getPath());
                json.put("lyrics", obj.getLyrics());
                json.put("like", songsFacade.countLikeSong(id));
                json.put("cmt", songsFacade.getSongReview(id).size());
                json.put("view", obj.getViews());
                if (session != null) {
                    int userID = Integer.parseInt(session.getAttribute("accountID").toString());
                    if (accountsFacade.checkSongLike(userID, id) == 1) {
                        json.put("slike", "yes");
                    } else {
                        json.put("slike", "not");
                    }
                }
            }
            if (session.getAttribute("accountID") != null) {
                int userID = Integer.parseInt(session.getAttribute("accountID").toString());
                for (Object song : accountsFacade.mySongBought(userID)) {
                    Songs idSong = songsFacade.find(song);
                    ja.put(new JSONObject().put("audio", idSong.getPath()).put("id", idSong.getSongID()));
                    json.put("value", ja);
                }
            }
            out.print(json.toString());
            // end listen
        } else if (todo.equals("like")) {
            int id = Integer.parseInt(request.getParameter("id")); // id song
            Songs obj = songsFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            if (obj == null) {
                json.put("msg", "error");
            } else {
                if (session != null && session.getAttribute("accountID") != null) {
                    int userID = Integer.parseInt(session.getAttribute("accountID").toString());
                    if (accountsFacade.checkSongLike(userID, id) == 1) {
                        json.put("msg", "unlike");
                        accountsFacade.deleteLike(userID, id);
                    } else {
                        SongLikes likes = new SongLikes();
                        Accounts account = accountsFacade.find(userID);
                        likes.setAccountID(account);
                        likes.setSongID(obj);
                        songLikesFacade.create(likes);
                        json.put("msg", "like");
                    }
                    json.put("numlike", songsFacade.countLikeSong(id));
                }
            }
            out.print(json.toString());
        } // end like
        // get comment song
        else if (todo.equals("review")) {
            int id = Integer.parseInt(request.getParameter("id")); // id song
            Songs obj = songsFacade.find(id);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                List<SongReview> list = songsFacade.getSongReview(id);
                for (SongReview sr : list) {
                    out.println("<div class='media' id='cmt" + sr.getReviewID() + "'>");
                    out.println("<div class='media-left'>");
                    out.println("<a>");
                    out.println("<img class=\"is-50-50 is-rounded\" src=\"" + app + "/storage/profile/" + sr.getAccountID().getAvatar() + "\" alt=\"\">");
                    out.println("</a>");
                    out.println("</div>");
                    out.println("<div class='media-content'>");
                    out.println("<div class='media-title'>");
                    out.println("<a>" + sr.getAccountID().getUsername() + "</a>");
                    out.println("<span class='media-time'>" + convertToTime(sr.getCreatedAt()) + "</span>");
                    if (session.getAttribute("accountID") != null) {
                        int aid = Integer.parseInt(session.getAttribute("accountID").toString());
                        if (aid == sr.getAccountID().getAccountID()) {
                            out.println("<span class='media-delete' data-cmt='" + sr.getReviewID() + "'>x<span>");
                        }
                    }
                    out.println("</div>");
                    out.println("<div class='media-subtitle light-text text-message'>");
                    out.println("<p>" + sr.getMessage() + "</p>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                }
            }
        } // end review
        else if (todo.equals("comment") && session.getAttribute("accountID") != null) {
            int id = Integer.parseInt(request.getParameter("id")); // id song
            String msg = request.getParameter("msg");
            Songs obj = songsFacade.find(id);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                Thread.sleep(1000);
                SongReview sr = new SongReview();
                int userID = Integer.parseInt(session.getAttribute("accountID").toString());
                Accounts account = accountsFacade.find(userID);
                sr.setAccountID(account);
                sr.setSongID(obj);
                sr.setMessage(msg);
                int dates = (int) (new Date().getTime() / 1000);
                sr.setCreatedAt(dates);
                songReviewFacade.create(sr);
                out.println("<div class='media' id='cmt" + sr.getReviewID() + "'>");
                out.println("<div class='media-left'>");
                out.println("<a>");
                out.println("<img class=\"is-50-50 is-rounded\" src=\"" + app + "/storage/profile/" + sr.getAccountID().getAvatar() + "\" alt=\"\">");
                out.println("</a>");
                out.println("</div>");
                out.println("<div class='media-content'>");
                out.println("<div class='media-title'>");
                out.println("<a>" + sr.getAccountID().getUsername() + "</a>");
                out.println("<span class='media-time'>" + convertToTime(sr.getCreatedAt()) + "</span>");
                if (session.getAttribute("accountID") != null) {
                    int aid = Integer.parseInt(session.getAttribute("accountID").toString());
                    if (aid == sr.getAccountID().getAccountID()) {
                        out.println("<span class='media-delete' data-cmt='" + sr.getReviewID() + "'>x<span>");
                    }
                }
                out.println("</div>");
                out.println("<div class='media-subtitle light-text text-message'>");
                out.println("<p>" + sr.getMessage() + "</p>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
            } catch (InterruptedException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (todo.equals("delcomment")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            if (request.getParameter("id") != null) {
                if (session.getAttribute("accountID") != null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int aid = Integer.parseInt(session.getAttribute("accountID").toString());
                    SongReview objReview = songReviewFacade.find(id);
                    if (objReview != null) {
                        if (objReview.getAccountID().getAccountID() == aid) {
                            songReviewFacade.remove(objReview);
                            json.put("msg", "success");
                        } else {
                            json.put("msg", "error");
                        }
                    }
                }
            }
            out.print(json);

        } // tracking chart
        else if (todo.equals("chart")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            ArrayList<String> labels = new ArrayList<>();
            ArrayList<Integer> nameSong = new ArrayList<>();
            ArrayList<String> entitySong = new ArrayList<>();

            // loop 3 top song
            ArrayList<Integer> top1 = new ArrayList<>();
            ArrayList<Integer> top2 = new ArrayList<>();
            ArrayList<Integer> top3 = new ArrayList<>();
            int i = 0;
            // current date
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(date);
            for (SongTracking songTracking : songTrackingFacade.getTop3(strDate)) {
                System.out.println("Song top " + songTracking.getSongID().getSongName() + " - " + songTracking.getDateTracking() + " view " + songTracking.getListens());
                nameSong.add(songTracking.getSongID().getSongID());
                entitySong.add(songTracking.getSongID().getSongName());
            }
            for (String label : songTrackingFacade.getDateTracking()) {
                labels.add(label);
                System.out.println("Loop ................... " + label);
                for (Integer namesString : nameSong) {
                    if (i == 0) {
                        System.out.println("loop 1");
                        if (songTrackingFacade.createTracking(namesString, label) != null) {
                            top1.add((Integer) songTrackingFacade.createTracking(namesString, label)[2]);
                        } else {
                            top1.add(0);
                        }
                    }
                    if (i == 1) {
                        System.out.println("loop 2");
                        if (songTrackingFacade.createTracking(namesString, label) != null) {
                            top2.add((Integer) songTrackingFacade.createTracking(namesString, label)[2]);
                        } else {
                            top2.add(0);
                        }
                    }
                    if (i == 2) {
                        System.out.println("loop 3");
                        if (songTrackingFacade.createTracking(namesString, label) != null) {
                            top3.add((Integer) songTrackingFacade.createTracking(namesString, label)[2]);
                        } else {
                            top3.add(0);
                        }
                    }
                    i++;

                }
                i = 0;

            }
            json.put("labels", labels);
            JSONArray ja = new JSONArray();
            int j = 0;
            for (String item : entitySong) {
                if (j == 0) {
                    ja.put(new JSONObject().put("name", item).put("data", top1));
                }
                if (j == 1) {
                    ja.put(new JSONObject().put("name", item).put("data", top2));
                }
                if (j == 2) {
                    ja.put(new JSONObject().put("name", item).put("data", top3));
                }
                j++;
            }
            json.put("value", ja);
            out.print(json.toString());

        } // end chart
        else if (todo.equals("favorite")) {
            if (session.getAttribute("accountID") != null) {
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                JSONObject json = new JSONObject();
                int idAcc = Integer.parseInt(session.getAttribute("accountID").toString());
                String idSong = request.getParameter("addAlbum");
                Songs song = songsFacade.find(Integer.parseInt(idSong));
                Accounts acc = accountsFacade.find(idAcc);
                FavouriteAlbum album = new FavouriteAlbum();
                album.setAccountID(acc);
                album.setAlbumName(acc.getFullname() + "- id:" + acc.getAccountID());
                album.setSongID(song);
                favouriteAlbumFacade.create(album);
                json.put("msg", "success");
                out.print(json);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

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

    /// check if user bought
    public boolean alreadyBought(String accountID, String songID) {
        String list = String.valueOf(songsFacade.listIDSongOrder(accountID));
        if (list.indexOf(songID) != -1) {
            return true;
        } else {
            return false;
        }
    }
}
