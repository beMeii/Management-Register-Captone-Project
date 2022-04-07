/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group;

import user.UserDTO;

/**
 *
 * @author Mai
 */
public class GroupDTO extends UserDTO {

    private String groupID;
    private String groupName;
    private int capstoneID;
    private String capstoneName;
    private int numOfPer;
    private int statusGroupID;
    private String statusID;
    private int statusID1;
    private int groupID1;
    public GroupDTO() {
    }

    public GroupDTO(String groupID, String groupName, String userID, String username, String password, String roleID, String gmail, String phone, String statusID, String photoUrl) {
        super(userID, username, password, roleID, gmail, phone, statusID, photoUrl);
        this.groupID = groupID;
        this.groupName = groupName;
    }

    @Override
    public String getGroupID() {
        return groupID;
    }

    
    public GroupDTO(int numOfPer, int groupID1) {
        this.numOfPer = numOfPer;
        this.groupID1 = groupID1;
    }

    public GroupDTO(int groupID, String groupName, int capstoneID, int numOfPer, int statusID1) {
        this.groupID1 = groupID;
        this.groupName = groupName;
        this.capstoneID = capstoneID;
        this.numOfPer = numOfPer;
        this.statusID1 = statusID1;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    public GroupDTO(String groupName, String capstoneName, int numOfPer) {
        this.groupName = groupName;
        this.capstoneName = capstoneName;
        this.numOfPer = numOfPer;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCapstoneID() {
        return capstoneID;
    }

    public void setCapstoneID(int capstoneID) {
        this.capstoneID = capstoneID;
    }

    public int getNumOfPer() {
        return numOfPer;
    }

    public void setNumOfPer(int numOfPer) {
        this.numOfPer = numOfPer;
    }

    public int getStatusGroupID() {
        return statusGroupID;
    }

    public void setStatusGroupID(int statusID) {
        this.statusGroupID = statusID;
    }

    public int getStatusID1() {
        return statusID1;
    }

    public void setStatusID1(int statusID1) {
        this.statusID1 = statusID1;
    }

    public int getGroupID1() {
        return groupID1;
    }

    public void setGroupID1(int groupID1) {
        this.groupID1 = groupID1;
    }

    public String getCapstoneName() {
        return capstoneName;
    }

    public void setCapstoneName(String capstoneName) {
        this.capstoneName = capstoneName;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    @Override
    public String toString() {
        return "GroupDTO{"
                + "groupID=" + groupID
                + ", groupName=" + groupName
                + ", capstoneID=" + capstoneID
                + ", numOfPer=" + numOfPer
                + ", statusGroupID=" + statusGroupID
                + ", userID=" + userID
                + ", username=" + username
                + ", phone=" + phone
                + ", gmail=" + gmail
                + ", statusID=" + statusID + '}';
    }

}
