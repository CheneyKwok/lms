package com.guo.pojo;

import java.util.Date;

public class Receipt {
    private Integer receiptId;
    private String receiptNo;
    private String carNo;
    private Integer deliverspotId;
    private String deliverspot;
    private Integer empId;
    private String empName;
    private String createtime;
    private String departTime;
    private String remark;
    private Integer flag;
    private String type;

    public String getDeliverspot() {
        return deliverspot;
    }

    public void setDeliverspot(String deliverspot) {
        this.deliverspot = deliverspot;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
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


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
