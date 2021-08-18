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
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbean.AccountsFacadeLocal;
import sessionbean.OrdersFacadeLocal;

/**
 *
 * @author hmtua
 */
@WebServlet("/Retunr")
public class Retunr extends HttpServlet {

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private OrdersFacadeLocal ordersFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {
        HttpSession session = request.getSession();

        //tra ve trang thai giao dich
        String trangThai = request.getParameter("vpc_TxnResponseCode");
        //Giá trị của vpc_Command đã gửi đi trong file DO đượctrả lại trên file DRString
        String vpc_Command = request.getParameter("vpc_Command ");
//        //Giá trị của đối số vpc_MerchTxnRef gửi đi từ file DO trả lại trên file DR
        String vpc_MerchTxnRef = request.getParameter("vpc_MerchTxnRef");
//        //Giá trị của đối số vpc_Merchant gửi đi từ file DO được trả lại trong file DR.
        String vpc_Merchant = request.getParameter("vpc_Merchant ");
//        //Giá trị của đối số vpc_OrderInfo trên file DO được trả lại trên file DR
        String vpc_OrderInfo = request.getParameter("vpc_OrderInfo");
//        //Giá trị của đối số vpc_Amount trên file DO được trả lại trên file DR
        String vpc_Amount = request.getParameter("vpc_Amount");
//        //Thông tin này được lưu trên cổng thanh toán để ánh xạ cho phép người sử dụng thực hiện các chức năng như refund hay capture.
        String vpc_TransactionNo = request.getParameter("vpc_TransactionNo ");
//        //Mô tả lỗi giao dịch khi thanh toán
        String vcp_Message = request.getParameter("vcp_Message ");
//        //6 số định danh ngân hàng thanh toán (tương tự 6 số đầu của thẻ)
        String vpc_AcqResponseCode = request.getParameter("vpc_AcqResponseCode");
//        //Một mã code được sinh ra từ ngân hàng để chấp nhận từ chối giao dịch.
        String vpc_Authorizeld = request.getParameter("vpc_Authorizeld ");
//        //Loại the thanh toan vc mc jc ae 
        String vpc_Card = request.getParameter("vpc_Card ");
        //thanh toan 

        // thanh toan momny
        if (session.getAttribute("typeAddMony") != null) {
            if (trangThai.equals("0") && session.getAttribute("typeAddMony").toString().equals("true")) {
                //khoi tao kieu add cho orrder
                Date date = new Date();
                //lay thong tin order hien co s
                Orders ordersEdit = new Orders();
                ordersEdit = ordersFacade.find(Integer.parseInt(session.getAttribute("idOrder").toString()));
                // tao edit
                ordersEdit.setAccountID(ordersEdit.getAccountID());
                ordersEdit.setBankName("OnePay");
                ordersEdit.setDayTrading(ordersEdit.getDayTrading());
                ordersEdit.setOrderDate(date);
                ordersEdit.setPrice(ordersEdit.getPrice());
                ordersEdit.setPaymentType(1);
                // null chua xac dinh 1 da thanh toan 
                ordersEdit.setStatus(1);
                ordersEdit.setTransactionStatus(1);
                ordersFacade.edit(ordersEdit);

                //add cho account 
                //lay id account
                int idAccount = Integer.parseInt(session.getAttribute("accountID").toString());
                //khoi tao doi tuong account
                Accounts account = new Accounts();
                //truy van account 
                System.out.println(idAccount);
                account = accountsFacade.find(idAccount);
                //Laays so tien can nap 
                int mony = Integer.parseInt(session.getAttribute("mony").toString());
                //khoi tao can nap

                account.setUsername(account.getUsername());
                account.setPassword(account.getPassword());
                account.setFullname(account.getFullname());
                account.setEmail(account.getEmail());
                account.setAddress(account.getAddress());
                account.setFavourite(account.getFavourite());
                account.setMoney(account.getMoney() + mony);
                account.setPhone(account.getPhone());
                account.setGender(account.getGender());
                account.setAvatar(account.getAvatar());
                account.setRole(account.getRole());
                account.setStatus(account.getStatus());
                accountsFacade.edit(account);

                try {
                    // sênmail
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                    String emailNhan = session.getAttribute("emailAccount") == null ? session.getAttribute("emailAccount").toString() : "hmtuana18122@cusc.ctu.edu.vn";
                    String emailSend = "textmayhmt@gmail.com";
                    String pass = "Vantrancodon1";

                    int i = Integer.parseInt(vpc_Amount);
                    i /= 100;
                    String string = String.valueOf(i);
                    DecimalFormat formatter1 = new DecimalFormat("###,###,##0");
                    string = formatter1.format(Double.parseDouble(string));
                    String monny = string + " VND<br/>";
                    String madd = vpc_MerchTxnRef;
                    String congThanhToan = "OnePay";
                    String dateTT = formatter.format(date);
                    SuLy.sendMail(emailNhan, emailSend, pass, monny, congThanhToan, dateTT, madd);
                } catch (MessagingException ex) {
                    //   Logger.getLogger(Retunr.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    String hostRetunr = null;
                    if (session.getAttribute("hostRetunr").toString().indexOf("?") == -1) {
                        hostRetunr = session.getAttribute("hostRetunr").toString() + "?trangThai=" + trangThai;
                    } else {
                        hostRetunr = session.getAttribute("hostRetunr").toString() + "&trangThai=" + trangThai;
                    }
                    session.removeAttribute("emailAccount");
                    session.removeAttribute("mony");
                    session.removeAttribute("idOrder");
                    session.removeAttribute("typeAddMony");
                    session.removeAttribute("hostRetunr");
                    hostRetunr = hostRetunr.replace("#", "");
                    response.sendRedirect(hostRetunr);
                }
            }
            
        } else //thanh toan song
        if (trangThai.equals("0") && session.getAttribute("typeAddMony") == null) {
            Date date = new Date();
            // //lay thong tin order hien co s
            Orders ordersEdit = new Orders();
            ordersEdit = ordersFacade.find(Integer.parseInt(session.getAttribute("idOrder").toString()));
            // tao edit
            ordersEdit.setAccountID(ordersEdit.getAccountID());
            ordersEdit.setBankName("OnePay");
            ordersEdit.setDayTrading(ordersEdit.getDayTrading());
            ordersEdit.setOrderDate(date);
            ordersEdit.setPrice(ordersEdit.getPrice());
            ordersEdit.setPaymentType(0);
//            // null chua xac dinh 1 da thanh toan 
            ordersEdit.setStatus(1);
            ordersEdit.setTransactionStatus(1);
            ordersFacade.edit(ordersEdit);
//            ////
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String emailNhan = session.getAttribute("emailAccount") == null ? session.getAttribute("emailAccount").toString() : "hmtuana18122@cusc.ctu.edu.vn";
            String emailSend = "textmayhmt@gmail.com";
            String pass = "Vantrancodon1";
//            //
            int i = Integer.parseInt(vpc_Amount);
            i /= 100;
            String string = String.valueOf(i);
            DecimalFormat formatter1 = new DecimalFormat("###,###,##0");
            string = formatter1.format(Double.parseDouble(string));
            String monny = string + " VND<br/>";
            String madd = vpc_MerchTxnRef;
            String congThanhToan = "OnePay";
            String dateTT = formatter.format(date);
            try {
                SuLy.sendMail(emailNhan, emailSend, pass, monny, congThanhToan, dateTT, madd);
            } catch (Exception ex) {
                //    Logger.getLogger(Retunr.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                session.setAttribute("trangThaiThanhToan", trangThai);
                String hostRetunr = null;
                if (session.getAttribute("hostRetunr").toString().indexOf("?") == -1) {
                    hostRetunr = session.getAttribute("hostRetunr").toString() + "?trangThai=" + trangThai;
                } else {
                    hostRetunr = session.getAttribute("hostRetunr").toString() + "&trangThai=" + trangThai;
                }
                session.removeAttribute("emailAccount");
                session.removeAttribute("hostRetunr");
                session.removeAttribute("emailAccount");
                session.removeAttribute("idOrder");
                hostRetunr = hostRetunr.replace("#", "");
                response.sendRedirect(hostRetunr);

            }

        } else {
            String hostRetunr = null;
            if (session.getAttribute("hostRetunr").toString().indexOf("?") == -1) {
                hostRetunr = session.getAttribute("hostRetunr").toString() + "?trangThai=" + trangThai;
            } else {
                hostRetunr = session.getAttribute("hostRetunr").toString() + "&trangThai=" + trangThai;
            }
            hostRetunr = hostRetunr.replace("#", "");
            response.sendRedirect(hostRetunr);
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
