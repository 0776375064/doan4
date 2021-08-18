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
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import sessionbean.CategoriesFacadeLocal;
import sessionbean.CommentFacadeLocal;
import sessionbean.NewsFacadeLocal;

/**
 *
 * @author HP
 */
@WebServlet("/admin-news")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class ManageNews extends HttpServlet {

    @EJB
    private NewsFacadeLocal newsFacade;

    @EJB
    private CategoriesFacadeLocal categoriesFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    @EJB
    private CommentFacadeLocal commentFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static final String UPLOAD_DIR = "storage/news";

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
        String app = request.getContextPath();
        String todo = request.getParameter("do");

        if (todo.equals("list")) {

            List<News> list = newsFacade.findAll();
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/news/list.jsp");
            // RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-news?do=list");
            requestDispatcher.forward(request, response);
        } else if (todo.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("id"));
            News obj = newsFacade.find(id);
            // get select options
            List<Categories> listCategories = new ArrayList();
            List<Accounts> listAccount = new ArrayList();

            listCategories = categoriesFacade.findAll();
            listAccount = accountsFacade.findAll();
            if (obj == null) {
                response.sendRedirect(app + "/admin-news?do=list");

            } else {
                obj.setViews(obj.getViews() + 1);
                newsFacade.edit(obj);
                Categories objCate = categoriesFacade.find(obj.getCategoryID().getCategoryID());
                request.setAttribute("categories", objCate);
                request.setAttribute("news", obj);
                request.setAttribute("accounts", listAccount);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dates = formatter.format(obj.getCreatedAt());
                request.setAttribute("date", dates);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/news/edit.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("create")) {
            //int id = Integer.parseInt(request.getParameter("newID")); //??? 
            HttpSession session = request.getSession();
            request.setAttribute("accountID", 7);
            List<Categories> listCategories = new ArrayList();
            listCategories = categoriesFacade.findAll();
            request.setAttribute("categories", listCategories);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/news/create.jsp");
            requestDispatcher.forward(request, response);

        } else if (todo.equals("search")) {
            String s = request.getParameter("s");
            List<News> list = new ArrayList();
            if (!s.isEmpty()) {
                list = newsFacade.findByNames(s);
            }
            request.setAttribute("list", list);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/news/search.jsp");
            requestDispatcher.forward(request, response);
        }  
        else if (todo.equals("statistic")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            System.out.println(LocalDate.now().format(formatter));
            String today = LocalDate.now().format(formatter);
            request.setAttribute("list", newsFacade.getStatistic(today, today));
            //request.setAttribute("list", newsFacade.getStatistic(today));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/news/statistic.jsp");
            requestDispatcher.forward(request, response);
    }
         else if (todo.equals("getDate")) {
            String fdate = request.getParameter("fdate");
            String tdate = request.getParameter("tdate");
            List<Integer> data = new ArrayList();
            if (fdate != null && tdate != null) {
                List<String> list = newsFacade.getDateNews(fdate, tdate);
                for (String text : list) {
                    data.add(newsFacade.countNewsInDay(text));
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
            HttpSession session = request.getSession();

            Part filePart = request.getPart("thumbnail");
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            //String thumbnail= request.getParameter("avatar");
            int cate = Integer.parseInt(request.getParameter("categoryID"));
            // HttpSession session = request.getSession(true);
            //Date createdAt = Date.valueOf(request.getParameter("date"));
            // set value
            request.setAttribute("accountID", 7);
            Categories category = categoriesFacade.find(cate);

            News news = new News();
            news.setCategoryID(category);
            news.setTitle(title);
            news.setDescription(description);

            //  news.setThumbnail(thumbnail);
            news.setViews(0);
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
            news.setCreatedAt(timestamp);

            if (session != null && session.getAttribute("accountID") != null) {
                int acc = Integer.parseInt(session.getAttribute("accountID").toString());
                Accounts account = accountsFacade.find(acc);
                news.setAccountID(account);
            }

            if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("thumbnail")) {
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
                        news.setThumbnail(newName); // set thumbnail

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
            } else {
                news.setThumbnail("default.jpg");
            }
            newsFacade.create(news);

            response.sendRedirect(app + "/admin-news?do=create&msg=success");
        } else if (submit.equals("edit")) {
            int id = Integer.parseInt(request.getParameter("newID"));
            News news = newsFacade.find(id);
            if (news == null) {
                response.sendRedirect(applicationPath + "/admin-news?do=list");
            } else {
                System.out.println("edit news");
                Part filePart = request.getPart("thumbnail");
                String title = request.getParameter("title");
                String description = request.getParameter("description");
                // int view = Integer.parseInt(request.getParameter("views"));
                //Date createdAt = Date.valueOf(request.getParameter("date"));
                //int cate = Integer.parseInt(request.getParameter("categoryID")); // 
                //  int acc = Integer.parseInt(request.getParameter("accountID"));
                news.setTitle(title);

                news.setDescription(description);

                if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("thumbnail")) {
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
                            news.setThumbnail(newName); // set thumbnail
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
                    //news.setViews(view);
                    //Categories objCategories = categoriesFacade.find(cate);
                    //news.setCategoryID(objCategories);
                    // Accounts objAccount = accountsFacade.find(id);
                    // news.setAccountID(objAccount);
                    //news.setCreatedAt(createdAt);
                    Calendar cal = Calendar.getInstance();
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(cal.getTimeInMillis());
                    news.setCreatedAt(timestamp);
                }
                newsFacade.edit(news);
                // response.sendRedirect(app + "/admin-news?do=edit&id=" + id + "&msg=success");
                response.sendRedirect(app + "/admin-news?do=list");
            }

        } else if (submit.equals("delete")) {
            int id = Integer.parseInt(request.getParameter("newID"));
            News obj = newsFacade.find(id);
            newsFacade.remove(obj);
            response.sendRedirect(app + "/admin-news?do=list");

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
