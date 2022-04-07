/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import capstone.CapstoneDTO;
import group.GroupDAO;
import group.GroupDTO;
import java.io.IOException;
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
import user.UserDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "GetListUserGroupController", urlPatterns = {"/GetListUserGroupController"})
public class GetListUserGroupController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String AD = "modGroup.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            GroupDAO gDAO = new GroupDAO();
            String semesterID = request.getParameter("semesterID");
            Map<String, ArrayList<String>> listUserGroup = new HashMap<>();
            List<GroupDTO> listGroup = gDAO.getListUserGroup(semesterID);
            for (GroupDTO gDTO : listGroup) {
                if (!listUserGroup.containsKey(gDTO.getGroupName())) {
                    listUserGroup.put(gDTO.getGroupName(), new ArrayList<String>());
                }
                listUserGroup.get(gDTO.getGroupName()).add(gDTO.getUsername());
            }
            for (Map.Entry<String, ArrayList<String>> entry : listUserGroup.entrySet()) {
                String key = entry.getKey();
                ArrayList<String> value = entry.getValue();
            }
            session.setAttribute("LIST_USERGROUP", listUserGroup);
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String roleID = loginUser.getRoleID();
            if ("AD".equals(roleID)){
                url = AD;
            }
        } catch (Exception e) {
            log("Error at GetListUserGroupController " + e.toString());
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
