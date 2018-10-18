package top.fankaljead.memo.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Tag extends LitePalSupport {
    @Column(unique = true)
    private Integer id;
    private Integer name;
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Tag(Integer id, Integer name, Date createTime) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
    }

    public Tag() {
    }
}
