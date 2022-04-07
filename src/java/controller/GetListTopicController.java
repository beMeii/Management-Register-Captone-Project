/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import semester.SemesterDAO;
import semester.SemesterDTO;
import user.UserDAO;
import user.UserDTO;
import capstone.CapstoneDAO;
import capstone.CapstoneDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author PNKV
 */
@WebServlet(name = "GetListTopicController", urlPatterns = {"/GetListTopicController"})
public class GetListTopicController extends HttpServlet {

    private static final String AD = "modTopic.jsp";
    private static final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        int checked = 1;
        try {
            String semesterID = request.getParameter("semesterID");
            if (semesterID == null) {
                SemesterDAO daoSe = new SemesterDAO();
                SemesterDTO semesterCurrent = daoSe.getSemesterV2();
                semesterID = semesterCurrent.getSemesterID();
            }
            SemesterDAO semesterDAO = new SemesterDAO();
            List<SemesterDTO> listSemesterTopic = semesterDAO.getListSemester();
            UserDAO dao = new UserDAO();
            CapstoneDAO capdao = new CapstoneDAO();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");

            session.setAttribute("checked", checked);

            List<CapstoneDTO> listTopic = capdao.getTopicSearch(semesterID);

            session.setAttribute("LIST_SEMESTER_TOPIC", listSemesterTopic);
            session.setAttribute("LIST_TOPIC", listTopic);
            session.setAttribute("SEMESTER_CURRENT", semesterID);
            Map<String, ArrayList<CapstoneDTO>> listCapMutippleMentor = new HashMap<>();
            List<CapstoneDTO> listCapMentor = capdao.getListCapstoneMutilpleMentorV2(semesterID);
            for (CapstoneDTO capstoneDTO : listCapMentor) {                
                if (!listCapMutippleMentor.containsKey(capstoneDTO.getCapstoneName())) {
                    listCapMutippleMentor.put(capstoneDTO.getCapstoneName(), new ArrayList<CapstoneDTO>());
                }

                listCapMutippleMentor.get(capstoneDTO.getCapstoneName()).add(capstoneDTO);
            }
            for (Map.Entry<String, ArrayList<CapstoneDTO>> entry : listCapMutippleMentor.entrySet()) {
                String key = entry.getKey();
                ArrayList<CapstoneDTO> value = entry.getValue();
            }
            session.setAttribute("LIST_MULTI_MT", listCapMutippleMentor);
            if (loginUser == null) {
                url = LOGIN;
            } else if ("AD".equals(loginUser.getRoleID())) {
                url = AD;
            }
        } catch (Exception e) {
            log("Error at GetListController" + e.toString());
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
