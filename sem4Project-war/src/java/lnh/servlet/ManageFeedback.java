/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh.servlet;

import entities.Feedbacks;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.sql.Date;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sessionbean.FeedbacksFacadeLocal;

/**
 *
 * @author HP
 */
@WebServlet("/admin-feedback")
public class ManageFeedback extends HttpServlet {

    
    @EJB
    private FeedbacksFacadeLocal feedbacksFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String todo = request.getParameter("do");
        if(todo.equals("list")){
            List<Feedbacks> list = feedbacksFacade.findAll();
            request.setAttribute("list",list );
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/feedback/list.jsp");
            requestDispatcher.forward(request, response);
        }
        else if(todo.equals("edit")){
            int feedbackID = Integer.parseInt(request.getParameter("id"));
            Feedbacks obj = feedbacksFacade.find(feedbackID);
            request.setAttribute("feedback", obj);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/feedback/edit.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String applicationPath = request.getContextPath(); 
        // get type of submit, create, edit, ...
        String submit = request.getParameter("submit");
        System.out.println("Type submit " + submit);
        // check type
        if(submit.equals("add")){
            String title = request.getParameter("title");
            String des = request.getParameter("description");
            String email = request.getParameter("email");
           // Date createdAt = Date.valueOf(request.getParameter("createdAt"));
            Feedbacks feed = new Feedbacks();
            
            feed.setTitle(title);
            feed.setDescription(des);
            feed.setEmail(email);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            feed.setCreatedAt(timestamp);
           // feed.setCreatedAt(createdAt);
            feedbacksFacade.create(feed);
            response.sendRedirect(applicationPath+"/admin/feedback/create.jsp?msg=success");
        }
        else if(submit.equals("edit")){
            int feedbackID = Integer.parseInt(request.getParameter("feedbackID"));
            Feedbacks obj = feedbacksFacade.find(feedbackID);
            if(obj == null){
                response.sendRedirect(applicationPath+"/admin-feedback?do=list");
            }
            else{
                String title = request.getParameter("title");
                String des = request.getParameter("description");
                String email = request.getParameter("email");
               // Date createdAt= Date.valueOf(request.getParameter("createdAt"));
                obj.setTitle(title);
                obj.setDescription(des);
                obj.setEmail(email);
               // obj.setCreatedAt(createdAt);
                 Calendar cal = Calendar.getInstance();
                 java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
                obj.setCreatedAt(timestamp);
                
                feedbacksFacade.edit(obj);
                request.setAttribute("feedback", obj);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/feedback/edit.jsp?msg=success");
                requestDispatcher.forward(request, response);
            }

        }
        else if(submit.equals("delete")){
            int feedbackID = Integer.parseInt(request.getParameter("feedbackID"));
            Feedbacks obj = feedbacksFacade.find(feedbackID);
            feedbacksFacade.remove(obj);
            response.sendRedirect(applicationPath+"/admin-feedback?do=list");
            
        }else if(submit.equals("them")){
            String title = request.getParameter("title");
            String des = request.getParameter("description");
            String email = request.getParameter("email");
           // Date createdAt = Date.valueOf(request.getParameter("createdAt"));
            Feedbacks feed = new Feedbacks();
            
            feed.setTitle(title);
            feed.setDescription(des);
            feed.setEmail(email);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            feed.setCreatedAt(timestamp);
           // feed.setCreatedAt(createdAt);
            feedbacksFacade.create(feed);
            response.sendRedirect(applicationPath+"/feedback.jsp?msg=success");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
