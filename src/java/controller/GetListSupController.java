/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Mai
 */
@WebServlet(name = "GetListSupController", urlPatterns = {"/GetListSupController"})
public class GetListSupController extends HttpServlet {

    private static final String AD = "modSupList.jsp";
    private static final String US = "studentList.jsp";
    private static final String LOGIN = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        int checked = 1;
        if (request.getParameter("radioSupGroup") != null) {
            checked = Integer.parseInt(request.getParameter("radioSupGroup"));
            try {
                UserDAO dao = new UserDAO();
                HttpSession session = request.getSession();
                session.setAttribute("checked", checked);
                UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                int pageNumber = 1;
                int pageSize = 11;
                if (request.getParameter("page") != null) {
                    pageNumber = Integer.parseInt(request.getParameter("page"));
                }
                int noOfPages;
                noOfPages = (int) Math.ceil(dao.getNoOfRecordsSupervisor(checked) * 1.0 / pageSize);
                List<UserDTO> listSupervisor = dao.getSupervisorSearch(pageSize, pageNumber, checked);

                Map<String, ArrayList<UserDTO>> ListSupervisorMap = new HashMap<>();
                for (UserDTO userDTO : listSupervisor) {
                    if (!ListSupervisorMap.containsKey(userDTO.getUserID())) {
                        ListSupervisorMap.put(userDTO.getUserID(), new ArrayList<UserDTO>());
                    }

                    ListSupervisorMap.get(userDTO.getUserID()).add(userDTO);
                }

                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", pageNumber);
                session.setAttribute("LIST_SUPERVISOR", ListSupervisorMap);
                if (loginUser == null) {
                    url = LOGIN;
                } else if ("AD".equals(loginUser.getRoleID())) {
                    url = AD;
                } else if ("US".equals(loginUser.getRoleID())) {
                    url = US;
                }
            } catch (Exception e) {
                log("Error at GetListSupController" + e.toString());
            } finally {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
