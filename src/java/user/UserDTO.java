/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.Serializable;

/**
 *
 * @author mac
 */
public class UserDTO implements Serializable{
    public String STT;
    public String userID;
    public String username;
    public String password;
    public String roleID;
    public String gmail;
    public String phone;
 
    public String statusID;
    public String photoUrl;
    
    public String semesterName;
    public String capstoneName;
    
    public String groupID;
    public String groupName;
    public String AmountGroup;
            
    public UserDTO() {
    }

    public UserDTO(String STT, String userID, String username, String password, String roleID, String gmail, String phone, String statusID, String photoUrl, String semesterName, String capstoneName, String groupID, String groupName, String AmountGroup) {
        this.STT = STT;
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.roleID = roleID;
        this.gmail = gmail;
        this.phone = phone;
        this.statusID = statusID;
        this.photoUrl = photoUrl;
        this.semesterName = semesterName;
        this.capstoneName = capstoneName;
        this.groupID = groupID;
        this.groupName = groupName;
        this.AmountGroup = AmountGroup;
    }
    
    public UserDTO(String stt,String userID, String username, String password, String roleID, String gmail, String phone, String statusID, String photoUrl) {
        this.STT = stt;
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.roleID = roleID;
        this.gmail = gmail;
        this.phone = phone;
       
        this.statusID = statusID;
        this.photoUrl = photoUrl;
    }
    public UserDTO(String userID, String username, String password, String roleID, String gmail, String phone, String statusID, String photoUrl) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.roleID = roleID;
        this.gmail = gmail;
        this.phone = phone;
       
        this.statusID = statusID;
        this.photoUrl = photoUrl;
    }

    public UserDTO(String username, String roleID, String gmail, String phone, String statusID, String groupID) {
        this.username = username;
        this.roleID = roleID;
        this.gmail = gmail;
        this.phone = phone;
        this.statusID = statusID;
        this.groupID = groupID;
    }
 
    public UserDTO(String userID, String username, String roleID, String gmail, String phone) {
        this.userID = userID;
        this.username = username;
        this.roleID = roleID;
        this.gmail = gmail;
        this.phone = phone;
    }
    
    public UserDTO(String userID, String username, String gmail, String phone) {
        this.userID = userID;
        this.username = username;
        this.gmail = gmail;
        this.phone = phone;
    }

    public UserDTO(String userID, String semesterName) {
        this.userID = userID;
        this.semesterName = semesterName;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getAmountGroup() {
        return AmountGroup;
    }

    public void setAmountGroup(String AmountGroup) {
        this.AmountGroup = AmountGroup;
    }

    public UserDTO(String STT, String userID, String username, String roleID, String gmail, String statusID, String capstoneName, String groupID, String groupName, String AmountGroup) {
        this.STT = STT;
        this.userID = userID;
        this.username = username;
        this.roleID = roleID;
        this.gmail = gmail;
        this.statusID = statusID;
        this.capstoneName = capstoneName;
        this.groupID = groupID;
        this.groupName = groupName;
        this.AmountGroup = AmountGroup;
    }
    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getCapstoneName() {
        return capstoneName;
    }

    public void setCapstoneName(String capstoneName) {
        this.capstoneName = capstoneName;
    }

    public UserDTO(String userID, String username, String gmail, String phone, String photoUrl, String semesterName, String capstoneName) {
        this.userID = userID;
        this.username = username;
        this.gmail = gmail;
        this.phone = phone;
        this.photoUrl = photoUrl;
        this.semesterName = semesterName;
        this.capstoneName = capstoneName;
    }
   
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSTT(String STT) {
        this.STT = STT;
    }

    public String getSTT() {
        return STT;
    }

   

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "STT=" + STT + ", userID=" + userID + ", username=" + username + ", password=" + password + ", roleID=" + roleID + ", gmail=" + gmail + ", phone=" + phone + ", statusID=" + statusID + ", photoUrl=" + photoUrl + ", semesterName=" + semesterName + ", capstoneName=" + capstoneName + ", groupID=" + groupID + ", groupName=" + groupName + ", AmountGroup=" + AmountGroup + '}';
    }

    

}
