/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

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
import java.text.SimpleDateFormat;
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
import sessionbean.AlbumsFacadeLocal;
import sessionbean.ArtistsFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/admin-album")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ManageAlbum extends HttpServlet {

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @EJB
    private AlbumsFacadeLocal albumsFacade;

    public static final String UPLOAD_DIR = "storage\\album";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        int page = 1;
        if (todo.equals("list")) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            Artists artist = artistsFacade.find(id);
            request.setAttribute("list", albumsFacade.getAlbumses(id, page));
            request.setAttribute("countPage", albumsFacade.countAlbumsPage(id));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/album/list.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Albums album = albumsFacade.find(id);
            if (album == null) {
                response.sendRedirect(applicationPath + "/admin-artist?do=list");
            }
            request.setAttribute("album", album);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dates = formatter.format(album.getRelease());
            System.out.println(dates);
            request.setAttribute("date", dates);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/album/edit.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Albums album = albumsFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();
            if(album == null){
                obj.put("msg", "error");
            }
            else{
                int count = albumsFacade.countSongInAlbum(id);
                if(count == 0){
                    albumsFacade.remove(album);
                    obj.put("msg", "success");
                }
                else{
                    obj.put("msg", "error");
                }
            }
            out.print(obj.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath(""); // get real path
        String app = request.getContextPath(); // get real path
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        String submit = request.getParameter("submit");
        // if add
        int names = (int) (new java.util.Date().getTime() / 1000);
        if (submit.equals("add")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Date release = Date.valueOf(request.getParameter("release"));
            Part filePart = request.getPart("thumbnail");
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
                        Albums album = new Albums();
                        Artists artist = artistsFacade.find(id);
                        album.setArtistID(artist);
                        album.setAlbumName(name);
                        album.setRelease(release);
                        album.setThumbnail(newName);
                        albumsFacade.create(album);

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
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/album/create.jsp");
                    requestDispatcher.forward(request, response);
                }
                response.sendRedirect(app + "/admin/album/create.jsp?msg=success&id=" + id);
            }
        } // end add
        else if (submit.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            Date release = Date.valueOf(request.getParameter("release"));
            Part filePart = request.getPart("thumbnail");
            Albums album = albumsFacade.find(id);
            album.setAlbumName(name);
            album.setRelease(release);
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
                        album.setThumbnail(newName);

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
                }
                // if image
            }
            // if upload
            albumsFacade.edit(album);
            request.setAttribute("album", album);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dates = formatter.format(album.getRelease());
            request.setAttribute("date", dates);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/album/edit.jsp");
            requestDispatcher.forward(request, response);
        }
        // end edit
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
