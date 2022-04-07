/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author Mai
 */
@WebServlet(name = "GetListController", urlPatterns = {"/GetListController"})
public class GetListController extends HttpServlet {

    private static final String AD = "modStudentList.jsp";
    private static final String US = "studentList.jsp";
    private static final String LOGIN = "login.jsp";
    private static final String LD = "studentList.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        int checked = 1;
        String semesterID = request.getParameter("semesterID");

//        System.out.println(request.getParameter("radioGroup"));
        if (request.getParameter("radioGroup") != null) {
            checked = Integer.parseInt(request.getParameter("radioGroup"));

        }
        if (request.getParameter("semesterID") != null) {
            semesterID = request.getParameter("semesterID");
        }
        String searchName = request.getParameter("txtSearch");
        searchName = searchName == null ? "" : searchName;
        try {
            boolean check = true;
            SemesterDAO semesterDAO = new SemesterDAO();
            List<SemesterDTO> listSemester = semesterDAO.getListSemester();
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String loginUserID = loginUser.getUserID();
//            String userDeadline = loginUser.getSTT();
            List<UserDTO> listSupervisor = dao.getListSupervisor();
            session.setAttribute("checked", checked);
            int pageNumber = 1;
            int pageSize = 11;
            if (request.getParameter("page") != null) {
                pageNumber = Integer.parseInt(request.getParameter("page"));
            }
            int noOfPages;
            noOfPages = (int) Math.ceil(dao.getNoOfRecordsSearchAdmin(checked, semesterID, searchName) * 1.0 / pageSize);
            List<UserDTO> listStudent = dao.getUserSearch(pageSize, pageNumber, checked, semesterID, searchName);
//            System.out.println(noOfPages);
            SemesterDAO sem = new SemesterDAO();
            SemesterDTO semDeadline = sem.getSemesterByUserID(loginUserID);

            String roleID = loginUser.getRoleID();
            if ("US".equals(roleID) || "LD".equals(roleID)) {
                
                GroupDAO gDAO = new GroupDAO();
                int groupID = gDAO.getGroupIDByUserID(loginUserID);
                if (groupID == 0) {
                } else {
                    GroupDTO group = gDAO.getGroupByGroupID(groupID);
                    int numOfPer = group.getNumOfPer();
                    if (numOfPer == 5 || numOfPer > 5) {
                        check = false;
                    }
                }
            }
//            if(userDeadline.equals(semDeadline)){
//                check = false;
//            }
            if(roleID.equals("US")){
                check = false;
            }
            session.setAttribute("CHECK_NUMOFPER", check);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", pageNumber);
            session.setAttribute("LIST_SEMESTER", listSemester);
            session.setAttribute("LIST_STUDENT", listStudent);
            session.setAttribute("LIST_SUPERVISOR", listSupervisor);
            if (loginUser == null) {
                url = LOGIN;
            } else if ("AD".equals(loginUser.getRoleID())) {
                url = AD;
            } else if ("US".equals(loginUser.getRoleID())) {
                url = US;
            } else if ("LD".equals(loginUser.getRoleID())) {
                url = LD;
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