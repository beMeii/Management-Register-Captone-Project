/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upload;

import capstone.CapstoneDTO;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import semester.SemesterDAO;
import semester.SemesterDTO;
import user.UserDTO;
import utils.DBUtils;

/**
 *
 * @author Mai
 */
public class UploadDAO {

    public List<UserDTO> listUser;

    public List<UserDTO> getListUser() {
        return listUser;
    }
    public List<CapstoneDTO> listCapstone;

    public List<CapstoneDTO> getListCapstone() {
        return listCapstone;
    }

    public int readFile_Student(String filename) throws FileNotFoundException, IOException {
        FileInputStream excelFile = new FileInputStream(new File(filename));
        Workbook workBook = null;
        if (filename.endsWith("xlsx")) {
            workBook = new XSSFWorkbook(excelFile);
        } else if (filename.endsWith("xls")) {
            workBook = new HSSFWorkbook(excelFile);
        }
        Sheet datatypeSheet = workBook.getSheetAt(0);
        DataFormatter fmt = new DataFormatter();
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        try {
            int count = 0;
            while (iterator.hasNext()) {
                UserDTO user = new UserDTO();
                Row currentRow = iterator.next();
                String userID = currentRow.getCell(0).getStringCellValue();
                user.setUserID(userID);
                String userName = currentRow.getCell(1).getStringCellValue();
                user.setUsername(userName);
                String gmail = currentRow.getCell(2).getStringCellValue();
                user.setGmail(gmail);
                String roleID = currentRow.getCell(3).getStringCellValue();
                user.setRoleID(roleID);
                String semesterID = currentRow.getCell(4).getStringCellValue();
                user.setSemesterName(semesterID);
                if (this.listUser == null) {
                    this.listUser = new ArrayList<>();
                }
                this.listUser.add(user);
                count++;
            }
            if (count == 0) {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workBook != null) {
                workBook.close();
            }
            if (excelFile != null) {
                excelFile.close();
            }
        }
        return 1;
    }

    public boolean pushExcelList(List<UserDTO> list) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblUser(userID, name, gmail, statusID, roleID, semesterID)"
                        + " VALUES(?,?,?,?,?,?)";
                stm = conn.prepareStatement(sql);
                for (UserDTO user : list) {
                    stm.setString(1, user.getUserID());
                    stm.setString(2, user.getUsername());
                    stm.setString(3, user.getGmail());
                    stm.setInt(4, 3);
                    stm.setString(5, user.getRoleID());
                    stm.setString(6, user.getSemesterName());
                    check = stm.executeUpdate() > 0 ? true : false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                stm.close();
            }
        }
        return check;
    }

    public int getMaxUserCapstoneID() throws SQLException {
        int userCapstoneID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT MAX(userCapstoneID) AS MAXUSERCAPSTONEID "
                        + " FROM tblUserCapstone ";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    userCapstoneID = rs.getInt("MAXUSERCAPSTONEID");
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
        return userCapstoneID;
    }

    public int getMaxCapstoneID() throws SQLException {
        int capstoneID = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT MAX(capstoneID) AS MAXCAPSTONEID "
                        + " FROM tblCapstone ";
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

    public boolean pushExcelListCap(List<CapstoneDTO> list) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblCapstone(capstoneID, capstoneName, semesterID, statusID)"
                        + " VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                SemesterDAO daoSe = new SemesterDAO();
                SemesterDTO semesterCurrent = daoSe.getSemesterV2();
                int capstoneID = getMaxCapstoneID() + 1;
//                for (CapstoneDTO caps : list) {
//                    if (caps.getCapstoneName().equalsIgnoreCase(list.get(list.indexOf(caps)+1).getCapstoneName())) {
//                        
//                    }
//                    stm.setInt(1, capstoneID);
//                    stm.setString(2, caps.getCapstoneName());
//                    stm.setString(3, semesterCurrent.getSemesterID());
//                    stm.setInt(4, 1);
//                    capstoneID++;
//                    check = stm.executeUpdate() > 0 ? true : false;
//                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCapstoneName().equalsIgnoreCase(list.get(i+1).getCapstoneName())) {
                        i++;
                    }
                    stm.setInt(1, capstoneID);
                    stm.setString(2, list.get(i).getCapstoneName());
                    stm.setString(3, semesterCurrent.getSemesterID());
                    stm.setInt(4, 1);
                    capstoneID++;
                    check = stm.executeUpdate() > 0 ? true : false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                stm.close();
            }
        }
        return check;
    }

    public boolean pushExcelListUserCap(List<CapstoneDTO> list) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " INSERT INTO tblUserCapstone(userCapstoneID, userID, capstoneID, isSupervisor)"
                        + " VALUES(?,?,?,?)";
                stm = conn.prepareStatement(sql);
                int userCapstoneID = getMaxUserCapstoneID() + 1;
                int capstoneID = getMaxCapstoneID();
                for (CapstoneDTO caps : list) {
                    stm.setInt(1, userCapstoneID);
                    stm.setString(2, caps.getUserID());
                    stm.setInt(3, capstoneID);
                    stm.setInt(4, 1);
                    userCapstoneID++;
                    check = stm.executeUpdate() > 0 ? true : false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                stm.close();
            }
        }
        return check;
    }

    public int readFile_Capstone(String filename) throws FileNotFoundException, IOException {
        FileInputStream excelFile = new FileInputStream(new File(filename));
        Workbook workBook = null;
        if (filename.endsWith("xlsx")) {
            workBook = new XSSFWorkbook(excelFile);
        } else if (filename.endsWith("xls")) {
            workBook = new HSSFWorkbook(excelFile);
        }
        Sheet datatypeSheet = workBook.getSheetAt(0);
        DataFormatter fmt = new DataFormatter();
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        try {
            int count = 0;
            while (iterator.hasNext()) {
                if (this.listCapstone == null) {
                    this.listCapstone = new ArrayList<>();
                }
                // capstoneName, semesterID, userID)
                CapstoneDTO cap = new CapstoneDTO();
                Row currentRow = iterator.next();
                String capstoneName = currentRow.getCell(0).getStringCellValue();
                cap.setCapstoneName(capstoneName);
                String semesterID = currentRow.getCell(1).getStringCellValue();
                cap.setSemesterName(semesterID);
                String userID = currentRow.getCell(2).getStringCellValue();
                cap.setUserID(userID);
                this.listCapstone.add(cap);
                count++;
            }
            if (count == 0) {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workBook != null) {
                workBook.close();
            }
            if (excelFile != null) {
                excelFile.close();
            }
        }
        return 1;
    }
}
