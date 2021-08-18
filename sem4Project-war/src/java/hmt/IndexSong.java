/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmt;

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
import javax.servlet.http.HttpSession;
import sessionbean.SongsFacadeLocal;

/**
 *
 * @author hmtua
 */
@WebServlet("/IndexSong")
public class IndexSong extends HttpServlet {

    @EJB
    private SongsFacadeLocal songsFacade;
    List<Songs> listSong;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("accountID", 1);
        String type = request.getParameter("type");
        listSong = songsFacade.findAll();
        if (type.equals("listAll")) {
            request.setAttribute("listSongAll", songsFacade.listSongAll());
            if (session.getAttribute("userID") != null) {
                request.setAttribute("idSongOrder", songsFacade.listIDSongOrder(String.valueOf(session.getAttribute("userID"))));
            }
            request.setAttribute("listSong", listSong);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/index.jsp");
            requestDispatcher.forward(request, response);
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
