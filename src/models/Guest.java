package models;

public class Guest {
    private int id;
    private String userMobile;
    private String name;
    private String mobile;
    private boolean withFamily;

    public Guest() {
    }

    public Guest(int id, String userMobile, String name, String mobile, boolean withFamily) {
        this.id = id;
        this.userMobile = userMobile;
        this.name = name;
        this.mobile = mobile;
        this.withFamily = withFamily;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isWithFamily() {
        return withFamily;
    }

    public void setWithFamily(boolean withFamily) {
        this.withFamily = withFamily;
    }
}
