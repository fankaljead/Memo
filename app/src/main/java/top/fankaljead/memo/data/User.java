package top.fankaljead.memo.data;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class User extends LitePalSupport  implements Serializable {
    public static final Integer LOGIN = 1;
    public static final Integer UN_LOGIN = 0;
    private String uuid;
    private String name;
    private String email;
    private String tel;
    private String password;
    private String headPicPath;
    private Integer isLogin;

    public Integer getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String id) {
        this.uuid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPicPath() {
        return headPicPath;
    }

    public void setHeadPicPath(String headPicPath) {
        this.headPicPath = headPicPath;
    }

    public User() {
    }

    public User(String id, String name, String email, String tel, String password, String headPicPath) {
        this.uuid = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.headPicPath = headPicPath;
        this.isLogin = UN_LOGIN;
    }
}
