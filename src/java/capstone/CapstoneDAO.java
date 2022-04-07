/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capstone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import user.UserDTO;
import utils.DBUtils;

/**
 *
 * @author dtsang
 */
public class CapstoneDAO {

    public CapstoneDTO getCapstoneName(String groupID) throws SQLException {
        CapstoneDTO capstone = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT tblUser.name, tblCapstone.capstoneName FROM tblUser \n"
                        + "Left Join tblUserGroup ON tblUser.userID = tblUserGroup.userID \n"
                        + "Left Join tblCapstone ON tblUserGroup.groupID = tblCapstone.groupID\n"
                        + "WHERE tblUserGroup.isSupervisor = 'true' and tblUserGroup.groupID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, groupID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String capstoneName = rs.getString("capstoneName");
                    capstone = new CapstoneDTO(capstoneName, "", name, "", "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return capstone;
    }

    public List<String> getListCapsRandom(int n, String semesterID) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT TOP (?) capstoneID "
                        + " FROM tblCapstone"
                        + " WHERE semesterID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, n);
                stm.setString(2, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String capstoneID = rs.getString("capstoneID");
                    list.add(capstoneID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<CapstoneDTO> getTopicSearch(String semesterID) throws SQLException {
        List<CapstoneDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT  capstoneID, capstoneName, groupID, statusID "
                        + " FROM tblCapstone "
                        + " WHERE semesterID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String capstoneID = rs.getString("capstoneID");
                    String capstoneName = rs.getString("capstoneName");
//                    String userName = rs.getString("name");
                    String groupID = rs.getString("groupID");
                    String statusID = rs.getString("statusID");
                    list.add(new CapstoneDTO(capstoneID, capstoneName, groupID, semesterID, statusID, ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<CapstoneDTO> getListCapstone(String semesterID) throws SQLException {
        List<CapstoneDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT  c.capstoneID, c.capstoneName, c.statusID, u.userID, u.name, u.gmail "
                        + " FROM tblCapstone c left join tblUserCapstone uc on c.capstoneID = uc.capstoneID left join tblUser u on uc.userID = u.userID  "
                        + " WHERE c.semesterID = ? AND u.roleID = 'MT' AND c.statusID = 1 "
                        + " ORDER BY c.capstoneName ASC ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String capstoneID = rs.getString("capstoneID");
                    String capstoneName = rs.getString("capstoneName");
                    String statusID = rs.getString("statusID");
                    String userID = rs.getString("userID");
                    String userName = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    list.add(new CapstoneDTO(capstoneID, capstoneName, gmail, userID, statusID, userName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<CapstoneDTO> getListMentorCapstone(String userID, String semesterID) throws SQLException {
        List<CapstoneDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT c.capstoneName, c.statusID, g.groupName "
                        + " FROM tblUserCapstone uc left join tblCapstone c on uc.capstoneID = c.capstoneID left join tblGroup g on c.groupID = g.groupID "
                        + " WHERE uc.userID = ? AND c.semesterID = ? "
                        + " ORDER BY c.capstoneName ASC ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String capstoneName = rs.getString("capstoneName");
                    String statusID = rs.getString("statusID");
                    String groupName = rs.getString("groupName");
                    list.add(new CapstoneDTO(capstoneName, groupName, statusID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public CapstoneDTO getCapstoneByCapstoneID(String capstoneID) throws SQLException {
        CapstoneDTO capDTO = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT u.userID, u.name, c.capstoneName, c.statusID "
                        + " FROM tblUser u left join tblUserCapstone uc on u.userID = uc.userID left join tblCapstone c on uc.capstoneID = c.capstoneID "
                        + " WHERE c.capstoneID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, capstoneID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String userName = rs.getString("name");
                    String capstoneName = rs.getString("capstoneName");
                    String statusID = rs.getString("statusID");
                    capDTO = new CapstoneDTO(capstoneID, capstoneName, userID, userName, statusID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return capDTO;
    }

    public boolean updateCapstone(CapstoneDTO capstone) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblCapstone set groupID = ?, statusID = ? "
                        + " WHERE capstoneID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, capstone.getGroupId());
                stm.setString(2, capstone.getStatusId());
                stm.setString(3, capstone.getCapstoneID());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public List<CapstoneDTO> getListCapstoneMutilpleMentor() throws SQLException {
        List<CapstoneDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT tb4.capstoneID, tb4.capstoneName, tb1.name"
                        + " FROM (tblUser tb1 LEFT JOIN tblUserGroup tb2 ON tb1.userID = tb2.userID \n"
                        + " Left Join tblUserCapstone tb3 ON tb1.userID = tb3.userID \n"
                        + " Left Join tblCapstone tb4 ON tb3.capstoneID = tb4.capstoneID\n"
                        + " Left Join tblGroup tb5 ON tb4.groupID = tb5.groupID\n"
                        + " )\n"
                        + " WHERE tb1.roleID = 'MT'\n"
                        + " GROUP BY tb4.capstoneID,tb1.name,tb4.capstoneName ORDER BY tb4.capstoneID ASC ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String capstoneID = rs.getString("capstoneID");
                    String capstoneName = rs.getString("capstoneName");
                    String name = rs.getString("name");
                    list.add(new CapstoneDTO(capstoneID, capstoneName, "", "", "", name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }

    public List<CapstoneDTO> getListCapstoneMutilpleMentorV2(String semesterID) throws SQLException {
        List<CapstoneDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT tb4.capstoneID, tb4.capstoneName, tb4.statusID, tb1.name\n"
                        + "FROM (tblUser tb1 LEFT JOIN tblUserGroup tb2 ON tb1.userID = tb2.userID\n"
                        + "Left Join tblUserCapstone tb3 ON tb1.userID = tb3.userID\n"
                        + "Left Join tblCapstone tb4 ON tb3.capstoneID = tb4.capstoneID\n"
                        + "Left Join tblGroup tb5 ON tb4.groupID = tb5.groupID)\n"
                        + "WHERE tb4.semesterID = ? AND tb1.roleID = 'MT'\n"
                        + "GROUP BY tb4.capstoneID,tb4.capstoneName,tb1.name, tb4.statusID ORDER BY tb4.capstoneID ASC  ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String capstoneID = rs.getString("capstoneID");
                    String capstoneName = rs.getString("capstoneName");
                    String statusID = rs.getString("statusID");
                    String name = rs.getString("name");
                    list.add(new CapstoneDTO(capstoneID, capstoneName, "", "", statusID, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
        public List<String> getSupByCapstoneID(String capstoneID) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT userID "
                        + " FROM tblUserCapstone "
                        + " WHERE capstoneID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, capstoneID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    list.add(userID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
}
