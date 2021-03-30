package cn.edu.zuel.demo4.model;

import java.math.BigDecimal;
import java.sql.Date;

public class RegRecord {
    private Integer id;
    private Integer patientId;
    private Integer deptId;
    private Date regTime;
    private BigDecimal price;
    private String patientName;
    private String deptName;
    public RegRecord() {

    }

    public RegRecord(Integer id, Integer patientId, Integer deptId, Date regTime, BigDecimal price) {
        this.id = id;
        this.patientId = patientId;
        this.deptId = deptId;
        this.regTime = regTime;
        this.price = price;
    }

    public RegRecord(Integer id, Integer patientId, Integer deptId, Date regTime, BigDecimal price
            , String patientName, String deptName) {
        this(id,patientId,deptId,regTime,price);
        this.patientName = patientName;
        this.deptName = deptName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("[id=").append(this.getId()).append(", patient_id=")
                .append(this.getPatientId()).append(", dept_id=").append(this.getDeptId())
                .append(", reg_time=").append(this.getRegTime()).append(", price=").append(this.getPrice())
                .append(", patient_name= ").append(this.getPatientName()).append(", dept_name=")
                .append(this.getDeptName()).append("]");
        return str.toString();
    }

}
