/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.SongLanguage;
import entities.Songs;
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
import sessionbean.SongLanguageFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/admin-language")
public class ManageLanguage extends HttpServlet {

    @EJB
    private SongLanguageFacadeLocal songLanguageFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        if (todo.equals("list")) {
            List<SongLanguage> list = songLanguageFacade.findAll();
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/langsong/list.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            SongLanguage obj = songLanguageFacade.find(id);
            if (obj == null) {
                response.sendRedirect(applicationPath + "/admin-language?do=list");

            } else {
                request.setAttribute("lang", obj);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/langsong/edit.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("del")) {
            int id = Integer.parseInt(request.getParameter("id"));
            SongLanguage objSongLanguage = songLanguageFacade.find(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();
            if (objSongLanguage == null) {
                obj.put("msg", "notfound");
            } else {
                int count = songLanguageFacade.countSongInLanguage(id);
                if (count == 0) {
                    songLanguageFacade.remove(objSongLanguage);
                    obj.put("msg", "success");
                } else {
                    obj.put("msg", "error");
                }
            }
            out.print(obj.toString());
        }
        else if(todo.equals("getList")){
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Songs> list = songLanguageFacade.getSongInLanguage(id);
                for(Songs song : list){
                    out.println("<li class=\"any-item\">");
                    out.println("<div class=\"any-item-avatar\">");
                    out.println(" <a href=\"#\"><img src=\"storage/song/"+song.getThumbnail()+"\" alt=\"\"></a>");
                    out.println("</div>");
                    out.println(" <div class=\"any-item-profile\">");
                    out.println(" <div class=\"any-profile-user\"><a href=\""+song.getSongID()+"\">"+song.getSongName()+"</a></div>");
                    out.println("<div class=\"any-profile-name\">"+song.getArtistID().getNickname()+"</div>");
                    out.println(" </div>");
                    out.println("<div class=\"any-item-btn\">");
                    out.println("<a href=\"SongDetail?songID="+song.getSongID()+"\" target=\"_blank\" class=\"btn-follow-sm follow-request\">View details</a>");
                    out.println("</div>");
                    out.println("</li>");
                }
                if(list.size() == 0){
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
            String name = request.getParameter("name");
            String des = request.getParameter("description");
            SongLanguage songLanguage = new SongLanguage();
            songLanguage.setLangName(name);
            songLanguage.setDescription(des);
            songLanguageFacade.create(songLanguage);
            response.sendRedirect(applicationPath + "/admin/langsong/create.jsp?msg=success");
        } else if (submit.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("typeID"));
            SongLanguage obj = songLanguageFacade.find(id);
            if (obj == null) {
                response.sendRedirect(applicationPath + "/admin-language?do=list");
            } else {
                String name = request.getParameter("name");
                String des = request.getParameter("description");
                obj.setLangName(name);
                obj.setDescription(des);
                songLanguageFacade.edit(obj);
                request.setAttribute("lang", obj);
                request.setAttribute("msg", "success");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/langsong/edit.jsp");
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
