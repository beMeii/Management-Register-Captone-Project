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
import group.UserGroup;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.acl.Group;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import request.RequestDAO;
import user.UserDAO;
import user.UserDTO;
import utils.EmailAcptUtils;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "AcceptInviteController", urlPatterns = {"/AcceptInviteController"})
public class AcceptInviteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String US = "GetListRequestController";
    private static final String MT = "GetListRegistRequestController";
    private static final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            UserDTO invitedUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String leaderID = request.getParameter("leaderID"); //lấy ID của leader   
            String email = request.getParameter("email");
            UserDAO uDAO = new UserDAO();
            UserDTO leader = uDAO.getUserByUserID(leaderID);
            String invitedID = invitedUser.getUserID();
            String roleID = invitedUser.getRoleID();
            if ("US".equals(roleID)) {
                GroupDAO gDAO = new GroupDAO();
                int userGroupID = gDAO.getMaxUserGroupID() + 1;
                int groupID = Integer.parseInt(leader.getGroupID());
                int isSupervisor = 0;
                UserGroup userGroup = new UserGroup(userGroupID, invitedID, groupID, isSupervisor);
                boolean check1 = gDAO.acceptInviteGroup(userGroup);
                if (check1) {
                    GroupDTO group = gDAO.getGroupByGroupID(groupID);
                    int numOfPer = group.getNumOfPer() + 1;
                    boolean check2 = gDAO.updateNumberOfPerson(numOfPer, groupID);
                    if (check2) {
                        int statusID = 2;
                        boolean check3 = uDAO.updateStatusID(invitedID, statusID);
                        if (check3) {
                            statusID = 0;
                            RequestDAO reqDAO = new RequestDAO();
                            boolean check4 = reqDAO.refuseRequest(invitedID, leaderID);
                            if (check4) {
                                url = US;
                            }
                            new Thread(() -> {
                                EmailAcptUtils.send(email, leaderID, invitedID);
                            }).start();
                        }

                    }
                }
            } else if ("MT".equals(roleID)) {
                GroupDAO gDAO = new GroupDAO();
                int userGroupID = gDAO.getMaxUserGroupID() + 1;
                int groupID = Integer.parseInt(leader.getGroupID());
                int isSupervisor = 1;
                String capstoneID = request.getParameter("capstoneID");
                CapstoneDAO capDAO = new CapstoneDAO();
                CapstoneDTO capDTO = capDAO.getCapstoneByCapstoneID(capstoneID);
                String statusID = capDTO.getPhone();
                if (statusID.equals("0")) {
                    RequestDAO reqDao = new RequestDAO();
                    boolean check = reqDao.refuseRequest(invitedID, leaderID);
                } else if (statusID.equals("1")) {
                    UserGroup userGroup = new UserGroup(userGroupID, invitedID, groupID, isSupervisor);
                    boolean check1 = gDAO.acceptInviteGroup(userGroup);
                    if (check1) {
                        GroupDTO group = gDAO.getGroupByGroupID(groupID);
                        boolean check2 = gDAO.updateCapstoneGroup(groupID, capstoneID);
                        if (check2) {
                            statusID = "0";
                            String groupID1 = String.valueOf(groupID);
                            CapstoneDTO capDTO2 = new CapstoneDTO(capstoneID, groupID1, statusID);
                            boolean check3 = capDAO.updateCapstone(capDTO2);
                            if (check3) {
                                url = MT;
                            }
                            new Thread(() -> {
                                EmailAcptUtils.send(email, leaderID, invitedID);
                            }).start();
                        }
                    }
                }

            }
        } catch (Exception e) {
            log("Error at AcceptInviteController" + e.toString());
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
