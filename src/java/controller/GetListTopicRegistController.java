/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import capstone.CapstoneDAO;
import capstone.CapstoneDTO;
import group.GroupDAO;
import group.GroupDTO;
import java.io.IOException;
import java.util.List;
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
 * @author ASUS
 */
@WebServlet(name = "GetListTopicRegistController", urlPatterns = {"/GetListTopicRegistController"})
public class GetListTopicRegistController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "registerTopic.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean check = true;
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String roleID = loginUser.getRoleID();
            String userID = loginUser.getUserID();
            GroupDAO gDAO = new GroupDAO();
            int groupID = gDAO.getGroupIDByUserID(userID);
            SemesterDAO semDAO = new SemesterDAO();
            SemesterDTO semDTO = semDAO.getSemesterByUserID(userID);
            String semesterID = semDTO.getSemesterID();
            CapstoneDAO capDAO = new CapstoneDAO();
            List<CapstoneDTO> list = capDAO.getListCapstone(semesterID);
            session.setAttribute("LIST_REGIST_TOPIC", list);
            if (roleID.equals("US")){
                check = false;
            }
            if (groupID == 0) {
                check = false;
                session.setAttribute("CHECK_CAPSTONE", check);
                url = SUCCESS;
            } else {
                GroupDTO group = gDAO.getGroupByGroupID(groupID);
                int numOfPer = group.getNumOfPer();
                int capstoneID = group.getCapstoneID();
                if (numOfPer < 4) {
                    check = false;
                    session.setAttribute("CHECK_CAPSTONE", check);
                    url = SUCCESS;
                } else {
                    if (capstoneID == 0) {
                        session.setAttribute("CHECK_CAPSTONE", check);
                        url = SUCCESS;
                    } else {
                        check = false;
                        session.setAttribute("CHECK_CAPSTONE", check);
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at GetListTopicRegistController" + e.toString());
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
