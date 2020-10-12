package com.example.mad;

public class StudentForum {
    private String stdusername;
    private String reply;
    private String Quection;

    public StudentForum(String stdusername, String reply, String quection) {
        this.stdusername = stdusername;
        this.reply = reply;
        Quection = quection;
    }

    public StudentForum() {
    }

    public String getStdusername() {
        return stdusername;
    }

    public void setStdusername(String stdusername) {
        this.stdusername = stdusername;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getQuection() {
        return Quection;
    }

    public void setQuection(String quection) {
        Quection = quection;
    }
}
