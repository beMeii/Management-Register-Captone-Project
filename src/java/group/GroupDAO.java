/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group;

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
 * @author Mai
 */
public class GroupDAO {

    public int getMaxUserGroupID() throws SQLException {
        int userGroupID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT MAX(userGroupID) AS MAXUSERGROUPID "
                        + " FROM tblUserGroup ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    userGroupID = rs.getInt("MAXUSERGROUPID");
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
        return userGroupID;
    }

    public int getMaxGroupID() throws SQLException {
        int userGroupID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT MAX(groupID) AS MAXGROUPID "
                        + " FROM tblGroup ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    userGroupID = rs.getInt("MAXGROUPID");
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
        return userGroupID;
    }

    public boolean acceptInviteGroup(UserGroup user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblUserGroup (userGroupID, userID, groupID, isSupervisor) "
                        + " VALUES (?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, user.getUserGroupID());
                stm.setString(2, user.getUserID());
                stm.setInt(3, user.getGroupID());
                stm.setInt(4, user.getIsSupervisor());
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

    public boolean addToGroup(GroupDTO group) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblGroup (groupID, groupName, capstoneID, numberOfPerson, statusID) "
                        + " VALUES (?,?,?,?,?) ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, group.getGroupID1());
                stm.setString(2, group.getGroupName());
                stm.setInt(3, group.getCapstoneID());
                stm.setInt(4, group.getNumOfPer());
                stm.setInt(5, group.getStatusID1());
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

        public boolean updateNumberOfPerson(int numOfPer, int groupID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblGroup set numberOfPerson = ? "
                        + " WHERE groupID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, numOfPer);
                stm.setInt(2, groupID);
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

    public String getGroupID(String userID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String groupID = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT top 1 groupID FROM tblUserGroup \n"
                        + "Where tblUserGroup.userID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    groupID = rs.getString("groupID");
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
        return groupID;
    }

    public List<GroupDTO> getListStudentInGroup(String groupIdSearch) throws SQLException {
        List<GroupDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT tb2.userID,tb2.roleID,tb2.name,tb2.phone, tb2.gmail, tb2.statusID,tb2.photoUrl,tb1.groupID, tb3.groupName\n"
                        + "FROM (tblUserGroup tb1 INNER JOIN tblUser tb2 ON tb1.userID = tb2.userID \n"
                        + "Left Join tblGroup tb3 ON tb1.groupID = tb3.groupID)\n"
                        + "WHERE tb1.userID IS NOT NULL AND (tb2.roleID = 'US' OR tb2.roleID = 'LD') AND tb1.groupID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, groupIdSearch);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String username = rs.getString("name");
                    String phone = rs.getString("phone");
                    String gmail = rs.getString("gmail");
                    String role = rs.getString("roleID");
                    String statusID = rs.getString("statusID");
                    String photoUrl = rs.getString("photoUrl");
                    String groupID = rs.getString("groupID");
                    String groupName = rs.getString("groupName");
                    list.add(new GroupDTO(groupID, groupName, userID, username, "", role, gmail, phone, statusID, photoUrl));
                }
            }
        } catch (Exception e) {
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

    public int getGroupIDByUserID(String userID) throws SQLException {
        int groupID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT groupID "
                        + " FROM tblUserGroup "
                        + " WHERE userID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    groupID = rs.getInt("groupID");
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
        return groupID;
    }

    public GroupDTO getGroupByGroupID(int groupID) throws SQLException {
        GroupDTO group = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT groupName, capstoneID, numberOfPerson, statusID "
                        + " FROM tblGroup "
                        + " WHERE groupID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, groupID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String groupName = rs.getString("groupName");
                    int capstoneID = rs.getInt("capstoneID");
                    int numOfPer = rs.getInt("numberOfPerson");
                    int groupStatus = rs.getInt("statusID");
                    group = new GroupDTO(groupID, groupName, capstoneID, numOfPer, groupStatus);
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
        return group;
    }

    public boolean insertUserGroup(List<UserDTO> list, int groupID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblUserGroup(userGroupID, userID, groupID, isSupervisor) "
                        + " VALUES ";

                for (UserDTO user : list) {
                    sql += "(?,?,?,?),";
                }
                sql = sql.substring(0, sql.length() - 1);
                stm = conn.prepareStatement(sql);
                int count = 1;
                int maxUGID = getMaxUserGroupID() + 1;
                for (UserDTO user : list) {
                    stm.setInt(count++, maxUGID++);
                    stm.setString(count++, user.getUserID());
                    stm.setInt(count++, groupID);
                    stm.setInt(count++, 0);
                }
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

    public boolean updateCapstoneGroup(int groupID, String capstoneID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblGroup set capstoneID = ? "
                        + " WHERE groupID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, capstoneID);
                stm.setInt(2, groupID);
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

    public List<UserDTO> getListCapsRandom(int n, String semesterID) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT TOP (?) us.userID, u.name, g.capstoneID "
                        + " FROM tblUserGroup AS us, tblUser AS u, tblGroup AS g "
                        + " WHERE groupID = ? AND us.userID = u.userID AND us.groupID = g.groupID ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, n);
                stm.setString(2, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String name = rs.getString("name");
                    String capstoneID = rs.getString("capstoneID");
                    //list.add(new UserGroup(n, sql, n, n));
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

    public boolean kickMember(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " DELETE tblUserGroup"
                        + " WHERE userID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
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

    public List<GroupDTO> getListUserGroup(String semesterID) throws SQLException {
        List<GroupDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT u.name, g.groupName "
                        + " FROM tblUser u full join tblUserGroup ug on u.userID = ug.userID full join tblGroup g on ug.groupID = g.groupID "
                        + " WHERE u.semesterID = ? AND ug.userID is not null ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userName = rs.getString("name");
                    String groupName = rs.getString("groupName");
                    list.add(new GroupDTO("", groupName, "", userName, "", "", "", "", semesterID, ""));
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

    public boolean inserSupIntoUserGroup(List<String> listSupID, int groupID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblUserGroup(userGroupID, userID, groupID, isSupervisor) "
                        + " VALUES ";
                for (String userID : listSupID) {
                    sql += "(?,?,?,?),";
                }
                sql = sql.substring(0, sql.length() - 1);
                stm = conn.prepareStatement(sql);
                int count = 1;
                int maxUGID = getMaxUserGroupID() + 1;
                for (String userID : listSupID) {
                    stm.setInt(count++, maxUGID++);
                    stm.setString(count++, userID);
                    stm.setInt(count++, groupID);
                    stm.setInt(count++, 1);
                }
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

    public boolean updateStudentStatus(String userID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblUser SET statusID = 2 "
                        + " WHERE userID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                result = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public List<GroupDTO> getListMentorGroup (String userID, String semesterID) throws SQLException {
        List<GroupDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT g.groupName, c.capstoneName, g.numberOfPerson "
                        + " FROM tblUser u left join tblUserGroup ug on u.userID = ug.userID left join tblGroup g on ug.groupID = g.groupID left join tblCapstone c on g.capstoneID = c.capstoneID "
                        + " WHERE u.userID = ? AND c.semesterID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String groupName = rs.getString("groupName");
                    String capstoneName = rs.getString("capstoneName");
                    int numOfPer = rs.getInt("numberOfPerson");
                    list.add(new GroupDTO(groupName, capstoneName, numOfPer));
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
    
    public int getMaxCapstoneID() throws SQLException {
        int capstoneID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT COUNT(c.capstoneID) AS ALLCAPSTONE "
                        + " FROM tblCapstone c"
                        + " WHERE c.statusID = '1' ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    capstoneID = rs.getInt("MAXCAPSTONEID");
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
        return capstoneID;
    }
    public int getCapstoneIDCheck() throws SQLException {
        int capstoneID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT COUNT(c.capstoneID) AS CAPSTONEHADCHECKED "
                        + " FROM tblCapstone c "
                        + " WHERE c.statusID = '0'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    capstoneID = rs.getInt("MAXCAPSTONEID");
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
        return capstoneID;
    }
    
    public int getMaxStudentID() throws SQLException {
        int studentID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT COUNT(u.userID) AS ALLSTUDENT "
                        + " FROM tblUser u "
                        + " WHERE u.roleID = 'LD' OR u.roleID = 'US' AND u.semesterID = 'SP22' ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    studentID = rs.getInt("MAXSTUDENTID");
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
        return studentID;
    }
    public int getStudnentInGroup() throws SQLException {
        int studentID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT COUNT(u.userID) AS USERINGROUP "
                        + " FROM tblUser u "
                        + " WHERE u.statusID = '2'";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    studentID = rs.getInt("USERINGROUP");
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
        return studentID;
    }
}
