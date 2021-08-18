/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lnh.servlet;

import entities.Accounts;
import entities.Categories;
import entities.Comment;
import entities.News;
import entities.NewsLike;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionbean.AccountsFacadeLocal;
import sessionbean.CategoriesFacadeLocal;
import sessionbean.CommentFacadeLocal;
import sessionbean.NewsFacadeLocal;
import sessionbean.NewsLikeFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/clientNews")
public class clientNews extends HttpServlet {

    @EJB
    private NewsLikeFacadeLocal newsLikeFacade;

    @EJB
    private CommentFacadeLocal commentFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private NewsFacadeLocal newsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String app = request.getContextPath();
        String todo = request.getParameter("do");
        HttpSession session = request.getSession(true);
        if (todo.equals("view")) {
            // int id = Integer.parseInt(request.getParameter("id"));
            int id = Integer.parseInt(request.getParameter("id"));
            News obj = newsFacade.find(id);
            // get select options
            if (obj == null) {
                response.sendRedirect(app + "/");

            } else {
                obj.setViews(obj.getViews() + 1);
                newsFacade.edit(obj);
                request.setAttribute("news", obj);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dates = formatter.format(obj.getCreatedAt());
                request.setAttribute("date", dates);
                List<Comment> listCmt = newsFacade.getComment(id);
                int countLike = newsFacade.countLikeNews(id);
                request.setAttribute("listCmt", listCmt);
                request.setAttribute("likes", countLike);
                if (session.getAttribute("accountID") != null) {
                    int acc = Integer.parseInt(session.getAttribute("accountID").toString());
                    if (newsFacade.checkLike(id, acc) == 0) {
                        request.setAttribute("statusLike", 0);
                    } else {
                        request.setAttribute("statusLike", 1);
                    }
                }
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("news/index.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("list")) {
            request.setAttribute("news", newsFacade.getLatest());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("category/index.jsp");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("category")) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Categories category = categoriesFacade.find(id);
                request.setAttribute("category", category);
                request.setAttribute("news", categoriesFacade.getNewsByCategory(id));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("category/list.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("like")) {
            if (session.getAttribute("accountID") != null) {
                int newID = Integer.parseInt(request.getParameter("id"));
                int acc = Integer.parseInt(session.getAttribute("accountID").toString());
                if (newsFacade.checkLike(newID, acc) == 0) {
                    News objNew = newsFacade.find(newID);
                    NewsLike objLike = new NewsLike();
                    objLike.setNewID(objNew);
                    objLike.setAccountID(acc);
                    newsLikeFacade.create(objLike);
                    response.sendRedirect(app + "/clientNews?do=view&id=" + newID);
                } else {
                    newsFacade.deleteLike(newID, acc);
                    response.sendRedirect(app + "/clientNews?do=view&id=" + newID);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String app = request.getContextPath();
        String todo = request.getParameter("submit");
        HttpSession session = request.getSession(true);
        if (todo.equals("comment")) {
            String str = request.getParameter("comment");
            int newID = Integer.parseInt(request.getParameter("newID"));
            if (session.getAttribute("accountID") != null) {
                int acc = Integer.parseInt(session.getAttribute("accountID").toString());
                Comment cmt = new Comment();
                Accounts account = accountsFacade.find(acc);
                News objNew = newsFacade.find(newID);
                cmt.setAccountID(account);
                cmt.setMessage(str);
                cmt.setNewID(objNew);
                int dates = (int) (new Date().getTime() / 1000);
                cmt.setCreatedAt(dates);
                commentFacade.create(cmt);
                response.sendRedirect(app + "/clientNews?do=view&id=" + newID);
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
