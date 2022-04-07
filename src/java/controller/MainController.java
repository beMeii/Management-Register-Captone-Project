/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mac
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String UPDATEPROFILE = "UpdateProfileController";
    private static final String GETLIST = "GetListController";
    private static final String INVITE = "InviteController";
    private static final String ACCEPT = "AcceptInviteController";
    private static final String REFUSE = "RefuseInviteController";
    private static final String RANDOM = "RandomStudentController";
    private static final String DEADLINE = "DeadlineSemesterController";
    private static final String IMPORT = "ImportController";
    private static final String IMPORTCAPSTONE = "ImportCapstoneController";
    private static final String GETLISTTOPIC = "GetListTopicController";
    private static final String KICK = "KickMemberController";
    private static final String INFOR = "GetInforController";
    private static final String GROUPMAX = "ChartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;

        try {
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("editProfile".equals(action)) {
                url = UPDATEPROFILE;
            } else if ("getList".equals(action)) {
                url = GETLIST;
            }else if ("getListTopic".equals(action)) {
                url = GETLISTTOPIC;
            } else if ("Invite".equals(action)) {
                url = INVITE;
            } else if ("Accept".equals(action)) {
                url = ACCEPT;
            } else if ("Refuse".equals(action)) {
                url = REFUSE;
            } else if ("Random".equals(action)) {
                url = RANDOM;
            } else if ("Deadline".equals(action)) {
                url = DEADLINE;
            } else if ("Import".equals(action)) {
                url = IMPORT;
            } else if ("ImportCapstone".equals(action)) {
                url = IMPORTCAPSTONE;
            } else if ("Kick".equals(action)) {
                url = KICK;
            }else if ("Infor".equals(action)) {
                url = INFOR;
            }else if ("maxGroup".equals(action)) {
                url = GROUPMAX;
            }else {
                session.setAttribute("ERROR_MESSAGE", "Function is not available!");
            }
        } catch (Exception e) {
            log("ERROR at MainController" + e.toString());
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
