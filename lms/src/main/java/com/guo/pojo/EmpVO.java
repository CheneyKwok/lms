package com.guo.pojo;

import sun.swing.plaf.synth.DefaultSynthStyle;

import java.security.PrivateKey;

public class EmpVO {
    private Integer empid;
    private String empNo;
    private String empName;
    private String gender;
    private Integer age;
    private String position;

    private String tel;
    private String startworktime;
    private String salary;
    private String cardId;
    private String email;
    private String deliverspot;
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }



    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStartworktime() {
        return startworktime;
    }

    public void setStartworktime(String startworktime) {
        this.startworktime = startworktime;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliverspot() {
        return deliverspot;
    }

    public void setDeliverspot(String deliverspot) {
        this.deliverspot = deliverspot;
    }
}
