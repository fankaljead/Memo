package top.fankaljead.memo.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Note extends LitePalSupport {
    @Column(unique = true)
    private Integer id;
    @Column(defaultValue = "0")
    private Integer tagId;
    private Date createTime;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Note(Integer id, Integer tagId, Date createTime, String content) {
        this.id = id;
        this.tagId = tagId;
        this.createTime = createTime;
        this.content = content;
    }

    public Note() {
    }
}
