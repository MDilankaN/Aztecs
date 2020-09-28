package com.example.mad;

public class Result {
    private String SName;
    private  int SResult;

    public Result() {
    }

    public Result(String SName, int SResult) {
        this.SName = SName;
        this.SResult = SResult;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public int getSResult() {
        return SResult;
    }

    public void setSResult(int SResult) {
        this.SResult = SResult;
    }
}
