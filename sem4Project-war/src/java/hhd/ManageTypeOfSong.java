/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Songs;
import entities.TypeOfSong;
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
import javax.servlet.http.Part;
import org.json.JSONObject;
import sessionbean.TypeOfSongFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/admin-typesong")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ManageTypeOfSong extends HttpServlet {

    @EJB
    private TypeOfSongFacadeLocal typeOfSongFacade;

    public static final String UPLOAD_DIR = "storage\\genre";

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
        if (todo.equals("list")) {
            List<TypeOfSong> list = typeOfSongFacade.findAll();
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/typesong/list.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            int typeID = Integer.parseInt(request.getParameter("id"));
            TypeOfSong obj = typeOfSongFacade.find(typeID);
            request.setAttribute("typeOfSong", obj);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/typesong/edit.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            TypeOfSong objTypeOfSong = typeOfSongFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();
            if (objTypeOfSong == null) {
                obj.put("msg", "notfound");
            } else {
                int count = typeOfSongFacade.countSongSoftOf(id);
                if (count == 0) {
                    typeOfSongFacade.remove(objTypeOfSong);
                    obj.put("msg", "success");
                } else {
                    obj.put("msg", "error");
                }
            }
            out.print(obj.toString());
        } else if (todo.equals("getList")) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Songs> list = typeOfSongFacade.getListSongInType(id);
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
        String app = request.getServletContext().getRealPath(""); // get real path
        String uploadPath = app + File.separator + UPLOAD_DIR;
        // get type of submit, create, edit, ...
        String submit = request.getParameter("submit");
        int names = (int) (new java.util.Date().getTime() / 1000);
        // check type
        if (submit.equals("add")) {
            String typeName = request.getParameter("typename");
            String des = request.getParameter("description");
            Part filePart = request.getPart("thumbnail");
            if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("thumbnail") && typeName != null && des != null) {
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
                        TypeOfSong song = new TypeOfSong();
                        song.setTypeName(typeName);
                        song.setDescription(des);
                        song.setThumbnail(newName);
                        typeOfSongFacade.create(song);

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
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/typesong/create.jsp");
                    requestDispatcher.forward(request, response);
                }
                response.sendRedirect(applicationPath + "/admin/typesong/create.jsp?msg=success");
            }
        } else if (submit.equals("edit")) {
            int typeID = Integer.parseInt(request.getParameter("typeID"));
            TypeOfSong obj = typeOfSongFacade.find(typeID);
            if (obj == null) {
                response.sendRedirect(applicationPath + "/admin-typesong?do=list");
            } else {
                String typeName = request.getParameter("typename");
                String des = request.getParameter("description");
                Part filePart = request.getPart("thumbnail");
                if (typeName != null && des != null) {
                    obj.setTypeName(typeName);
                    obj.setDescription(des);
                }
                if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("thumbnail")) {
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
                            obj.setThumbnail(newName);

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
                }
                typeOfSongFacade.edit(obj);
                request.setAttribute("typeOfSong", obj);
                request.setAttribute("update", "success");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/typesong/edit.jsp");
                requestDispatcher.forward(request, response);
            }
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
