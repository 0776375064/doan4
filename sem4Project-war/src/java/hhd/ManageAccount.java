/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Accounts;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.json.JSONObject;
import sessionbean.AccountsFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/admin-account")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ManageAccount extends HttpServlet {

    @EJB
    private AccountsFacadeLocal accountsFacade;

    public static final String UPLOAD_DIR = "storage\\profile";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        HttpSession session = request.getSession(true);
        if (session.getAttribute("admin") == null) {
            response.sendRedirect(applicationPath + "/");
        }
        if (todo.equals("block")) {
            int id = Integer.parseInt(request.getParameter("id")); // id song
            Accounts account = accountsFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            if (account != null) {
                if (account.getStatus() == 0) {
                    account.setStatus(1);
                    json.put("msg", "block");
                } else {
                    account.setStatus(0);
                    json.put("msg", "unblock");
                }
                accountsFacade.edit(account);
            }
            out.print(json);
        } else if (todo.equals("list")) {
            List<Accounts> list = accountsFacade.findAll();
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/account/list.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Accounts objAccounts = accountsFacade.find(id);
                if (objAccounts != null) {
                    request.setAttribute("account", objAccounts);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/account/edit.jsp");
                    requestDispatcher.forward(request, response);

                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String applicationPath = request.getServletContext().getRealPath(""); // get real path
        String app = request.getContextPath(); // get real path
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        String submit = request.getParameter("submit");
        int names = (int) (new java.util.Date().getTime() / 1000);
        if (submit.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String role = request.getParameter("role");
            Part filePart = request.getPart("thumbnail");
            Accounts objAccounts = accountsFacade.find(id);
            if (fullname != null && email != null && gender != null && role != null) {
                objAccounts.setFullname(fullname);
                objAccounts.setGender(gender);
                objAccounts.setRole(role);
                objAccounts.setEmail(email);
            }
            if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("thumbnail")) {
                String extension = "";
                String fileName = filePart.getSubmittedFileName();
                int i = fileName.lastIndexOf('.');
                if (i > 0) {
                    extension = fileName.substring(i + 1);
                    extension = extension.toLowerCase();
                }
                String newName = id + "." + extension;
                OutputStream out = null;
                InputStream filecontent = null;
                if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
                    try {
                        out = new FileOutputStream(new File(uploadPath + File.separator + newName));
                        filecontent = filePart.getInputStream();
                        int read = 0;
                        final byte[] bytes = new byte[1024];
                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                        objAccounts.setAvatar(newName);

                    } catch (FileNotFoundException fne) {
                        System.out.println(fne.getMessage());
                    } finally {
                        if (out != null) {
                            out.close();
                        }
                        if (filecontent != null) {
                            filecontent.close();
                        }
                    }
                } else {
                    request.setAttribute("msg", "thumbnail");
                }
                // if image
            }
            accountsFacade.edit(objAccounts);
            // if upload
            request.setAttribute("account", objAccounts);
            request.setAttribute("update", "success");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/account/edit.jsp");
            requestDispatcher.forward(request, response);
        }
        // end edit
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
