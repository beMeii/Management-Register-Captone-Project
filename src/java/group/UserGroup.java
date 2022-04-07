/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import user.UserDTO;

/**
 *
 * @author Mai
 */
public class UserGroup {
    private int userGroupID;
    private String userID;
    private int groupID;
    private int isSupervisor;

    public UserGroup(int userGroupID, String userID, int groupID, int isSupervisor) {
        this.userGroupID = userGroupID;
        this.userID = userID;
        this.groupID = groupID;
        this.isSupervisor = isSupervisor;
    }

    public int getUserGroupID() {
        return userGroupID;
    }

    public void setUserGroupID(int userGroupID) {
        this.userGroupID = userGroupID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }  

    public int getIsSupervisor() {
        return isSupervisor;
    }

    public void setIsSupervisor(int isSupervisor) {
        this.isSupervisor = isSupervisor;
    }
    
}
