package org.nju.sesta.sams.response.message;

import java.util.Calendar;

public class MessageResponse {

    private Long id;

    private String title;

    private String content;

    private Calendar releasedTime;

    private Boolean isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(Calendar releasedTime) {
        this.releasedTime = releasedTime;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }


}
