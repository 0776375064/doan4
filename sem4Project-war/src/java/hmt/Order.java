/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmt;

import entities.Accounts;
import entities.Accounts_;
import entities.OrderDetails;
import entities.Orders;
import entities.Songs;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
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
import sessionbean.OrderDetailsFacadeLocal;
import sessionbean.OrdersFacadeLocal;
import sessionbean.SongsFacadeLocal;

/**
 *
 * @author hmtua
 */
@WebServlet("/Order")
public class Order extends HttpServlet {

    @EJB
    private SongsFacadeLocal songsFacade;

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    List<Songs> listSongID;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String songID = request.getParameter("idSong");
        HttpSession session = request.getSession();

        if (request.getParameter("setSession") != null) {
            if (request.getParameter("setSession").equals("host")) {
                session.setAttribute("hostRetunr", request.getParameter("urlHost"));
            }
        }

        if (songID != null) {
            request.setAttribute("host", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
            Songs song = songsFacade.find(Integer.parseInt(songID));
            Accounts acc = accountsFacade.find(session.getAttribute("accountID"));
            request.setAttribute("songID", song);
            request.setAttribute("songPrice", song.getPrice());
            request.setAttribute("email", acc.getEmail());
            session.setAttribute("emailAccount", acc.getEmail());
            request.setAttribute("user", acc);

            // them vao ban order
            //lay gia tri acc
            String userID = session.getAttribute("accountID").toString();
            Accounts account = accountsFacade.find(Integer.parseInt(userID));

            //lay ngay go hien tai 
            Date date = new Date();
            //them vao ban order
            Orders order = new Orders();
            order.setAccountID(account);
            order.setPrice(song.getPrice());
            order.setOrderDate(null);
            order.setPaymentType(null);
            order.setStatus(1);
            order.setBankName("null");
            order.setTransactionStatus(null);
            order.setDayTrading(date);
            ordersFacade.create(order);
            // ket thuc them 

            //lat gia trị id vua duoc them vao cua ban order
            String orderIdEnd = ordersFacade.orderIdEnd();
            //khoi tao sestion  id order
            session.setAttribute("idOrder", orderIdEnd);
            request.setAttribute("id", orderIdEnd);
            //them OrderDetail
            // lay  doi tuong  order 
            Orders o = ordersFacade.find(Integer.parseInt(orderIdEnd));
            //lay doi duong song 
            Songs songs = songsFacade.find(Integer.parseInt(songID));
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrderID(o);
            orderDetails.setSongID(songs);
            orderDetails.setPrice(songs.getPrice());
            orderDetails.setDiscount(0);
            orderDetailsFacade.create(orderDetails);

            //duyen trang
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/Order.jsp");
            requestDispatcher.forward(request, response);
        }
        //add mony
        String addMony = request.getParameter("addMony");
        if (addMony!=null) {
            if (addMony.equals("add")) {
                request.setAttribute("host", request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath());
                String mony = request.getParameter("mony");
                String host = request.getParameter("urlHost").replace("#", "");
                session.setAttribute("hostRetunr", host);
                //lay gia tri acc
                String userID = session.getAttribute("accountID").toString();
                Accounts account = accountsFacade.find(Integer.parseInt(userID));
                //set email
                session.setAttribute("emailAccount", account.getEmail());
                //khoi tao order
                //lay ngay go hien tai 
                Date date = new Date();
                //them vao ban order
                Orders order = new Orders();
                order.setAccountID(account);
                order.setPrice(Integer.parseInt(mony));
                order.setOrderDate(null);
                order.setPaymentType(null);
                order.setStatus(5);
                order.setBankName("null");
                order.setTransactionStatus(null);
                order.setDayTrading(date);
                ordersFacade.create(order);
                // ket thuc them 

                //truy du lieulen jsp
                // truyen thong tin nguoi dung 
                request.setAttribute("user", account);
                //lat gia trị id vua duoc them vao cua ban order
                String orderIdEnd = ordersFacade.orderIdEnd();
                //khoi tao sestion  id order
                session.setAttribute("idOrder", orderIdEnd);
                request.setAttribute("id", orderIdEnd);
                //truyen thong tin song = nulll
                request.setAttribute("songID", "null");
                //truyen so tien can nap len 
                request.setAttribute("mony", mony);
                session.setAttribute("mony", mony);
                //set kieu thanh toán cho addmony
                session.setAttribute("typeAddMony", true);
                //duyen trang
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/Order.jsp");
                requestDispatcher.forward(request, response);
            }
        }

    }
//    String typeName = request.getParameter("typename");
//    String des = request.getParameter("description");
//    TypeOfSong song = new TypeOfSong();
//
//    song.setTypeName (typeName);
//
//    song.setDescription (des);
//
//    typeOfSongFacade.create (song);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
