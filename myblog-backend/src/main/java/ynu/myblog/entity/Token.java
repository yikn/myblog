package ynu.myblog.entity;

public class Token {

    private int userid;
    private String token;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int user_id) {
        this.userid = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
