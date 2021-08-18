/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmt;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.YearMonth;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionbean.OrdersFacadeLocal;

/**
 *
 * @author hmtua
 */
@WebServlet("/ManageBillOrder")
public class ManageBillOrder extends HttpServlet {

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // nhan requet truy xuat thoi gian 
        String dateStart = request.getParameter("start");
        String dateEnd = request.getParameter("end");
        //nhaanh  gai tri reuy xuat
        String retun = request.getParameter("date");

        LocalDate start = YearMonth.now().atDay(1);
        LocalDate end = YearMonth.now().atEndOfMonth();
        LocalDate d1 = java.time.LocalDate.now();

        // lay de truy xuat ket qua chi tiet
        SuLy suLy = new SuLy();

        //indix
        if (retun.equals("hientai")) {
            //tra ve thong tin order
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/index.jsp");
            requestDispatcher.forward(request, response);
        } else if (retun.equals("hientaidate")) {
            //tra ve thong tin order vieu do 
            request.setAttribute("order", ordersFacade.sumOrrder(d1.toString(), d1.toString()));
            //thont in sum somorder
            request.setAttribute("sunAll", ordersFacade.sunAll(d1.toString(), d1.toString()));
            request.setAttribute("sumPaid", ordersFacade.sunPaid(d1.toString(), d1.toString()));
            request.setAttribute("sumUnpaid", ordersFacade.sunUnpaid(d1.toString(), d1.toString()));
            request.setAttribute("sumCanceled", ordersFacade.sumCanceled(d1.toString(), d1.toString()));

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/indexByDate.jsp");
            requestDispatcher.forward(request, response);
        } else if (retun.equals("chidinh")) {
            String s = request.getParameter("start");
            String e = request.getParameter("end");
            //tra ve thong tin order bieu do 
            request.setAttribute("order", ordersFacade.sumOrrder(s.toString(), e.toString()));
            //thont in sum somorder
            request.setAttribute("sunAll", ordersFacade.sunAll(s.toString(), e.toString()));
            request.setAttribute("sumPaid", ordersFacade.sunPaid(s.toString(), e.toString()));
            request.setAttribute("sumUnpaid", ordersFacade.sunUnpaid(s.toString(), e.toString()));
            request.setAttribute("sumCanceled", ordersFacade.sumCanceled(s.toString(), e.toString()));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/indexByDate.jsp");
            requestDispatcher.forward(request, response);
        }
        //detail
        if (retun.equals("details")) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/details.jsp");
            requestDispatcher.forward(request, response);

        }

        if (retun.indexOf("detailsAll") != -1) {
            request.setAttribute("informationOrderDate", ordersFacade.nformationOrder(d1.toString(), d1.toString()));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/all.jsp");
            requestDispatcher.forward(request, response);
        } else if (retun.indexOf("CoutomsAll") != -1) {
            request.setAttribute("informationOrderDate", ordersFacade.nformationOrder(dateStart, dateEnd));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/all.jsp");
            requestDispatcher.forward(request, response);
        }

        if (retun.indexOf("detailsPaid") != -1) {
            System.out.println("vao detail ");
            request.setAttribute("informationOrderDate", ordersFacade.nformationOrder(d1.toString(), d1.toString()));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/paid.jsp");
            requestDispatcher.forward(request, response);
        } else if (retun.indexOf("CoutomsPaid") != -1) {
            System.out.println("vao custome");
            request.setAttribute("informationOrderDate", ordersFacade.nformationOrder(dateStart, dateEnd));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/paid.jsp");
            requestDispatcher.forward(request, response);
        }

        if (retun.indexOf("detailsUnpaid") != -1) {
            request.setAttribute("informationOrderDate", ordersFacade.nformationOrder(d1.toString(), d1.toString()));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/unpaid.jsp");
            requestDispatcher.forward(request, response);
        } else if (retun.indexOf("CoutomsUnpaid") != -1) {
            request.setAttribute("informationOrderDate", ordersFacade.nformationOrder(dateStart, dateEnd));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/unpaid.jsp");
            requestDispatcher.forward(request, response);
        }

        if (retun.indexOf("detailsCanceled") != -1) {
            request.setAttribute("informationOrderDate", ordersFacade.nformationOrder(d1.toString(), d1.toString()));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/canceled.jsp");
            requestDispatcher.forward(request, response);
        } else if (retun.indexOf("CoutomsCanceled") != -1) {
            request.setAttribute("informationOrderDate", ordersFacade.nformationOrder(dateStart, dateEnd));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/canceled.jsp");
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
