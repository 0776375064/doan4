/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Songs;
import entities.Suppliers;
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
import sessionbean.SuppliersFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/admin-supplier")
public class ManageSupplier extends HttpServlet {

    @EJB
    private SuppliersFacadeLocal suppliersFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        if (todo.equals("list")) {
            List<Suppliers> list = suppliersFacade.findAll();
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/supplier/list.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Suppliers obj = suppliersFacade.find(id);
            if (obj == null) {
                response.sendRedirect(applicationPath + "/admin-supplier?do=list");

            } else {
                request.setAttribute("supplier", obj);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/supplier/edit.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        else if (todo.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Suppliers objSuppliers = suppliersFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();
            if (objSuppliers == null) {
                obj.put("msg", "error");
            } else {
                int count = suppliersFacade.countSongInSup(id);
                if (count == 0) {
                    suppliersFacade.remove(objSuppliers);
                    obj.put("msg", "success");
                } else {
                    obj.put("msg", "error");
                }
            }
            out.print(obj.toString());
        }
        else if (todo.equals("getList")) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Songs> list = suppliersFacade.getSongInSup(id);
                for (Songs song : list) {
                    out.println("<li class=\"any-item\">");
                    out.println("<div class=\"any-item-avatar\">");
                    out.println(" <a href=\"#\"><img src=\"storage/song/" + song.getThumbnail() + "\" alt=\"\"></a>");
                    out.println("</div>");
                    out.println(" <div class=\"any-item-profile\">");
                    out.println(" <div class=\"any-profile-user\"><a href=\"" + song.getSongID() + "\">" + song.getSongName() + "</a></div>");
                    out.println("<div class=\"any-profile-name\">" + song.getArtistID().getNickname() + "</div>");
                    out.println(" </div>");
                    out.println("<div class=\"any-item-btn\">");
                    out.println("<a href=\"SongDetail?songID=" + song.getSongID() + "\" target=\"_blank\" class=\"btn-follow-sm follow-request\">View details</a>");
                    out.println("</div>");
                    out.println("</li>");
                }
                if (list.size() == 0) {
                    out.println("<li class=\"center\">No data available!</li>");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        // get type of submit, create, edit, ...
        String submit = request.getParameter("submit");
        if (submit.equals("add")) {
            String name = request.getParameter("compayName");
            String contact = request.getParameter("contactName");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String fax = request.getParameter("fax");
            String homepage = request.getParameter("homepage");
            Suppliers suppliers = new Suppliers();
            suppliers.setCompanyName(name);
            suppliers.setContactName(contact);
            suppliers.setAddress(address);
            suppliers.setPhone(phone);
            suppliers.setFax(fax);
            suppliers.setHompage(homepage);
            suppliersFacade.create(suppliers);
            response.sendRedirect(applicationPath + "/admin/supplier/create.jsp?msg=success");
        } else if (submit.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Suppliers obj = suppliersFacade.find(id);
            if (obj == null) {
                response.sendRedirect(applicationPath + "/admin-supplier?do=list");
            } else {
                String name = request.getParameter("compayName");
                String contact = request.getParameter("contactName");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");
                String homepage = request.getParameter("homepage");
                obj.setCompanyName(name);
                obj.setContactName(contact);
                obj.setAddress(address);
                obj.setPhone(phone);
                obj.setFax(fax);
                obj.setHompage(homepage);
                suppliersFacade.edit(obj);
                request.setAttribute("supplier", obj);
                request.setAttribute("msg", "success");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/supplier/edit.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
