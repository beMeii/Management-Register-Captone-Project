/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import stackjava.com.accessgoogle.common.GooglePojo;
import stackjava.com.accessgoogle.common.GoogleUtils;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author mac
 */
@WebServlet("/login-google")
public class GoogleController extends HttpServlet {

    private static final String USER = "GetListGroupController";
    private static final String ADMIN = "moderator.jsp";
    private static final String MENTOR = "supervisor.jsp";
    private static final String LEADER = "GetListGroupController";
    private static final String AD = "AD";
    private static final String US = "US";
    private static final String MT = "MT";
    private static final String LD = "LD";
    private static final String ERROR = "login.jsp";

    public GoogleController() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
                dis.forward(request, response);
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo ggPojo = GoogleUtils.getUserInfo(accessToken);
                String gmail = ggPojo.getEmail();
                UserDAO dao = new UserDAO();
                UserDTO user = dao.checkGmail(gmail);
                UserDTO userAD = dao.checkLoginGG(gmail);
                HttpSession session = request.getSession();

                if (user != null) {
                    UserDTO userInfor = dao.getInforUser(user.getUserID());
                    session.setAttribute("INFOR", userInfor);
                    session.setAttribute("LOGIN_USER", user);
                    String roleID = user.getRoleID();
                    if (AD.equals(roleID) || AD.equals(user)) {
                        url = ADMIN;                       
                    } else if (LD.equals(roleID) || LD.equals(userAD)) {
                        url = LEADER;
                    } else if (US.equals(roleID) || US.equals(userAD)) {
                        url = USER;
                    } else if (MT.equals(roleID) || MT.equals(user)) {
                        url = MENTOR;
                    }
                } else {
                    request.setAttribute("ERROR", "Your email is not supported!");
                }
            }
        } catch (Exception e) {
            log("Error at GoogleController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
