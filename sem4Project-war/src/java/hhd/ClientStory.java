/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Accounts;
import entities.Songs;
import entities.Stories;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbean.AccountsFacadeLocal;
import sessionbean.SongsFacadeLocal;
import sessionbean.StoriesFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/ClientStory")
public class ClientStory extends HttpServlet {

    @EJB
    private SongsFacadeLocal songsFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private StoriesFacadeLocal storiesFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        HttpSession session = request.getSession(true);
        // search
        if (todo.equals("song")) {
            String name = request.getParameter("name");
            List<Songs> list = songsFacade.findByNames(name);
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                for (Songs obj : list) {
                    out.println("<tr id='" + obj.getSongID() + "'>");
                    out.println("<td><img class='is-50-50 is-rounded' src='" + applicationPath + "/storage/song/" + obj.getThumbnail() + "'></td>");
                    out.println("<td>");
                    out.println("<div> <p class='title'>" + obj.getSongName() + "</p><span>" + obj.getArtistID().getNickname() + "</span></div>");
                    out.println("</td>");
                    out.println("<td class='form-check'><i class='bx bx-check'></i></td>");
                    out.println("</tr>");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("submit");
        HttpSession session = request.getSession(true);
        if (todo.equals("create")) {
            if (request.getParameter("msg") != null) {
                try (PrintWriter out = response.getWriter()) {
                    String msg = request.getParameter("msg");
                    int feeling = Integer.parseInt(request.getParameter("feeling"));
                    int songID = Integer.parseInt(request.getParameter("song"));
                    if (session.getAttribute("accountID") != null) {
                        int userID = Integer.parseInt(session.getAttribute("accountID").toString());
                        Stories story = new Stories();
                        Accounts account = accountsFacade.find(userID);
                        story.setFeeling(feeling);
                        story.setAccountID(account);
                        story.setMessage(msg);
                        story.setSongID(songID);
                        int dates = (int) (new Date().getTime() / 1000);
                        story.setCreated(dates);
                        storiesFacade.create(story);
                        if (story.getStoryID() != null) {
                            out.println("<div class='media'>");
                            out.println("<div class='media-left'>");
                            out.println(" <a href=\"\"><img class=\"is-50-50 is-rounded\" src=\'"+applicationPath+"/storage/profile/"+account.getAvatar()+"'></a>");
                            out.println("</div>");
                            out.println("<div class='media-content'>");
                            out.println("<div class='media-title'>");
                            out.println(" <a href=\"\">" + story.getAccountID().getUsername() + "</a>");
                            if (story.getFeeling() == 1) {
                                out.println("<span class='media-react'> - Feeling happy <img src='" + applicationPath + "/storage/images/smile.svg'></span>");
                            }
                            if (story.getFeeling() == 2) {
                                out.println("<span class='media-react'> - Feeling sad <img src='" + applicationPath + "/storage/images/sad.svg'></span>");
                            }
                            if (story.getFeeling() == 3) {
                                out.println("<span class='media-react'> - Feeling angry <img src='" + applicationPath + "/storage/images/angry.svg'></span>");
                            }
                            if (story.getFeeling() == 4) {
                                out.println("<span class='media-react'> - Feeling excited <img src='" + applicationPath + "/storage/images/heart.svg'></span>");
                            }
                            if (story.getFeeling() == 5) {
                                out.println("<span class='media-react'> - Feeling scary <img src='" + applicationPath + "/storage/images/omg.svg'></span>");
                            }
                            out.println("</div>");
                            out.println("<div class='media-subtitle light-text text-message'>");
                            // media play
                            Songs song = songsFacade.find(songID);
                            if (song != null) {
                                out.println("<div class='media-play'>");
                                out.println("<span>Shared with</span>");
                                out.println("<a href=\"\"><i class='bx bx-headphone bx-tada'></i> " + song.getSongName() + " - " + song.getArtistID().getNickname() + "</a>");
                                out.println("</div>");
                            }
                            //end
                            out.println("<p>" + story.getMessage() + "</p>");
                            out.println("</div>");
                            out.println("<div class='diary-times'>");
                            out.println("<i class='bx bx-stopwatch'></i> <i>Just a moment</i>");
                            out.println("</div>");
                            out.println("</div>");
                            out.println("</div>");
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
