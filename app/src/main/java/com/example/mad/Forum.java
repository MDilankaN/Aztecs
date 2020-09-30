package com.example.mad;

public class Forum {

    private Integer ID;
    private String stdusername;
    private String message;
    private Integer code;
    private String reply;
    private String tusername;

    public Forum() {
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getStdusername() {
        return stdusername;
    }

    public void setStdusername(String stdusername) {
        this.stdusername = stdusername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getTusername() {
        return tusername;
    }

    public void setTusername(String tusername) {
        this.tusername = tusername;
    }
}
