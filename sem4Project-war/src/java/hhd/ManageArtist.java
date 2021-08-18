/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Artists;
import entities.Songs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
import sessionbean.ArtistInSongFacadeLocal;
import sessionbean.ArtistsFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/admin-artist")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ManageArtist extends HttpServlet {

    @EJB
    private ArtistInSongFacadeLocal artistInSongFacade;

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    public static final String UPLOAD_DIR = "storage\\artist";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        int page = 1;
        if (todo.equals("list")) {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            List<Artists> list = artistsFacade.Pagination(page);
            request.setAttribute("list", list);
            request.setAttribute("countPage", artistsFacade.countPage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/artist/list.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists obj = artistsFacade.find(id);
            if (obj == null) {
                response.sendRedirect(applicationPath + "/admin-artist?do=list");
            } else {
                request.setAttribute("artist", obj);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/artist/edit.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("song")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists obj = artistsFacade.find(id);
            if (obj == null) {
                response.sendRedirect(applicationPath + "/admin-artist?do=list");
            } else {
                request.setAttribute("artist", obj.getNickname());
                request.setAttribute("id", id);
                request.setAttribute("songs", artistInSongFacade.getAllSongs(id));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/artist/song.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists objArtists = artistsFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();
            int countAlbum = artistsFacade.countAlbums(id);
            int countSong = artistsFacade.countSongs(id);
            int countWork = artistsFacade.countArtistInSongs(id);
            if (countAlbum == 0 && countSong == 0 && countWork == 0) {
                artistsFacade.remove(objArtists);
                obj.put("msg", "success");
            } else {
                obj.put("msg", "error");
            }
            out.print(obj.toString());
        } else if (todo.equals("search")) {
            String s = request.getParameter("s");
            List<Artists> list = new ArrayList();
            if (!s.isEmpty()) {
                list = artistsFacade.findByNames(s);
            }
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/artist/search.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("getList")) {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Songs> list = artistsFacade.getSongs(id);
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
        String applicationPath = request.getServletContext().getRealPath(""); // get real path
        String app = request.getContextPath(); // get real path
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        String submit = request.getParameter("submit");
        // if add
        int names = (int) new Date().getTime();
        if (submit.equals("add")) {
            Part filePart = request.getPart("avatar");
            String nickname = request.getParameter("nickname");
            String fullname = request.getParameter("fullname");
            String birthday = request.getParameter("birthday");
            String nationality = request.getParameter("nationality");
            String description = request.getParameter("description");
            String achievement = request.getParameter("achievement");
            if (nickname != null && fullname != null && birthday != null && nationality != null && description != null && achievement != null) {
                Artists artist = new Artists();
                artist.setNickname(nickname);
                artist.setFullname(fullname);
                artist.setBirthday(birthday);
                artist.setNationality(nationality);
                artist.setDescription(description);
                artist.setAchievement(achievement);
                // neu up anh
                if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("avatar")) {
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
                            artist.setAvatar(newName);

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
                        artist.setAvatar("default.png");
                    }
                } else {
                    artist.setAvatar("default.png");
                }
                artistsFacade.create(artist);
            }
            response.sendRedirect(app + "/admin/artist/create.jsp?msg=success");
        } // if edit
        else if (submit.equals("edit")) {
            try {
                Part filePart = request.getPart("avatar");
                int id = Integer.parseInt(request.getParameter("id"));
                Artists artist = artistsFacade.find(id);
                String nickname = request.getParameter("nickname");
                String fullname = request.getParameter("fullname");
                String birthday = request.getParameter("birthday");
                String nationality = request.getParameter("nationality");
                String description = request.getParameter("description");
                String achievement = request.getParameter("achievement");
                artist.setNickname(nickname);
                artist.setFullname(fullname);
                artist.setBirthday(birthday);
                artist.setNationality(nationality);
                artist.setDescription(description);
                artist.setAchievement(achievement);
                if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("avatar")) {
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
                            artist.setAvatar(newName);
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
                        request.setAttribute("artist", artist);
                        request.setAttribute("msg", "avatar");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/artist/edit.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
                artistsFacade.edit(artist);
                request.setAttribute("artist", artist);
                request.setAttribute("msg", "success");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/artist/edit.jsp");
                requestDispatcher.forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (submit.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists obj = artistsFacade.find(id);
            artistsFacade.remove(obj);
            response.sendRedirect(app + "/admin-artist?do=list");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
