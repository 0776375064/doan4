/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmt;

import entities.Accounts;
import entities.Artists;
import entities.FavouriteAlbum;
import entities.Songs;
import hhd.Controller;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbean.AccountsFacadeLocal;
import sessionbean.ArtistsFacadeLocal;
import sessionbean.FavouriteAlbumFacadeLocal;
import sessionbean.SongsFacadeLocal;

/**
 *
 * @author hmtua
 */
@WebServlet("/SongDetail")
public class SongDetail extends HttpServlet {

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @EJB
    private FavouriteAlbumFacadeLocal favouriteAlbumFacade;

    @EJB
    private SongsFacadeLocal songsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String app = request.getContextPath(); // get real path
        HttpSession session = request.getSession(true);
        String idAcc = String.valueOf(session.getAttribute("accountID"));
        String songListID = request.getParameter("songID");
        if (session.getAttribute("accountID") == null) {
            response.sendRedirect(app + "/login.jsp");
        } else {
            if (songListID != null) {
                //thong tin nghe si
                Songs song = songsFacade.find(Integer.parseInt(songListID));
                request.setAttribute("songDetails", song);

                Artists objArtist = artistsFacade.find(song.getArtistID().getArtistID());
                request.setAttribute("artist", objArtist);

                // thong tin bai hat don 
                List<Songs> listSonghd = artistsFacade.getSongs(song.getArtistID().getArtistID());
                request.setAttribute("songs", listSonghd);

                //danh sach bai hat da mua 
                if (session.getAttribute("accountID") != null) {
                    request.setAttribute("idSongOrder", songsFacade.listIDSongOrder(String.valueOf(session.getAttribute("accountID"))));
                }

                //danh sach tat ca bai hat
                request.setAttribute("listAllSongAndArtist", songsFacade.listAllSongAndArtist("all"));

                // Danh sach bai hat top 5 cua ca si 
                request.setAttribute("listSongOfArtistTop5", songsFacade.listAllSongAndArtist(song.getArtistID().getArtistID() + "top10"));

                //danh sach tat ca bai hat voi nghe si tuonwg ung 
                request.setAttribute("listSongOfArtist", songsFacade.listAllSongAndArtist(String.valueOf(song.getArtistID().getArtistID())));

                //danh sachs bai hat 
                request.setAttribute("listSongArtistID", songsFacade.listSongAllIDArtistID(song.getArtistID().getArtistID().toString()));
                //
                //Danh sách nghe si yeu thich 
                request.setAttribute("artistsTop", artistsFacade.listArtistsLikeTop());

                request.setAttribute("trangThai", request.getParameter("trangThai"));
                //  request.setAttribute("ngheSi", artistsFacade.find(listSong.get(0)));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/detail_music.jsp");
                requestDispatcher.forward(request, response);
            }
            //add album
            if (request.getParameter("addAlbum") != null) {
                if (request.getParameter("addAlbum").equals("all")) {
                    request.setAttribute("album", favouriteAlbumFacade.listSongAll(idAcc));
                    if (session.getAttribute("accountID") != null) {
                        request.setAttribute("idSongOrder", songsFacade.listIDSongOrder(String.valueOf(session.getAttribute("accountID"))));
                    }
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/album.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    String idSong = request.getParameter("addAlbum");
                    Songs song = songsFacade.find(Integer.parseInt(idSong));
                    Accounts acc = accountsFacade.find(Integer.parseInt(idAcc));
                    FavouriteAlbum album = new FavouriteAlbum();
                    album.setAccountID(acc);
                    album.setAlbumName(acc.getFullname() + "- id:" + acc.getAccountID());
                    album.setSongID(song);
                    favouriteAlbumFacade.create(album);
                    String idAlbumEnd = songsFacade.albumIdEnd();
                    request.setAttribute("album", favouriteAlbumFacade.listSongAll(idAcc));
                    if (session.getAttribute("accountID") != null) {
                        request.setAttribute("idSongOrder", songsFacade.listIDSongOrder(String.valueOf(session.getAttribute("accountID"))));
                    }

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/album.jsp");
                    requestDispatcher.forward(request, response);
                }
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

}
