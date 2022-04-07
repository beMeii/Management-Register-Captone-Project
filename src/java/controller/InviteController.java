/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import group.GroupDAO;
import group.GroupDTO;
import group.UserGroup;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import request.RequestDAO;
import request.RequestDTO;
import user.UserDAO;
import user.UserDTO;
import utils.EmailSendUtils;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "InviteController", urlPatterns = {"/InviteController"})
public class InviteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String US = "GetListController?radioGroup=0&semesterID=SP22";
    private static final String MT = "GetListTopicRegistController";
    private static final String ERROR = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            RequestDAO reqDAO = new RequestDAO();
            UserDAO usDAO = new UserDAO();
            int requestID = reqDAO.getMaxRequestID() + 1;//Tạo RequestID mới
            String invitedID = request.getParameter("userID");//userID của người dc mời
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String loginUserID = loginUser.getUserID();//lấy userID của người mời
            UserDAO uDAO = new UserDAO();
            int statusID = Integer.parseInt(loginUser.getStatusID());
            String email = request.getParameter("email");
            UserDTO invitedUser = usDAO.getUserByUserID(invitedID);
            UserDTO leader = usDAO.getUserByUserID(loginUserID);//Lấy leader
            String roleID = invitedUser.getRoleID();
            if ("US".equals(roleID)) {
                if (statusID == 3) { //check status của LD 
                    statusID = 2;
                    boolean check1 = uDAO.updateStatusID(loginUserID, statusID);
                    if (check1) {
                        GroupDAO gDAO = new GroupDAO();
                        int groupID = gDAO.getMaxGroupID() + 1; //Tạo groupID mới -> tạo group mới
                        String groupName = "Group " + String.valueOf(groupID); //Tên nhóm mới
                        int capstoneID = 0;
                        int numOfPer = 1; // 1 thằng leader
                        statusID = 1;
                        GroupDTO group = new GroupDTO(groupID, groupName, capstoneID, numOfPer, statusID);
                        boolean check2 = gDAO.addToGroup(group); //Tạo nhóm mới
                        if (check2) {
                            int userGroupID = gDAO.getMaxUserGroupID() + 1;
                            int isSupervisor = 0;
                            UserGroup userGroup = new UserGroup(userGroupID, loginUserID, groupID, isSupervisor);
                            loginUser.setGroupID(String.valueOf(groupID));
                            boolean check3 = gDAO.acceptInviteGroup(userGroup);
                            if (check3) {
                                String requestDetail = loginUser.getGroupID();
                                RequestDTO reqDTO = new RequestDTO(requestID, invitedID, loginUserID, requestDetail, statusID);//Lấy thông tin cho request
                                boolean check4 = reqDAO.inviteGroup(reqDTO); //Insert param vào request      
                                if (check4) {
                                    url = US;
                                }
                                new Thread(() -> {
                                    EmailSendUtils.send(email, loginUserID, invitedID);
                                }).start();
                            }
                        }
                    }
                } else if (statusID == 2) {
                    statusID = 1;
                    String groupID = leader.getGroupID();
                    String requestDetail = groupID;
                    RequestDTO reqDTO = new RequestDTO(requestID, invitedID, loginUserID, requestDetail, statusID);//Lấy thông tin cho request
                    boolean check = reqDAO.inviteGroup(reqDTO); //Insert param vào request      
                    if (check) {
                        url = US;
                    }
                    new Thread(() -> {
                        EmailSendUtils.send(email, loginUserID, invitedID);
                    }).start();
                }
            }
            if ("MT".equals(roleID)) {
                statusID = 1;
                String requestDetail = request.getParameter("capstoneID");
                RequestDTO reqDTO = new RequestDTO(requestID, invitedID, loginUserID, requestDetail, statusID);//Lấy thông tin cho request
                boolean check = reqDAO.inviteGroup(reqDTO); //Insert param vào request 
                if (check) {
                    url = MT;
                }
                new Thread(() -> {
                    EmailSendUtils.send(email, loginUserID, invitedID);
                }).start();
            }

        } catch (Exception e) {
            log("Error at InviteController" + e.toString());
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
