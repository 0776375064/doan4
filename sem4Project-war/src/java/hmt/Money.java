/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionbean.OrderDetailsFacadeLocal;
import sessionbean.OrdersFacadeLocal;

/**
 *
 * @author hmtua
 */
@WebServlet("/Money")
public class Money extends HttpServlet {

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String date = request.getParameter("date");
        LocalDate d1 = java.time.LocalDate.now();
        if (date.equals("hientais")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/money/index.jsp");
            requestDispatcher.forward(request, response);
        }
        if (date.equals("hientai")) {
            //Chart money
            request.setAttribute("chartMoney", ordersFacade.chartMoney(d1.toString(), d1.toString()));
            //sumMoney
            request.setAttribute("sumMoney", ordersFacade.sumMoney(d1.toString(), d1.toString()));
            //countMoney
            request.setAttribute("countMoney", ordersFacade.countMoneyt(d1.toString(), d1.toString()));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/money/indexAjax.jsp");
            requestDispatcher.forward(request, response);
        } else if (date.equals("chidinh")) {
            String startDate = request.getParameter("start");
            String endDate = request.getParameter("end");
            //Chart money
            request.setAttribute("chartMoney", ordersFacade.chartMoney(startDate, endDate));
            //sumMoney
            request.setAttribute("sumMoney", ordersFacade.sumMoney(startDate, endDate));
            //countMoney
            request.setAttribute("countMoney", ordersFacade.countMoneyt(startDate, endDate));

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/money/indexAjax.jsp");
            requestDispatcher.forward(request, response);
        }
        if (date.equals("detail")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/money/detail.jsp");
            requestDispatcher.forward(request, response);
        }
         if (date.equals("details")) {
            request.setAttribute("detail",orderDetailsFacade.findAll());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/money/detailAjax.jsp");
            requestDispatcher.forward(request, response);
        }
         if (date.equals("detailsdate")) {
            request.setAttribute("detail",orderDetailsFacade.findAll());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/money/detailAjax.jsp");
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
