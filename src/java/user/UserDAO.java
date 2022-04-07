/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author mac
 */
public class UserDAO {

    public UserDTO checkLogin(String gmail, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT u.name, u.roleID, u.userID, s.statusID, u.photoUrl, u.phone "
                        + "FROM tblUser u, tblStatus s "
                        + "WHERE u.statusID = s.statusID AND gmail=? AND password=? AND s.statusID != 0 ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, gmail);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("name");
                    String roleID = rs.getString("roleID");
                    String userID = rs.getString("userID");
                    String statusID = rs.getString("statusID");
                    String photo = rs.getString("photoUrl");
                    String phone = rs.getString("phone");
                    user = new UserDTO("", userID, username, password, roleID, gmail, phone, statusID, photo);
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
        return user;
    }

    public UserDTO checkGmail(String gmail) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT u.name, u.password, u.roleID, u.userID, s.statusID, u.photoUrl, u.phone "
                        + "FROM tblUser u, tblStatus s "
                        + "WHERE u.statusID = s.statusID AND gmail=? AND s.statusID != 0 ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, gmail);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("name");
                    String password = rs.getString("password");
                    String roleID = rs.getString("roleID");
                    String userID = rs.getString("userID");
                    String statusID = rs.getString("statusID");
                    String photoUrl = rs.getString("photoUrl");
                    String phone = rs.getString("phone");
//                    String deadline = rs.getString("deadline");
                    user = new UserDTO("", userID, username, password, roleID, gmail, phone, statusID, photoUrl);
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
        return user;
    }

    public UserDTO checkLoginGG(String gmail) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT u.name, u.password, u.roleID, u.userID, s.statusID, u.photoUrl, u.phone "
                        + "FROM tblUser u, tblStatus s, tblSemester st "
                        + "WHERE u.statusID = s.statusID AND gmail=? AND s.statusID != 0 AND u.semesterID = st.semesterID AND st.NO = (SELECT MAX(NO) AS STT FROM tblSemester) ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, gmail);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("name");
                    String password = rs.getString("password");
                    String roleID = rs.getString("roleID");
                    String userID = rs.getString("userID");
                    String statusID = rs.getString("statusID");
                    String photoUrl = rs.getString("photoUrl");
                    String phone = rs.getString("phone");
                    user = new UserDTO("", userID, username, password, roleID, gmail, phone, statusID, photoUrl);
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
        return user;
    }

    public List<UserDTO> getListStudent() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT userID, name, gmail, phone, photoUrl, statusID "
                        + " FROM tblUser "
                        + " WHERE [roleID] = 'US' ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String username = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String photoUrl = rs.getString("photoUrl");

                    String statusID = rs.getString("statusID");
                    list.add(new UserDTO("", userID, username, "", "US", gmail, phone, statusID, photoUrl));
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

    public List<UserDTO> getListSupervisor() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT userID, name, gmail, phone, photoUrl, statusID "
                        + " FROM tblUser "
                        + " WHERE [roleID] = 'MT' ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userID = rs.getString("userID");
                    String username = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String photoUrl = rs.getString("photoUrl");

                    String statusID = rs.getString("statusID");
                    list.add(new UserDTO("", userID, username, "", "MT", gmail, phone, statusID, photoUrl));
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

    public List<UserDTO> getListStudentNoGroup(String semesterID) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT ROW_NUMBER() OVER (ORDER BY userID) AS STT, userID, name, gmail, phone, photoUrl, statusID "
                        + " FROM tblUser "
                        + " WHERE [statusID] = '3' AND semesterID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, semesterID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String stt = rs.getString("STT");
                    String userID = rs.getString("userID");
                    String username = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String photoUrl = rs.getString("photoUrl");
                    String statusID = rs.getString("statusID");
                    list.add(new UserDTO(stt, userID, username, gmail, statusID, semesterID, "", "", "", photoUrl));
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

    public UserDTO getInforUser(String userIDValue) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT tblUser.userID, tblUser.name, tblUser.gmail, tblUser.phone,"
                        + " tblUser.photoUrl, tblSemester.semesterName,tblCapstone.capstoneName"
                        + " FROM tblUser Left Join tblUserGroup ON tblUser.userID = tblUserGroup.userID  "
                        + " Left Join tblGroup ON tblUserGroup.groupID = tblGroup.groupID  Left Join tblCapstone on tblGroup.capstoneID = tblCapstone.capstoneID  "
                        + " Left Join tblSemester ON tblUser.semesterID = tblSemester.semesterID where tblUser.userID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userIDValue);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("userID");
                    String name = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String photoUrl = rs.getString("photoUrl");
                    String semesterName = rs.getString("semesterName");
                    String capstoneName = rs.getString("capstoneName");
                    user = new UserDTO(userID, name, gmail, phone, photoUrl, semesterName, capstoneName);
                    if (user != null) {
                        return user;
                    }
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
        return null;

    }

    public boolean updateInfor(String name, String phone, String photoUrl, String userID) throws SQLException {
        int row = 0;
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            String sql = "UPDATE tblUser \n"
                    + "SET name = ? , phone = ?, photoUrl = ? "
                    + "WHERE userID = ? ";
            stm = conn.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, phone);
            stm.setString(3, photoUrl);
            stm.setString(4, userID);
            row = stm.executeUpdate();
            if (row > 0) {
                return true;
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
        return false;
    }

    public int getNoOfRecordsSearchAdmin(int check, String semesterID, String nameSearch) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = null;

                if (check == 1) {
                    sql = "SELECT count(*) as noRecord \n"
                            + "FROM tblUser \n"
                            + "WHERE  (tblUser.roleID = 'US' OR tblUser.roleID = 'LD') AND tblUser.semesterID = ? AND tblUser.name like ? ";

                } else if (check == 0) {
                    sql = "SELECT count(*) as noRecord "
                            + "FROM tblUser\n"
                            + "LEFT JOIN tblUserGroup tblUserGroup ON tblUserGroup.userID = tblUser.userID\n"
                            + "LEFT JOIN tblSemester tblSemester ON tblSemester.semesterID = tblUser.semesterID\n"
                            + "WHERE tblUserGroup.userID IS NULL AND (tblUser.roleID = 'US' OR tblUser.roleID = 'LD') AND tblUser.semesterID = ? AND tblUser.name like ? ";

                }
                stm = conn.prepareStatement(sql);
                stm.setString(1, semesterID);
                stm.setString(2, "%" + nameSearch + "%");
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("noRecord");
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
        return result;
    }

    public List<UserDTO> getUserSearch(int pagesize, int pageNumber, int check, String semesterID, String nameSearch) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = null;
                if (check == 1) {
                    sql = "SELECT ROW_NUMBER() OVER (ORDER BY tblUser.userID) AS STT, tblUser.userID, tblUser.name, tblUser.gmail, tblUser.phone, tblUser.photoUrl, tblUser.statusID, tblUser.roleID \n"
                            + "FROM tblUser\n"
                            + "LEFT JOIN tblSemester tblSemester ON tblSemester.semesterID = tblUser.semesterID\n"
                            + "WHERE (tblUser.roleID = 'US' OR tblUser.roleID = 'LD') AND tblUser.semesterID = ? AND tblUser.name like ? "
                            + "ORDER BY (SELECT NULL)"
                            + "OFFSET ? * (? - 1) ROWS "
                            + "FETCH NEXT ? ROWS ONLY ";

                } else if (check == 0) {
                    sql = "SELECT ROW_NUMBER() OVER (ORDER BY tblUser.userID) AS STT, tblUser.userID, tblUser.name, tblUser.gmail, tblUser.phone, tblUser.photoUrl, tblUser.statusID, tblUser.roleID \n"
                            + "FROM tblUser\n"
                            + "LEFT JOIN tblUserGroup tblUserGroup ON tblUserGroup.userID = tblUser.userID\n"
                            + "LEFT JOIN tblSemester tblSemester ON tblSemester.semesterID = tblUser.semesterID\n"
                            + "WHERE tblUserGroup.userID IS NULL AND (tblUser.roleID = 'US' OR tblUser.roleID = 'LD') AND tblUser.semesterID = ? AND tblUser.name like ? "
                            + "ORDER BY (SELECT NULL)"
                            + "OFFSET ? * (? - 1) ROWS "
                            + "FETCH NEXT ? ROWS ONLY ";

                }
                stm = conn.prepareStatement(sql);
                stm.setInt(3, pagesize);
                stm.setInt(4, pageNumber);
                stm.setInt(5, pagesize);
                stm.setString(1, semesterID);
                stm.setString(2, "%" + nameSearch + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String stt = rs.getString("STT");
                    String userID = rs.getString("userID");
                    String username = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String photoUrl = rs.getString("photoUrl");
                    String roleID = rs.getString("roleID");
                    String statusID = rs.getString("statusID");
                    list.add(new UserDTO(stt, userID, username, "", roleID, gmail, phone, statusID, photoUrl));
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

    public UserDTO getUserByUserID(String userID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT u.name, u.gmail, u.phone, u.statusID, u.roleID, ug.groupID "
                        + " FROM tblUser u left join tblUserGroup ug on u.userID = ug.userID "
                        + " WHERE u.userID like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userName = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String phone = rs.getString("phone");
                    String statusID = rs.getString("statusID");
                    String roleID = rs.getString("roleID");
                    String groupID = rs.getString("groupID");
                    user = new UserDTO(userName, roleID, gmail, phone, statusID, groupID);
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
        return user;
    }

    public int getNoOfRecordsSupervisor(int check) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = null;

                if (check == 1) {
                    sql = "Select count(*) as noRecord from (SELECT tb1.userID, tb1.name,tb1.gmail, tb1.statusID\n"
                            + "FROM tblUser tb1 LEFT JOIN tblUserGroup tb2 ON tb1.userID = tb2.userID\n"
                            + "Left Join tblUserCapstone tb3 ON tb1.userID = tb3.userID \n"
                            + "Left Join tblCapstone tb4 ON tb3.capstoneID = tb4.capstoneID\n"
                            + "Left Join tblGroup tb5 ON tb4.groupID = tb5.groupID\n"
                            + "WHERE tb1.roleID = 'MT'\n"
                            + "GROUP BY tb1.userID, tb1.name,tb1.gmail,tb1.statusID\n"
                            + "HAVING COUNT (tb2.userID) = 5) tableCount";
                } else if (check == 0) {
                    sql = "Select count(*) as noRecord from (SELECT tb1.userID, tb1.name,tb1.gmail, tb1.statusID\n"
                            + "FROM tblUser tb1 LEFT JOIN tblUserGroup tb2 ON tb1.userID = tb2.userID\n"
                            + "Left Join tblUserCapstone tb3 ON tb1.userID = tb3.userID \n"
                            + "Left Join tblCapstone tb4 ON tb3.capstoneID = tb4.capstoneID\n"
                            + "Left Join tblGroup tb5 ON tb4.groupID = tb5.groupID\n"
                            + "WHERE tb1.roleID = 'MT'\n"
                            + "GROUP BY tb1.userID, tb1.name,tb1.gmail,tb1.statusID\n"
                            + "HAVING COUNT (tb2.userID) < 5) tableCount";
                }
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("noRecord");
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
        return result;
    }

    public List<UserDTO> getSupervisorSearch(int pagesize, int pageNumber, int check) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = null;
                if (check == 1) {
                    sql = "SELECT ROW_NUMBER() OVER (ORDER BY tb1.userID) AS STT,tb1.userID, tb1.name,tb1.gmail, tb1.statusID,tb4.capstoneName,tb5.groupID, tb5.groupName, COUNT (tb2.userID) AS AmountGroup\n"
                            + "FROM (tblUser tb1 LEFT JOIN tblUserGroup tb2 ON tb1.userID = tb2.userID \n"
                            + "Left Join tblUserCapstone tb3 ON tb1.userID = tb3.userID \n"
                            + "Left Join tblCapstone tb4 ON tb3.capstoneID = tb4.capstoneID\n"
                            + "Left Join tblGroup tb5 ON tb4.groupID = tb5.groupID\n"
                            + ")"
                            + "WHERE tb1.roleID = 'MT'\n"
                            + "GROUP BY tb1.userID, tb1.name,tb1.gmail,tb1.statusID,tb4.capstoneName,tb5.groupID, tb5.groupName HAVING COUNT (tb2.userID) = 5 \n"
                            + "ORDER BY (SELECT NULL)\n";
//                            + "OFFSET ? * (? - 1) ROWS \n"
//                            + "FETCH NEXT ? ROWS ONLY ";

                } else if (check == 0) {
                    sql = "SELECT ROW_NUMBER() OVER (ORDER BY tb1.userID) AS STT, tb1.userID, tb1.name,tb1.gmail, tb1.statusID,tb4.capstoneName,tb5.groupID, tb5.groupName, COUNT (tb2.userID) AS AmountGroup\n"
                            + "FROM (tblUser tb1 LEFT JOIN tblUserGroup tb2 ON tb1.userID = tb2.userID \n"
                            + "Left Join tblUserCapstone tb3 ON tb1.userID = tb3.userID \n"
                            + "Left Join tblCapstone tb4 ON tb3.capstoneID = tb4.capstoneID\n"
                            + "Left Join tblGroup tb5 ON tb4.groupID = tb5.groupID\n"
                            + ")"
                            + "WHERE tb1.roleID = 'MT'\n"
                            + "GROUP BY tb1.userID, tb1.name,tb1.gmail,tb1.statusID,tb4.capstoneName,tb5.groupID, tb5.groupName HAVING COUNT (tb2.userID) < 5 \n"
                            + "ORDER BY (SELECT NULL)\n";
//                            + "OFFSET ? * (? - 1) ROWS \n"
//                            + "FETCH NEXT ? ROWS ONLY ";

                }
                stm = conn.prepareStatement(sql);
//                stm.setInt(1, pagesize);
//                stm.setInt(2, pageNumber);
//                stm.setInt(3, pagesize);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String stt = rs.getString("STT");
                    String userID = rs.getString("userID");
                    String username = rs.getString("name");
                    String gmail = rs.getString("gmail");
                    String statusID = rs.getString("statusID");
//                    String capstoneName = rs.getString("capstoneName");
//                    String groupID = rs.getString("groupID");
//                    String groupName = rs.getString("groupName");
                    String amountGroup = rs.getString("AmountGroup");
                    list.add(new UserDTO(stt, userID, username, "US", gmail, statusID, "", "", "", amountGroup));
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

    public boolean updateStudentRedundant(String userID, String sesmesterID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblUser SET semesterID=?, statusID = 3 "
                        + " WHERE userID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, sesmesterID);
                stm.setString(2, userID);
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

    public boolean updateStatusID(String userID, int statusID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblUser set statusID = ? "
                        + " WHERE userID = ? ";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, statusID);
                stm.setString(2, userID);
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

    public boolean updateRoleID(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblUser set roleID = 'LD' "
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
}
