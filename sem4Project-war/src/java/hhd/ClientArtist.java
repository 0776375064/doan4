/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Accounts;
import entities.Albums;
import entities.ArtistFollow;
import entities.Artists;
import entities.Songs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import sessionbean.AccountsFacadeLocal;
import sessionbean.AlbumsFacadeLocal;
import sessionbean.ArtistFollowFacadeLocal;
import sessionbean.ArtistsFacadeLocal;
import sessionbean.SongsFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/ClientArtist")
public class ClientArtist extends HttpServlet {

    @EJB
    private ArtistFollowFacadeLocal artistFollowFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private AlbumsFacadeLocal albumsFacade;

    @EJB
    private SongsFacadeLocal songsFacade;

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        HttpSession session = request.getSession(true);
        if (todo.equals("info")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists artist = artistsFacade.find(id);
            List<Songs> listSong = artistsFacade.getSongs(id);
            request.setAttribute("artist", artist);
            request.setAttribute("artists", artistsFacade.Pagination(1));
            request.setAttribute("songs", listSong);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("artist/index.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("award")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists artist = artistsFacade.find(id);
            if (artist == null) {
                response.sendRedirect(applicationPath + "/");
            } else {
                request.setAttribute("artist", artist);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("artist/award.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("album")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists artist = artistsFacade.find(id);
            if (artist == null) {
                response.sendRedirect(applicationPath + "/");
            } else {
                List<Albums> list = artistsFacade.getAlbums(id);
                request.setAttribute("artist", artist);
                request.setAttribute("list", list);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("artist/album.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("albumdetails")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Albums album = albumsFacade.find(id);
            if (album == null) {
                response.sendRedirect(applicationPath + "/");
            } else {
                Artists artist = artistsFacade.find(album.getArtistID().getArtistID());
                request.setAttribute("artist", artist);
                List<Songs> list = albumsFacade.getSongs(id);
                request.setAttribute("list", list);
                request.setAttribute("album", album);
                request.setAttribute("artists", artistsFacade.Pagination(1));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("artist/albumdetails.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("songs")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists artist = artistsFacade.find(id);
            if (artist == null) {
                response.sendRedirect(applicationPath + "/");
            } else {
                List<Songs> list = artistsFacade.getSongs(id);
                request.setAttribute("artist", artist);
                request.setAttribute("list", list);
                request.setAttribute("artists", artistsFacade.Pagination(1));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("artist/songs.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("follow")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists artist = artistsFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            if (artist != null && session.getAttribute("accountID") != null) {
                int accountID = Integer.parseInt(session.getAttribute("accountID").toString());
                if(accountsFacade.checkArtistFollow(accountID, id) == 0){
                    ArtistFollow objArtistFollow = new ArtistFollow();
                    objArtistFollow.setArtistID(artist);
                    Accounts objAccounts = accountsFacade.find(accountID);
                    objArtistFollow.setAccountID(objAccounts);
                    artistFollowFacade.create(objArtistFollow);
                    json.put("msg", "follow");
                }
                else{
                    accountsFacade.deleteFollow(accountID,id);
                    json.put("msg", "unfollow");
                }
            }
            else{
                json.put("msg", "error");
            }
            out.print(json);
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
