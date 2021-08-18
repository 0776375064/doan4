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
import java.util.List;
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
@WebServlet("/ManageRevenue")
public class ManageRevenue extends HttpServlet {

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //day ngay dau va cuoi thanh 
        LocalDate start = YearMonth.now().atDay(1);
        LocalDate end = YearMonth.now().atEndOfMonth();
        LocalDate d1 = java.time.LocalDate.now();
        //nhaanh  gai tri reuy xuat
        String sqlSumPrice = request.getParameter("date");
        //khoi tao lop su ly
        SuLy suLy = new SuLy();
        //lay  price date 
        if (!sqlSumPrice.equals(null)) {
            //index
            //lay doan thu trong thang hien tai
            if (sqlSumPrice.equals("hientai")) {

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/doanhthu/index.jsp");
                requestDispatcher.forward(request, response);
            } else if (sqlSumPrice.endsWith("hientaidate")) {
                // nhan requet truy xuat thoi gian 

                //Danh Sach Ngay cho ban doanh thu 
                request.setAttribute("listDay", suLy.days(d1.toString(), d1.toString()));

                //gia trij price da lam tron
                request.setAttribute("sumPriceAllInDateNo", ordersFacade.sumPriceOderAllNo(d1.toString(), d1.toString()));

                //gia tri price chinh xac da themdau .
                request.setAttribute("sumPriceAllInDate", ordersFacade.sumPriceOderAll(d1.toString(), d1.toString()));

                //lay danh sach gia trong thang of song song
                request.setAttribute("listPriceAll", ordersFacade.PriceAll(d1.toString(), d1.toString()));

                //lay danh sach gia trong thang of money account
                request.setAttribute("listPriceAllAccount", ordersFacade.PriceAllAccount(d1.toString(), d1.toString()));
               
                //lay danh sach gia trong thang of money admin
                request.setAttribute("listPriceAllAdmint", ordersFacade.PriceAllAdmin(d1.toString(), d1.toString()));

                //dem so sluot order trong thang
                request.setAttribute("countOrder", ordersFacade.countOrderNo(d1.toString(), d1.toString()));

                //dem so sluot order da thanh toan trong  trong thang of song
                request.setAttribute("countOrderIS", ordersFacade.countOrderNotIs(d1.toString(), d1.toString()));
                
                //So luong  order da thanh toan trong  trong thang of Admin
                request.setAttribute("countOrderAdmin", ordersFacade.countOrderAdmin(d1.toString(), d1.toString()));

                //dem so sluot order da thanh toan trong  trong thang of account
                request.setAttribute("countOrderISAccount", ordersFacade.countOrderNotIsAccount(d1.toString(), d1.toString()));

                // lay thong tin sum top 10
                request.setAttribute("sumTop10", ordersFacade.sumPriceTop(d1.toString(), d1.toString()));

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/doanhthu/indexByDate.jsp");
                requestDispatcher.forward(request, response);
            } else if (sqlSumPrice.equals("chidinh")) {
                //gia trij price da lam tron
                //   request.setAttribute("sumPriceAllInDateNo", ordersFacade.sumPriceOderAllNo(dateStart, dateEnd));

                // nhan requet truy xuat thoi gian 
                String dateStart = request.getParameter("start");
                String dateEnd = request.getParameter("end");
                //Danh Sach Ngay cho ban doanh thu 
                request.setAttribute("listDay", suLy.days(dateStart.toString(), dateEnd.toString()));

                //Tong doanh thu trong khoang thoi gian da chon
                request.setAttribute("sumPriceAllInDate", ordersFacade.sumPriceOderAll(dateStart, dateEnd));

                //Bieeur do tong daon thu 
                request.setAttribute("listPriceAll", ordersFacade.PriceAll(dateStart, dateEnd));

                //lay danh sach gia trong thang of money account
                request.setAttribute("listPriceAllAccount", ordersFacade.PriceAllAccount(dateStart, dateEnd));

                //lay danh sach gia trong thang of money admin
                request.setAttribute("listPriceAllAdmint", ordersFacade.PriceAllAdmin(dateStart, dateEnd));

                //Dem so sluot order co trong thang
                request.setAttribute("countOrder", ordersFacade.countOrderNo(dateStart, dateEnd));

                //So luong  order da thanh toan trong  trong thang of song 
                request.setAttribute("countOrderIS", ordersFacade.countOrderNotIs(dateStart, dateEnd));
                
                 //So luong  order da thanh toan trong  trong thang of Admin
                request.setAttribute("countOrderAdmin", ordersFacade.countOrderAdmin(dateStart, dateEnd));

                //dem so sluot order da thanh toan trong  trong thang of account
                request.setAttribute("countOrderISAccount", ordersFacade.countOrderNotIsAccount(dateStart, dateEnd));

                // Bieu do top 5 san pham ban chay nhat 
                request.setAttribute("sumTop10", ordersFacade.sumPriceTop(dateStart, dateEnd));

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/doanhthu/indexByDate.jsp");
                requestDispatcher.forward(request, response);
            }

            //detail
            //lay lít san sam doan thu 
            if (sqlSumPrice.equals("listAll")) {
                // nhan requet truy xuat thoi gian 
                request.setAttribute("listSong", ordersFacade.listSong(d1.toString(), d1.toString()));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/doanhthu/listAll.jsp");
                requestDispatcher.forward(request, response);
            }
            if (sqlSumPrice.indexOf("listAllDetailHomNay") != -1) {
                // nhan requet truy xuat thoi gian 
                String dateStart = request.getParameter("start");
                String dateEnd = request.getParameter("end");
                request.setAttribute("listSong", ordersFacade.listSong(d1.toString(), d1.toString()));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/doanhthu/ajaxdetail.jsp");
                requestDispatcher.forward(request, response);
            }
            if (sqlSumPrice.indexOf("listAllDetailChiDinh") != -1) {
                // nhan requet truy xuat thoi gian 
                String dateStart = request.getParameter("start");
                String dateEnd = request.getParameter("end");
                request.setAttribute("listSong", ordersFacade.listSong(dateStart, dateEnd));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/doanhthu/ajaxdetail.jsp");
                requestDispatcher.forward(request, response);
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
    }

    public String lamTton(String soLe) {
        String sum = soLe;
        char[] t = sum.toCharArray();
        sum = "";
        int inedx1 = Integer.parseInt(String.valueOf(t[0]));
        int inedx2 = Integer.parseInt(String.valueOf(t[1]));
        if (inedx2 < 9) {
            inedx2 += 1;
            for (int i = 0; i < t.length; i++) {
                if (i == 0) {
                    sum += String.valueOf(t[i]);

                } else if (i == 1) {
                    sum += String.valueOf(inedx2);
                } else {
                    sum += "0";
                }

            }
        }
        return sum;
    }

}
