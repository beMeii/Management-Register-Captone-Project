/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import capstone.CapstoneDAO;
import group.Cart;
import group.GroupDAO;
import group.GroupDTO;
import group.UserGroup;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
 * @author Mai
 */
@WebServlet(name = "RandomStudentController", urlPatterns = {"/RandomStudentController"})
public class RandomStudentController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "GetListUserGroupController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            UserDAO dao = new UserDAO();
            HttpSession session = request.getSession();
            String semesterID = request.getParameter("semesterID");
            List<UserDTO> listStudentNoGroup = dao.getListStudentNoGroup(semesterID);
            int n = listStudentNoGroup.size();
            // check n>0
            SemesterDAO daoSes = new SemesterDAO();
            int maxSesID = daoSes.getMaxSemesterNO();
            SemesterDTO sesmester = daoSes.getSemester(maxSesID);
            boolean flag = true;
            if (sesmester.getSemesterID().equalsIgnoreCase(semesterID)) {
                boolean check = daoSes.insertNewSesmester(semesterID);
            }
            int groupBy5 = 0;
            int groupBy4 = 0;
            GroupDAO daoGroup = new GroupDAO();
            if (n < 4) {
                for (UserDTO userDTO : listStudentNoGroup) {
                    boolean check2 = dao.updateStudentRedundant(userDTO.getUserID(), sesmester.getSemesterID());
                    if (!check2) {
                        flag = false;
                        break;
                    }
                }
            } else if (n > 4) {
                if (n == 6 || n == 7 || n == 11) {
                    // random group of 5 and move redundant student to next semester
                    groupBy5 = n / 5;
                    List<UserDTO> listRedundant = listStudentNoGroup.subList(groupBy5 * 5, n);
                    for (UserDTO userDTO : listRedundant) {
                        boolean check2 = dao.updateStudentRedundant(userDTO.getUserID(), sesmester.getSemesterID());
                        if (!check2) {
                            flag = false;
                            break;
                        }
                    }
                } else if (n % 5 == 0) {
                    groupBy5 = n / 5;
                } else {
                    int delta = 0;
                    for (int i = 0;; i += 5) {
                        if (i > n) {
                            delta = i;
                            break;
                        }
                    }
                    groupBy4 = delta - n;
                    groupBy5 = (n - groupBy4 * 4) / 5;
                }
                List<UserDTO> list = new ArrayList<>();
                int count = 0;
                int groupID = daoGroup.getMaxGroupID() + 1;
                List<Integer> listGroupID = new ArrayList<>();
                for (int i = 0; i < groupBy5; i++) {
                    listGroupID.add(groupID);
                    // insert group
                    String groupName = "Group" + String.valueOf(groupID);
                    boolean check = daoGroup.addToGroup(new GroupDTO(groupID, groupName, 0, 5, 2));
                    // insert UserGroup
                    boolean checkUserGroup = daoGroup.insertUserGroup(listStudentNoGroup.subList(count, (i + 1) * 5), groupID);
                    groupID++;
                    count = (i + 1) * 5;
                    if (!check && !checkUserGroup) {
                        flag = false;
                        break;
                    }
                }
                int num = groupBy5 + groupBy4;
                for (int i = 0; i < groupBy4; i++) {
                    listGroupID.add(groupID);
                    // insert group
                    String groupName = "Group" + String.valueOf(groupID);
                    boolean check = daoGroup.addToGroup(new GroupDTO(groupID, groupName, 0, 4, 2));
                    // insert UserGroup
                    boolean checkUserGroup = daoGroup.insertUserGroup(listStudentNoGroup.subList(count, count + 4), groupID);
                    groupID++;
                    count += 4;
                    groupBy5++;
                    if (!check && !checkUserGroup) {
                        flag = false;
                        break;
                    }
                }
                // random capstone
                CapstoneDAO daoCap = new CapstoneDAO();
                List<String> listCapstone = daoCap.getListCapsRandom(num, semesterID);
                List<String> listSupByCapstone = new ArrayList<>();
                for (int i = 0; i < num; i++) {
                    boolean checkUpdateCaps = daoGroup.updateCapstoneGroup(listGroupID.get(i), listCapstone.get(i));
                    listSupByCapstone = daoCap.getSupByCapstoneID(listCapstone.get(i));
                    boolean checkInsertSup = daoGroup.inserSupIntoUserGroup(listSupByCapstone, listGroupID.get(i));
                    if (!checkUpdateCaps && !checkInsertSup) {
                        flag = false;
                        break;
                    }
                }
                for (UserDTO userDTO : listStudentNoGroup) {
                    boolean checkUpdateStatus = daoGroup.updateStudentStatus(userDTO.getUserID());
                    if (!checkUpdateStatus) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at RandomController" + e.toString());
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
