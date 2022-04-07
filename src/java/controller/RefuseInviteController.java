/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import request.RequestDAO;
import user.UserDTO;
import utils.EmailRefuseUtils;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RefuseInviteController", urlPatterns = {"/RefuseInviteController"})
public class RefuseInviteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String MT = "GetListRegistRequestController";
    private static final String US = "GetListRequestController";
    private static final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO invitedUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String roleID = invitedUser.getRoleID();
            String invitedID = invitedUser.getUserID();
            String leaderID = request.getParameter("leaderID");
            String email = request.getParameter("email");
            RequestDAO reqDao = new RequestDAO();
            boolean check = reqDao.refuseRequest(invitedID, leaderID);
            if ("US".equals(roleID)) {
                if (check) {
                    url = US;
                }
                new Thread(() -> {
                    EmailRefuseUtils.send(email, leaderID, invitedID);
                }).start();
            } else if ("MT".equals(roleID)) {
                if (check) {
                    url = MT;
                }
                new Thread(() -> {
                    EmailRefuseUtils.send(email, leaderID, invitedID);
                }).start();
            }

        } catch (Exception e) {
            log("Error at RefuseInviteController" + e.toString());
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
