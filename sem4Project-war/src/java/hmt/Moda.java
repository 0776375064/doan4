/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmt;

import entities.Accounts;
import entities.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
import sessionbean.OrderDetailsFacadeLocal;
import sessionbean.OrdersFacadeLocal;
import sessionbean.SongsFacadeLocal;

/**
 *
 * @author hmtua
 */
@WebServlet("/Moda")
public class Moda extends HttpServlet {

    @EJB
    private SongsFacadeLocal songsFacade;

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private OrderDetailsFacadeLocal orderDetailsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String typeModa = request.getParameter("moda");
        if (typeModa != null) {
            //moda OrderDetail
            if (typeModa.equals("orderDetail")) {
                //lay gia tri idOrder
                String idOrderDetail = request.getParameter("idOrderDetail");
                if (idOrderDetail != null) {
                    System.out.println(idOrderDetail);
                    int idorderdetail = Integer.parseInt(idOrderDetail);
                    request.setAttribute("orderDetail", orderDetailsFacade.find(idorderdetail));
                    System.out.println(orderDetailsFacade.find(idorderdetail));
                    // tra ve trang thai thanh toan  
                    request.setAttribute("TransactionStatus", ordersFacade.getTransactionStatus(orderDetailsFacade.find(idorderdetail).getOrderID().getOrderID().toString()));
                    request.setAttribute("Note", ordersFacade.getNote(orderDetailsFacade.find(idorderdetail).getOrderID().getOrderID().toString()));
                    //tra ve trang detail order moda
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/manage/billorder/modaDetail.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
            //cap nhat trang thay order 
            if (typeModa.equals("updateStatusOrder")) {
                String idOrder = request.getParameter("idOrder");
                String trangThai = request.getParameter("trangThai");
                String note = request.getParameter("note");
                //lay doi tương order
                try {
                    // lay doi tuong cap nhat             
                    String acc = "404";
                    if (session.getAttribute("accountID").toString() != null) {
                        acc = session.getAttribute("accountID").toString();
                        Accounts a = new Accounts();
                        a= accountsFacade.find(Integer.parseInt(acc));
                        acc = a.getFullname().toString();
                    }
                    Orders order = new Orders();
                    order = ordersFacade.find(Integer.parseInt(idOrder));
                    Date date = new Date();
                    String sau = "";

                    boolean setDate = false;
                    //truy gia tri cho orrder
                    order.setAccountID(order.getAccountID());
                    order.setDayTrading(order.getDayTrading());
                    order.setPaymentType(order.getPaymentType());
                    order.setPrice(order.getPrice());
                    order.setOrderDate(order.getOrderDate());
                    //cap nhat don  thanh don da mua thanh cong 
                    if (trangThai.equals("1")) {
                        sau = "Paid";
                        order.setStatus(1);
                        order.setTransactionStatus(1);
                        setDate = true;
                        order.setOrderDate(date);
                        order.setBankName("Admin");
                    } // cap nhat thanh don hang chua thanh toan
                    else if (trangThai.equals("2")) {
                        sau = "Unpaid";
                        order.setStatus(1);
                        order.setTransactionStatus(null);
                    } //cap nhat huy don hang da mua 
                    else if (trangThai.equals("3")) {
                        sau = "Canceled";
                        order.setStatus(3);
                        order.setTransactionStatus(1);
                    } //chuyeenr down hang da mua sang dang su ly 
                    else if (trangThai.equals("4")) {
                        order.setStatus(2);
                        order.setTransactionStatus(1);
                    } // chuyen don hang chua mua sang dang su ly
                    else if (!trangThai.equals("5")) {
                        order.setStatus(2);
                        order.setTransactionStatus(null);
                    }

                    if (setDate == false) {
                        order.setOrderDate(order.getOrderDate());
                        order.setBankName(order.getBankName());
                    }
                    //tao gia ti note
                    String Note = (ordersFacade.getNote(order.getOrderID().toString()) != "null") ? ordersFacade.getNote(order.getOrderID().toString()) : "";
                    System.out.println(ordersFacade.getNote(order.getOrderID().toString()).toString());
                    String bandau = SuLy.traRaTrangThai(order.getStatus().toString(), ordersFacade.getTransactionStatus(order.getOrderID().toString()).toString());
                    order.setNote(Note + "[" + note + "/" + acc + "/" + date + "/" + bandau + "/" + sau + "]");

                    ordersFacade.edit(order);
                    // thoong tin  phan hoi   thanh cong
                    request.setAttribute("tinNhan", "Update successful");

                } catch (Exception e) {
                    // thoong tin  phan hoi  loi
                    request.setAttribute("tinNhan", "Update Failed" + e.getMessage());
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                } finally {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/retunr/retunr.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String pass = request.getParameter("pass");
        if (pass != null) {
            String passMd5 = SuLy.Createmd5(pass);
            String idAccount = session.getAttribute("accountID").toString();
            if (passMd5 != null) {
                int ketQua = accountsFacade.checkLoginOrder(idAccount, passMd5);
                if (ketQua != 0) {
                    //khoi tao kieu add cho orrder
                    Date date = new Date();
                    //lay thong tin order hien co s
                    Orders ordersEdit = new Orders();
                    ordersEdit = ordersFacade.find(Integer.parseInt(session.getAttribute("idOrder").toString()));
                    // tao edit
                    ordersEdit.setAccountID(ordersEdit.getAccountID());
                    ordersEdit.setBankName("Bcoin");
                    ordersEdit.setDayTrading(ordersEdit.getDayTrading());
                    ordersEdit.setOrderDate(date);
                    ordersEdit.setPrice(ordersEdit.getPrice());
                    ordersEdit.setPaymentType(2);
                    // null chua xac dinh 1 da thanh toan 
                    ordersEdit.setStatus(1);
                    ordersEdit.setTransactionStatus(1);
                    ordersFacade.edit(ordersEdit);

                    //khoi tao doi tuong account
                    Accounts account = new Accounts();
                    //truy van account 
                    account = accountsFacade.find(Integer.parseInt(idAccount));
                    //Laays so tien can nap 
                    int mony = Integer.parseInt(session.getAttribute("Moneyn").toString());
                    //khoi tao can nap
                    account.setUsername(account.getUsername());
                    account.setPassword(account.getPassword());
                    account.setFullname(account.getFullname());
                    account.setEmail(account.getEmail());
                    account.setAddress(account.getAddress());
                    account.setFavourite(account.getFavourite());
                    account.setMoney(account.getMoney() - mony);
                    account.setPhone(account.getPhone());
                    account.setGender(account.getGender());
                    account.setAvatar(account.getAvatar());
                    account.setRole(account.getRole());
                    account.setStatus(account.getStatus());
                    accountsFacade.edit(account);
                }
                String thongBao = ketQua == 0 ? "Incorrect password" : "Successful";
                request.setAttribute("ThongTin", thongBao);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("musics/retunr/checkPass.jsp");
                requestDispatcher.forward(request, response);
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
