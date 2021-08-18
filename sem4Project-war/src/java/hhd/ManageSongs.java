/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Albums;
import entities.ArtistInSong;
import entities.Artists;
import entities.SongLanguage;
import entities.Songs;
import entities.Suppliers;
import entities.TypeOfSong;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import sessionbean.AlbumsFacadeLocal;
import sessionbean.ArtistInSongFacadeLocal;
import sessionbean.ArtistsFacadeLocal;
import sessionbean.SongLanguageFacadeLocal;
import sessionbean.SongsFacadeLocal;
import sessionbean.SuppliersFacadeLocal;
import sessionbean.TypeOfSongFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/admin-songs")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ManageSongs extends HttpServlet {

    @EJB
    private ArtistInSongFacadeLocal artistInSongFacade;

    @EJB
    private AlbumsFacadeLocal albumsFacade;

    @EJB
    private ArtistsFacadeLocal artistsFacade;

    @EJB
    private SuppliersFacadeLocal suppliersFacade;

    @EJB
    private TypeOfSongFacadeLocal typeOfSongFacade;

    @EJB
    private SongLanguageFacadeLocal songLanguageFacade;

    @EJB
    private SongsFacadeLocal songsFacade;

    public static final String UPLOAD_DIR = "storage\\song";
    public static final String UPLOAD_ADUIO_DIR = "storage\\audio";

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
        String app = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        int page = 1;
        if (todo.equals("list")) {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            List<Songs> list = songsFacade.Pagination(page);
            request.setAttribute("list", list);
            request.setAttribute("countPage", songsFacade.countPage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/songs/list.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Songs obj = songsFacade.find(id);
            // get select options
            List<Suppliers> listSuppliers = new ArrayList();
            List<TypeOfSong> listType = new ArrayList();
            List<SongLanguage> listLanguages = new ArrayList();
            // get value
            listSuppliers = suppliersFacade.findAll();
            listType = typeOfSongFacade.findAll();
            listLanguages = songLanguageFacade.findAll();
            if (obj == null) {
                response.sendRedirect(app + "/admin-songs?do=list");

            } else {
                Artists objArtist = artistsFacade.find(obj.getArtistID().getArtistID());
                request.setAttribute("artist", objArtist);
                request.setAttribute("song", obj);
                request.setAttribute("suppliers", listSuppliers);
                request.setAttribute("languages", listLanguages);
                request.setAttribute("types", listType);
                request.setAttribute("albums", artistsFacade.getAlbums(objArtist.getArtistID()));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dates = formatter.format(obj.getRelease());
                request.setAttribute("date", dates);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/songs/edit.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("create")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Artists artist = artistsFacade.find(id);
            if (artist == null) {
                response.sendRedirect(app + "/admin-artist?do=list");
            } else {
                List<Suppliers> listSuppliers = new ArrayList();
                List<TypeOfSong> listType = new ArrayList();
                List<SongLanguage> listLanguages = new ArrayList();
                // get value
                listSuppliers = suppliersFacade.findAll();
                listType = typeOfSongFacade.findAll();
                listLanguages = songLanguageFacade.findAll();
                request.setAttribute("artist", artist);
                request.setAttribute("suppliers", listSuppliers);
                request.setAttribute("languages", listLanguages);
                request.setAttribute("types", listType);
                request.setAttribute("albums", artistsFacade.getAlbums(id));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/songs/create.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Songs obj = songsFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            int count = songsFacade.countOrders(id);

            if (count == 0) {
                songsFacade.remove(obj);
                json.put("msg", "success");
            } else {
                json.put("msg", "error");
            }
            out.print(json.toString());
        } else if (todo.equals("search")) {
            String s = request.getParameter("s");
            List<Songs> list = new ArrayList();
            if (!s.isEmpty()) {
                list = songsFacade.findByNames(s);
            }
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/songs/search.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("statistic")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.println(LocalDate.now().format(formatter));
            String today = LocalDate.now().format(formatter);
            request.setAttribute("list", songsFacade.getStatistic(today, today));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/songs/statistic.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("report")) {
            String fdate = request.getParameter("fdate");
            String tdate = request.getParameter("tdate");
            if (fdate != null && tdate != null) {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    //out.println("Hello " + fdate + " - " + tdate);
                    for (Object[] song : songsFacade.getStatistic(fdate, tdate)) {
                        Songs objSong = songsFacade.find(song[0]);
                        int sales = (int) song[1];
                        out.println("<tr>");
                        out.println("<td class='avatar'>");
                        out.println("<img src=\"" + app + "/storage/song/" + objSong.getThumbnail() + "\"/><br/>");
                        out.println("<span>" + objSong.getSongName() + "</span>");
                        out.println("</td>");
                        out.println("<td>" + objSong.getArtistID().getNickname() + "</td>");
                        out.println("<td>" + objSong.getTypeID().getTypeName() + "</td>");
                        out.println("<td>" + objSong.getLangID().getLangName() + "</td>");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String fmDate = formatter.format(objSong.getRelease());
                        out.println("<td>" + fmDate + "</td>");
                        if (objSong.getTypeAlbum() == 0) {
                            out.println("<td>Single</td>");
                        } else {
                            out.println("<td>" + objSong.getAlbumID().getAlbumName() + "</td>");
                        }
                        out.println("<td>" + objSong.getPrice() + " $</td>");
                        out.println("<td><span class='center'>" + sales + "</span></td>");
                        out.println("</tr>");
                    }
                }
            }
        } // end report
        else if (todo.equals("getDate")) {
            String fdate = request.getParameter("fdate");
            String tdate = request.getParameter("tdate");
            List<Integer> data = new ArrayList();
            if (fdate != null && tdate != null) {
                List<String> list = songsFacade.getDateOrder(fdate, tdate);
                for (String text : list) {
                    data.add(songsFacade.countOrdersInDay(text));
                }
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                JSONObject json = new JSONObject();
                json.put("list", list);
                json.put("value", data);
                out.print(json);
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
        String app = request.getContextPath(); // get real path
        String applicationPath = request.getServletContext().getRealPath(""); // get real path
        String uploadPath = applicationPath + File.separator + UPLOAD_DIR;
        // get type of submit, create, edit, ...
        String submit = request.getParameter("submit");
        // check type
        if (submit.equals("add")) {
            System.out.println("add");
            boolean flagThumb = true;
            boolean flagPath = true;
            Part filePart = request.getPart("avatar"); // images
            Part fileAudio = request.getPart("audio"); // audio
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String lyrics = request.getParameter("lyrics");
            String loai = request.getParameter("loai");
            int Price = Integer.parseInt(request.getParameter("price"));
            Date release = Date.valueOf(request.getParameter("date"));
            int languageID = Integer.parseInt(request.getParameter("language"));
            int type = Integer.parseInt(request.getParameter("type")); // soft of music
            int supID = Integer.parseInt(request.getParameter("supplier")); // supplier ID
            Songs song = new Songs();
            Artists objArtist = artistsFacade.find(id);
            song.setArtistID(objArtist);
            song.setSongName(name);
            song.setDescription(description);
            song.setLyrics(lyrics);
            song.setPrice(Price);
            song.setRelease(release);
            SongLanguage objLang = songLanguageFacade.find(languageID);
            song.setLangID(objLang);
            TypeOfSong objType = typeOfSongFacade.find(type);
            song.setTypeID(objType);
            Suppliers objSup = suppliersFacade.find(supID);
            song.setSupplierID(objSup);
            song.setViews(0);
            if (loai.equals("single")) {
                song.setTypeAlbum(0);
            } else {
                int albumID = Integer.parseInt(request.getParameter("album")); // supplier ID
                Albums objAl = albumsFacade.find(albumID);
                song.setTypeAlbum(1);
                song.setAlbumID(objAl);
            }
            if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("avatar")) {
                int names = (int) new java.util.Date().getTime();
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
                    System.out.println("upload " + newName);
                    try {
                        out = new FileOutputStream(new File(uploadPath + File.separator + newName));
                        filecontent = filePart.getInputStream();
                        int read = 0;
                        final byte[] bytes = new byte[1024];
                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                        song.setThumbnail(newName); // set thumbnail
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
                    flagThumb = false;
                }
            } else {
                song.setThumbnail("cd.png");
            }
            if (fileAudio != null && fileAudio.getSize() != 0 && fileAudio.getName().equals("audio")) {
                int names = (int) new java.util.Date().getTime();
                String extension = "";
                String fileName = fileAudio.getSubmittedFileName();
                int i = fileName.lastIndexOf('.');
                if (i > 0) {
                    extension = fileName.substring(i + 1);
                    extension = extension.toLowerCase();
                }
                String newName = names + "." + extension;
                OutputStream out = null;
                InputStream filecontent = null;
                if (extension.equals("mp3")) {
                    System.out.println("upload audio " + newName);
                    try {
                        String uploadPath1 = applicationPath + File.separator + UPLOAD_ADUIO_DIR;
                        out = new FileOutputStream(new File(uploadPath1 + File.separator + newName));
                        filecontent = fileAudio.getInputStream();
                        int read = 0;
                        final byte[] bytes = new byte[1024];
                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                        song.setPath(newName); // set thumbnail
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
                    flagPath = false;
                }
            }
            if (flagPath && flagThumb) {
                songsFacade.create(song);
                ArtistInSong artistInSong = new ArtistInSong();
                Artists objArtists = artistsFacade.find(id);
                artistInSong.setArtistID(objArtists);
                artistInSong.setSongID(song);
                artistInSong.setRole("main");
                artistInSongFacade.create(artistInSong);
                response.sendRedirect(app + "/admin-artist?do=song&id=" + id + "&msg=success");
            } else {
                response.sendRedirect(app + "/admin-songs?do=create&id=" + id + "&msg=error");
            }
        } else if (submit.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Songs song = songsFacade.find(id);
            if (song == null) {
                response.sendRedirect(applicationPath + "/admin-songs?do=list");
            } else {
                System.out.println("edit song");
                Part filePart = request.getPart("avatar");
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                String lyrics = request.getParameter("lyrics");
                String loai = request.getParameter("loai");
                int Price = Integer.parseInt(request.getParameter("price"));
                Date release = Date.valueOf(request.getParameter("date"));
                int languageID = Integer.parseInt(request.getParameter("language"));
                int type = Integer.parseInt(request.getParameter("type")); // soft of music
                int supID = Integer.parseInt(request.getParameter("supplier")); // supplier ID
                song.setSongName(name);
                song.setDescription(description);
                song.setLyrics(lyrics);
                song.setPrice(Price);
                song.setRelease(release);
                SongLanguage objLang = songLanguageFacade.find(languageID);
                song.setLangID(objLang);
                TypeOfSong objType = typeOfSongFacade.find(type);
                song.setTypeID(objType);
                Suppliers objSup = suppliersFacade.find(supID);
                song.setSupplierID(objSup);
                if (loai.equals("single")) {
                    song.setTypeAlbum(0);
                } else {
                    int albumID = Integer.parseInt(request.getParameter("album")); // supplier ID
                    Albums objAl = albumsFacade.find(albumID);
                    song.setTypeAlbum(1);
                    song.setAlbumID(objAl);
                }
                if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("avatar")) {
                    int names = (int) new java.util.Date().getTime();
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
                        System.out.println("upload " + newName);
                        try {
                            out = new FileOutputStream(new File(uploadPath + File.separator + newName));
                            filecontent = filePart.getInputStream();
                            int read = 0;
                            final byte[] bytes = new byte[1024];
                            while ((read = filecontent.read(bytes)) != -1) {
                                out.write(bytes, 0, read);
                            }
                            song.setThumbnail(newName); // set thumbnail
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
                }
                songsFacade.edit(song);
                response.sendRedirect(app + "/admin-songs?do=edit&id=" + id + "&msg=success");
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
