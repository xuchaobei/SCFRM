package cn.gov.scciq.bussiness.example;

public class UserBean{
    private int id;
    private String name = "attr";
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String attr) {
        this.name = attr;
    }
}
