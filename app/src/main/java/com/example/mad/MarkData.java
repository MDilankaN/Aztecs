package com.example.mad;

public class MarkData {

    private String stuName;
    private double Totalmark;

    public MarkData(String stuName, double totalmark) {
        this.stuName = stuName;
        Totalmark = totalmark;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public double getTotalmark() {
        return Totalmark;
    }

    public void setTotalmark(double totalmark) {
        Totalmark = totalmark;
    }


}
