/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import capstone.CapstoneDAO;
import capstone.CapstoneDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import semester.SemesterDAO;
import semester.SemesterDTO;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "GetListMentorTopicController", urlPatterns = {"/GetListMentorTopicController"})
public class GetListMentorTopicController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private static final String MT = "supTopic.jsp";
    private static final String ERROR = "login.jsp"; 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        int checked = 1;
        String semesterID = request.getParameter("semesterID");
        try {
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String userID = loginUser.getUserID();
            
            CapstoneDAO capDAO = new CapstoneDAO();
            SemesterDAO semesterDAO = new SemesterDAO();
            List<SemesterDTO> listSemesterTopic = semesterDAO.getListSemester();
            UserDAO dao = new UserDAO();
//            List<UserDTO> listSupervisor = dao.getListSupervisor();

            session.setAttribute("checked", checked);
            int pageNumber = 1;
            int pageSize = 11;
            if (request.getParameter("page") != null) {
                pageNumber = Integer.parseInt(request.getParameter("page"));
            }
//          int noOfPages;
//            noOfPages = (int) Math.ceil(dao.getNoOfRecordsSearchAdmin(checked,semesterID) * 1.0 / pageSize);
            List<CapstoneDTO> listTopic = capDAO.getListMentorCapstone(userID, semesterID);
//            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", pageNumber);
            session.setAttribute("LIST_SEMESTER_TOPIC", listSemesterTopic);
            session.setAttribute("LIST_MENTOR_TOPIC", listTopic);
//            session.setAttribute("LIST_SUPERVISOR", listSupervisor);
            Map<String, ArrayList<String>> listCapMutippleMentor = new HashMap<>();
            List<CapstoneDTO> listCapMentor = capDAO.getListCapstoneMutilpleMentor();
            for (CapstoneDTO capstoneDTO : listCapMentor) {
                if (!listCapMutippleMentor.containsKey(capstoneDTO.getCapstoneName())) {
                    listCapMutippleMentor.put(capstoneDTO.getCapstoneName(), new ArrayList<String>());
                }

                listCapMutippleMentor.get(capstoneDTO.getCapstoneName()).add(capstoneDTO.getUserName());
            }
            for (Map.Entry<String, ArrayList<String>> entry : listCapMutippleMentor.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> value = entry.getValue();
            }
        if (loginUser == null) {
                url = ERROR;
            } else if ("MT".equals(loginUser.getRoleID())) {
                url = MT;
            }
        } catch (Exception e) {
            log ("Error at GetListMentorController " + e.toString());
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
