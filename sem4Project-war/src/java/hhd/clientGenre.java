/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Songs;
import entities.TypeOfSong;
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
import sessionbean.TypeOfSongFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/clientGenre")
public class clientGenre extends HttpServlet {

    @EJB
    private TypeOfSongFacadeLocal typeOfSongFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String app = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        if (todo.equals("list")) {
            List<TypeOfSong> list = typeOfSongFacade.getLatest();
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("song/genre.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("view")) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Songs> listSong = typeOfSongFacade.getListSongInType(id);
                TypeOfSong objTypeOfSong = typeOfSongFacade.find(id);
                if (objTypeOfSong != null) {
                     request.setAttribute("genre", objTypeOfSong);
                    request.setAttribute("list", listSong);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("song/songInGenre.jsp");
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
