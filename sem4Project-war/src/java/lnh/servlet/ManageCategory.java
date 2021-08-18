/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh.servlet;


import entities.Categories;
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
import org.json.JSONObject;
import sessionbean.CategoriesFacadeLocal;

/**
 *
 * @author HP
 */
@WebServlet("/admin-categories")
public class ManageCategory extends HttpServlet {

    
    @EJB
    private CategoriesFacadeLocal categoriesFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

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
            List<Categories> list = categoriesFacade.findAll();
            request.setAttribute("list",list );
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/categories/list.jsp");
            requestDispatcher.forward(request, response);
        }
        else if(todo.equals("edit")){
            int categoryID = Integer.parseInt(request.getParameter("id"));
            Categories obj = categoriesFacade.find(categoryID);
            request.setAttribute("categories", obj);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/categories/edit.jsp");
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
        String applicationPath = request.getContextPath(); // get real path
        // get type of submit, create, edit, ...
        String submit = request.getParameter("submit");
        System.out.println("Type submit " + submit);
        // check type
        if(submit.equals("add")){
            String categoryName = request.getParameter("categoryname");
            String des = request.getParameter("description");
            int stat = Integer.parseInt(request.getParameter("status"));
            Categories song = new Categories();
            song.setCategoryName(categoryName);
            song.setDescription(des);
            song.setStatus(stat);
            
            categoriesFacade.create(song);
            response.sendRedirect(applicationPath+"/admin/categories/create.jsp?msg=success");
        }
        else if(submit.equals("edit")){
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            Categories obj = categoriesFacade.find(categoryID);
            if(obj == null){
                response.sendRedirect(applicationPath+"/admin-categories?do=list");
            }
            else{
                String categoryName = request.getParameter("categoryname");
                String des = request.getParameter("description");
                int stat = Integer.parseInt(request.getParameter("status"));
                obj.setCategoryName(categoryName);
                obj.setDescription(des);
                obj.setStatus(stat);
                
                categoriesFacade.edit(obj);
                request.setAttribute("categories", obj);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/categories/edit.jsp?msg=success");
                requestDispatcher.forward(request, response);
            }

        }
      /*else if(submit.equals("delete")){
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            Categories obj = categoriesFacade.find(categoryID);
            
            categoriesFacade.remove(obj);
           
            response.sendRedirect(applicationPath+"/admin-categories?do=list");
        }
        */
        else if (submit.equals("delete")) {
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            Categories objcate = categoriesFacade.find(categoryID);
            
            if (objcate == null) {
            } else {
                int count = categoriesFacade.getNewsCategory(categoryID);
                if (count == 0) {
                    categoriesFacade.remove(objcate);
                    
                } else {
                    
                }
            }
            //out.print(obj.toString());
         response.sendRedirect(applicationPath+"/admin-categories?do=list");
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
