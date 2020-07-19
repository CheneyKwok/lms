package com.guo.pojo;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private Integer permissionId;
    private Integer deliverspotId;
    private Integer empId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getDeliverspotId() {
        return deliverspotId;
    }

    public void setDeliverspotId(Integer deliverspotId) {
        this.deliverspotId = deliverspotId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
}
