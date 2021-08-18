/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhd;

import entities.Accounts;
import entities.Songs;
import entities.Stories;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import sessionbean.FavouriteAlbumFacadeLocal;
import sessionbean.SongsFacadeLocal;
import sessionbean.StoriesFacadeLocal;

/**
 *
 * @author asus
 */
@WebServlet("/clientAccount")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class clientAccount extends HttpServlet {

    @EJB
    private FavouriteAlbumFacadeLocal favouriteAlbumFacade;

    @EJB
    private SongsFacadeLocal songsFacade;

    @EJB
    private StoriesFacadeLocal storiesFacade;

    @EJB
    private AccountsFacadeLocal accountsFacade;

    public static final String UPLOAD_DIR = "storage\\profile";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path
        String todo = request.getParameter("do");
        HttpSession session = request.getSession(true);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        if (todo.equals("login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                String hashPassword = md5(password);
                int kq = accountsFacade.checkLogin(username, hashPassword);
                if (kq == 0) {
                    json.put("msg", "These credentials is not match our records.");
                } else {
                    Accounts aAccount = accountsFacade.find(kq);
                    if (aAccount.getStatus() == 1) {
                        json.put("msg", "Account has been locked, contact admin or hotline 0933.222.222");
                    } else {
                        if (aAccount != null) {
                            if (aAccount.getRole().equals("admin")) {
                                session.setAttribute("admin", aAccount.getRole());
                            }
                        }
                        session.setAttribute("accountID", kq);
                        json.put("status", "success");
                    }
                }

            } else {
                json.put("msg", "Please, fill full these fields below.");
            }
            out.print(json);
        } // end login
        else if (todo.equals("register")) {

        } else if (todo.equals("exit")) {
            session.removeAttribute("accountID");
            session.removeAttribute("admin");
            response.sendRedirect(applicationPath + "/");
        } else if (todo.equals("profile")) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Accounts account = accountsFacade.find(id);
                if (account == null) {
                    response.sendRedirect(applicationPath + "/");
                } else {
                    List<Stories> listStories = storiesFacade.getList(id);
                    request.setAttribute("list", listStories);
                    request.setAttribute("account", account);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/index.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        } else if (todo.equals("orders")) {
            if (request.getParameter("type") != null && session.getAttribute("accountID") != null) {
                if (request.getParameter("type").equals("all")) {
                    int id = Integer.parseInt(session.getAttribute("accountID").toString());
                    request.setAttribute("list", accountsFacade.myOrders(id, 0));
                    request.setAttribute("type", "all");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/myorders.jsp");
                    requestDispatcher.forward(request, response);
                } else if (request.getParameter("type").equals("paid")) {
                    int id = Integer.parseInt(session.getAttribute("accountID").toString());
                    request.setAttribute("list", accountsFacade.myOrders(id, 1));
                    request.setAttribute("type", "paid");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/myorders.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    int id = Integer.parseInt(session.getAttribute("accountID").toString());
                    request.setAttribute("list", accountsFacade.myOrders(id, 2));
                    request.setAttribute("type", "unpaid");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/myorders.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        } else if (todo.equals("mysongs")) {
            if (session.getAttribute("accountID") != null) {
                int id = Integer.parseInt(session.getAttribute("accountID").toString());
                request.setAttribute("list", accountsFacade.mySongBought(id));
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/mysongs.jsp");
                requestDispatcher.forward(request, response);
            }
        } else if (todo.equals("playlist")) {
            if (session.getAttribute("accountID") != null) {
                int id = Integer.parseInt(session.getAttribute("accountID").toString());
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter prt = response.getWriter()) {
                    for (Object song : accountsFacade.mySongBought(id)) {
                        Songs idSong = songsFacade.find(song);
                        prt.println("<div class=\"playlist-content-item\" id=\"pl" + idSong.getSongID() + "\">");
                        prt.println("<div class='media'>");
                        prt.println("<div class='media-left'>");
                        prt.println("<a href='' class='ms-play btn-play' data-id='" + idSong.getSongID() + "'>");
                        prt.println("<img class=\"thumbnail-music is-30-30\" src=\"" + applicationPath + "/storage/song/" + idSong.getThumbnail() + "\" alt=\"\">");
                        prt.println("<i class='bx bx-play-circle btn-bs-play'></i>");
                        prt.println("</a>");
                        prt.println("</div>");
                        prt.println("<div class='media-content'>");
                        prt.println("<div class='media-title'>");
                        prt.println("<a>" + idSong.getSongName() + "</a>");
                        prt.println("</div>");
                        prt.println("<div class='media-subtitle'>");
                        prt.println("<p>" + idSong.getArtistID().getNickname() + "</p>");
                        prt.println("</div>");
                        prt.println("</div>");
                        prt.println("</div>");
                        prt.println("</div>");
                    }
                }
            }
        } else if (todo.equals("favorite")) {
            if (session.getAttribute("accountID") != null) {
                int id = Integer.parseInt(session.getAttribute("accountID").toString());
                List<Integer> list = favouriteAlbumFacade.myFavorite(id);
                request.setAttribute("list", list);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("account/myfavorite.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String applicationPath = request.getContextPath(); // get real path'
        HttpSession session = request.getSession(true);
        String todo = request.getParameter("submit");
        if (todo.equals("register")) { // if register
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String gender = request.getParameter("gender");
            if (username != null && password != null && fullname != null && gender != null) {
                if (isValidUser(username)) {
                    Accounts account = new Accounts();
                    account.setUsername(username);
                    account.setPassword(md5(password));
                    account.setFullname(fullname);
                    if (gender.equals("male")) {
                        account.setGender("m");
                    } else {
                        account.setGender("f");
                    }
                    account.setEmail("");
                    account.setAddress("");
                    account.setFavourite("");
                    account.setMoney(0);
                    account.setPhone("");
                    account.setAvatar("default.jpg");
                    account.setRole("member");
                    account.setStatus(0);
                    accountsFacade.create(account);
                    if (account.getAccountID() != null) {
                        session.setAttribute("accountID", account.getAccountID());
                        response.sendRedirect(applicationPath + "/");
                    }
                } else {
                    response.sendRedirect(applicationPath + "/register.jsp?username=error");
                }
            } else {
                response.sendRedirect(applicationPath + "/register.jsp?fields=error");
            }
        } // end register
        else if (todo.equals("login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                String hashPassword = md5(password);
                int kq = accountsFacade.checkLogin(username, hashPassword);
                if (kq == 0) {
                    response.sendRedirect(applicationPath + "/login.jsp?not=error");
                } else {
                    Accounts aAccount = accountsFacade.find(kq);
                    if (aAccount.getStatus() == 1) {
                        response.sendRedirect(applicationPath + "/login.jsp?block=error");

                    } else {
                        if (aAccount.getRole().equals("admin")) {
                            session.setAttribute("admin", aAccount.getRole());
                        }
                        session.setAttribute("accountID", kq);
                        response.sendRedirect(applicationPath + "/");
                    }
                }

            } else {
                response.sendRedirect(applicationPath + "/login.jsp?fields=error");
            }
        } else if (todo.equals("setting")) {
            String applicationPath1 = request.getServletContext().getRealPath(""); // get real path
            String app = request.getContextPath(); // get real path
            String uploadPath = applicationPath1 + File.separator + UPLOAD_DIR;
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject obj = new JSONObject();
            Part filePart = request.getPart("avatarFile");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String favorite = request.getParameter("favorite");
            String gender = request.getParameter("gender");
            if (session.getAttribute("accountID") != null) {
                int userID = Integer.parseInt(session.getAttribute("accountID").toString());
                if (fullname != null && phone != null && address != null && favorite != null && gender != null) {
                    obj.put("msg", "success");
                    Accounts objAccounts = accountsFacade.find(userID);
                    objAccounts.setFullname(fullname);
                    objAccounts.setPhone(phone);
                    objAccounts.setAddress(address);
                    objAccounts.setFavourite(favorite);
                    if (gender.equals("m")) {
                        objAccounts.setGender("m");
                    } else {
                        objAccounts.setGender("f");
                    }
                    if (filePart != null && filePart.getSize() != 0 && filePart.getName().equals("avatarFile")) {
                        String extension = "";
                        String fileName = filePart.getSubmittedFileName();
                        int i = fileName.lastIndexOf('.');
                        if (i > 0) {
                            extension = fileName.substring(i + 1);
                            extension = extension.toLowerCase();
                        }
                        String newName = userID + "." + extension;
                        OutputStream out1 = null;
                        InputStream filecontent = null;
                        if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")) {
                            try {
                                out1 = new FileOutputStream(new File(uploadPath + File.separator + newName));
                                filecontent = filePart.getInputStream();
                                int read = 0;
                                final byte[] bytes = new byte[1024];
                                while ((read = filecontent.read(bytes)) != -1) {
                                    out1.write(bytes, 0, read);
                                }
                                objAccounts.setAvatar(newName);

                            } catch (FileNotFoundException fne) {
                                System.out.println(fne.getMessage());
                            } finally {
                                if (out1 != null) {
                                    out1.close();
                                }
                                if (filecontent != null) {
                                    filecontent.close();
                                }
                            }
                        } else {
                            obj.put("msg", "file");
                        }
                    }
                    accountsFacade.edit(objAccounts);
                } // neu tob tai
            } // neu login
            else {
                obj.put("msg", "login");
            }

            out.print(obj);
        } else if (todo.equals("changepass")) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            JSONObject json = new JSONObject();
            String oldPass = request.getParameter("oldpass");
            String newPass = request.getParameter("newpass");
            if (session.getAttribute("accountID") == null) {
                response.sendRedirect(applicationPath + "/");
            } else {
                int userID = Integer.parseInt(session.getAttribute("accountID").toString());
                Accounts objAccount = accountsFacade.find(userID);
                String md5Oldpass = md5(oldPass);
                int kq = accountsFacade.checkLogin(objAccount.getUsername(), md5Oldpass);
                if (kq == 0) {
                    json.put("msg", "no");
                } else {
                    if (objAccount != null) {
                        if (newPass.length() < 5 || newPass.length() > 12) {
                            json.put("msg", "length");
                        } else {
                            objAccount.setPassword(md5(newPass));
                            accountsFacade.edit(objAccount);
                            json.put("msg", "yes");
                        }
                    }
                }
            }
            out.print(json);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // generate password md5
    public String md5(String input) {

        String md5 = null;

        if (null == input) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        return md5;
    }
    // check if user is contains special character ?

    public boolean isValidUser(String user) {
        String regex = "^[a-zA-Z0-9]{5,12}$";
        return user.matches(regex);
    }

    // check is if exists account in db a18138
    public boolean isExistUser(String user) {
        boolean flag = false;
        try {
            int kq = accountsFacade.findByUsername(user);
            if (kq == 1) {
                flag = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

}
