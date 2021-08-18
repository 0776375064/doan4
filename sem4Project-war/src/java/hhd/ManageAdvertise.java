/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Accounts;
import entities.Advertise;
import entities.Albums;
import entities.Artists;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
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
import sessionbean.AdvertiseFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/admin-advertise")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ManageAdvertise extends HttpServlet {

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private AdvertiseFacadeLocal advertiseFacade;

    public static final String UPLOAD_DIR = "storage\\banner";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        HttpSession session = request.getSession(true);
        if (session.getAttribute("admin") == null) {
            response.sendRedirect(applicationPath + "/");
        }
        if (todo.equals("list")) {
            List<Advertise> list = advertiseFacade.findAll();
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/advertise/list.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Advertise advertise = advertiseFacade.find(id);
            if (advertise == null) {
                response.sendRedirect(applicationPath + "/admin-advertise?do=list");
            }
            request.setAttribute("advertise", advertise);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/advertise/edit.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Advertise objAdvertise = advertiseFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();
            if (objAdvertise == null) {
                obj.put("msg", "error");
            } else {
                advertiseFacade.remove(objAdvertise);
                obj.put("msg", "success");
            }
            out.print(obj.toString());
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
        if (submit.equals("add")) {
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String url = request.getParameter("url");
            Part filePart = request.getPart("thumbnail");
            int accountID = Integer.parseInt(session.getAttribute("accountID").toString());
            if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("thumbnail") && name != null && description != null && url != null) {
                String extension = "";
                String fileName = filePart.getSubmittedFileName();
                int i = fileName.lastIndexOf('.');
                if (i > 0) {
                    extension = fileName.substring(i + 1);
                    extension = extension.toLowerCase();
                }
                String newName = names + "." + extension;
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
                        Accounts objAccount = accountsFacade.find(accountID);
                        Advertise objAdvertise = new Advertise();
                        objAdvertise.setAdvertiseName(name);
                        objAdvertise.setThumbnail(newName);
                        objAdvertise.setDescription(description);
                        objAdvertise.setUrl(url);
                        objAdvertise.setAccountID(objAccount);
                        advertiseFacade.create(objAdvertise);

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
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/advertise/create.jsp");
                    requestDispatcher.forward(request, response);
                }
                response.sendRedirect(app + "/admin/advertise/create.jsp?msg=success");
            }
        } // end add
        else if (submit.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String url = request.getParameter("url");
            Advertise objAdvertise = advertiseFacade.find(id);
            Part filePart = request.getPart("thumbnail");
            if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("thumbnail") && name != null && description != null && url != null) {
                String extension = "";
                String fileName = filePart.getSubmittedFileName();
                int i = fileName.lastIndexOf('.');
                if (i > 0) {
                    extension = fileName.substring(i + 1);
                    extension = extension.toLowerCase();
                }
                String newName = names + "." + extension;
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
                        objAdvertise.setThumbnail(newName);

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
            objAdvertise.setAdvertiseName(name);
            objAdvertise.setDescription(description);
            objAdvertise.setUrl(url);
            advertiseFacade.edit(objAdvertise);
            // if upload
            request.setAttribute("advertise", objAdvertise);
            request.setAttribute("update", "success");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/advertise/edit.jsp");
            requestDispatcher.forward(request, response);
        }
        // end edit

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
