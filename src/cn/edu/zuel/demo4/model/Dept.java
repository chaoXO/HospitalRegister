package cn.edu.zuel.demo4.model;

public class Dept {
    private Integer id;
    private String name;
    private Integer type;
    private String remark;

    public Dept() {

    }

    public Dept(int id, String name, int type, String remark) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("[id=").append(this.getId()).append(", name=").append(this.getName())
                .append(", type=").append(this.getType()).append(", remark=").append(this.getRemark()).append("]");
        return str.toString();
    }
}
