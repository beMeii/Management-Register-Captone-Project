/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import group.GroupDAO;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import semester.SemesterDAO;
import semester.SemesterDTO;
import user.UserDTO;

/**
 *
 * @author Mai
 */
@WebServlet(name = "ChartController", urlPatterns = {"/ChartController"})
public class ChartController extends HttpServlet {

    private static final String AD = "moderator.jsp";
    private static final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            GroupDAO daoSe = new GroupDAO();
            int groupMax = daoSe.getMaxGroupID();
            int capstoneMax = daoSe.getMaxCapstoneID();
            int capstoneChecked = daoSe.getCapstoneIDCheck();
            int studentMax = daoSe.getMaxStudentID();
            int studentInGroup = daoSe.getStudnentInGroup();
            session.setAttribute("GROUP_MAX", groupMax);
            session.setAttribute("CAPSTONE_MAX", capstoneMax);
            session.setAttribute("CAPSTONE_CHECKED", capstoneChecked);
            session.setAttribute("STUDENT_MAX", studentMax);
            session.setAttribute("STUDENT_IN_GROUP", studentInGroup);
            if (loginUser == null) {
                url = LOGIN;
            } else if ("AD".equals(loginUser.getRoleID())) {
                url = AD;
            }

        } catch (Exception e) {
            log("Error at DeadlineSemesterController" + e.toString());
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
