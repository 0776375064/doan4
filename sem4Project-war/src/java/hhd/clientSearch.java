/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Artists;
import entities.News;
import entities.Songs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionbean.ArtistsFacadeLocal;
import sessionbean.NewsFacadeLocal;
import sessionbean.SongsFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/clientSearch")
public class clientSearch extends HttpServlet {

    @EJB
    private SongsFacadeLocal songsFacade;

    @EJB
    private NewsFacadeLocal newsFacade;

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("submit");
        if (todo.equals("search")) {
            if (request.getParameter("s") != null) {
                String str = request.getParameter("s");
                List<News> listNews = new ArrayList();
                List<Songs> listSong = new ArrayList();
                List<Artists> listArtist = new ArrayList();
               
                if (str.isEmpty()) {
                    request.setAttribute("news", listNews);
                    request.setAttribute("artists", listArtist);
                    request.setAttribute("listMusic",listSong);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("search/index.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    listSong = songsFacade.findByNames(str);
                    listArtist = artistsFacade.findByNames(str);
                    listNews = newsFacade.findByNames(str);
                    request.setAttribute("news", listNews);
                    request.setAttribute("artists", listArtist);
                    request.setAttribute("listMusic",listSong);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("search/index.jsp");
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
