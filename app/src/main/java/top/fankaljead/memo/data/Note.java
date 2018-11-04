package top.fankaljead.memo.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.Date;

public class Note extends LitePalSupport implements Serializable {

    @Column(defaultValue = "0")
    private Integer tagId;
    private String createTime;
    private String content;
    private String uuid;


    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Note(Integer tagId, String createTime, String content, String uuid) {
        this.tagId = tagId;
        this.createTime = createTime;
        this.content = content;
        this.uuid = uuid;
    }

    public Note() {
    }
}
