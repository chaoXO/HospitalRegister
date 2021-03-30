package cn.edu.zuel.demo4.model;

public class Doctor {
    private Integer id;
    private String name;
    private String password;
    private Integer sex;
    private Integer deptId;

    private String deptName;

    public Doctor(){

    }

    public Doctor(Integer id, String name, String password, Integer sex, Integer deptId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.deptId = deptId;
    }

    public Doctor(Integer id, String name, String password, Integer sex,
                  Integer deptId, String deptName) {
        this(id,name,password,sex,deptId);
        this.deptName = deptName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    //ov字段
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("[id=").append(this.getId()).append(", name=").append(this.getName())
                .append(", sex=").append(this.getSex()).append(", password=").append(this.getPassword())
                .append(", dept_id=").append(this.getDeptId()).append(", deptName=").append(this.getDeptName())
                .append("]");
        return str.toString();
    }
}
