package top.fankaljead.memo.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class User extends LitePalSupport  implements Serializable {
    @Column(unique = true)
    private Integer id;
    private String name;
    private String email;
    private String tel;
    private String password;
    private String headPicPath;


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

    public User(Integer id, String name, String email, String tel, String password, String headPicPath) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.headPicPath = headPicPath;
    }
}
