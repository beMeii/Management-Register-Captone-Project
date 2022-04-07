/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package request;

/**
 *
 * @author ASUS
 */
public class RequestDTO {
    int requestID;
    String invitedID;//ID của người được mời
    String userID; //ID của người mời (LoginUser)
    String requestDetail; //GroupID/CapstoneID
    int statusID;

    public RequestDTO() {
    }

    public RequestDTO(int requestID, String invitedID, String userID, String requestDetail, int statusID) {
        this.requestID = requestID; 
        this.invitedID = invitedID;
        this.userID = userID;
        this.requestDetail = requestDetail;
        this.statusID = statusID;
    }

    public String getInvitedID() {
        return invitedID;
    }

    public void setInvitedID(String invitedID) {
        this.invitedID = invitedID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getRequestDetail() {
        return requestDetail;
    }

    public void setRequestDetail(String requestDetail) {
        this.requestDetail = requestDetail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }   
}
