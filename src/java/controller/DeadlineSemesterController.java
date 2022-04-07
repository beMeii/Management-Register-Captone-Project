/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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

/**
 *
 * @author Mai
 */
@WebServlet(name = "DeadlineSemesterController", urlPatterns = {"/DeadlineSemesterController"})
public class DeadlineSemesterController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "moderator.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
            try {
                String deadline = request.getParameter("deadline");
                SemesterDAO daoSe = new SemesterDAO();
                int noMax = daoSe.getMaxSemesterNO();
                SemesterDTO semester = daoSe.getSemester(noMax);
                boolean check = daoSe.updateDeadline(semester.getSemesterID(), deadline);
                HttpSession session = request.getSession();
                
                if (check) {
                    session.setAttribute("DEADLINE", deadline);
                    url = SUCCESS;
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
