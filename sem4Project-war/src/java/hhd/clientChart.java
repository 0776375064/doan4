/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionbean.SongTrackingFacadeLocal;
import sessionbean.SongsFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/clientChart")
public class clientChart extends HttpServlet {

    @EJB
    private SongsFacadeLocal songsFacade;

    @EJB
    private SongTrackingFacadeLocal songTrackingFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String app = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        if (todo.equals("view")) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = formatter.format(date);
            request.setAttribute("chart", songTrackingFacade.getTop10(strDate));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("chart/index.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("today")) {
            List<Object[]> list = songTrackingFacade.getTopToday(1);
            request.setAttribute("chart", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("chart/today.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("week")) {
            List<Object[]> list = songTrackingFacade.getTopWeek(1);
            request.setAttribute("chart", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("chart/weekly.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("month")) {
            List<Object[]> list = songTrackingFacade.getTopMonth(1);
            request.setAttribute("chart", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("chart/monthly.jsp");
            requestDispatcher.forward(request, response);
        }
        else if(todo.equals("release")){
            request.setAttribute("list", songsFacade.getLatestSonsg());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("song/new-release.jsp");
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
