package com.guo.pojo;

public class UserVO {

    private Integer userId;
    private String userName;
    private String userPermission;
    private String userEmpno;
    private String userDeliverspot;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public String getUserEmpno() {
        return userEmpno;
    }

    public void setUserEmpno(String userEmpno) {
        this.userEmpno = userEmpno;
    }

    public String getUserDeliverspot() {
        return userDeliverspot;
    }

    public void setUserDeliverspot(String userDeliverspot) {
        this.userDeliverspot = userDeliverspot;
    }
}
