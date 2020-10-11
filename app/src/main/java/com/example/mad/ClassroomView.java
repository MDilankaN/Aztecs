package com.example.mad;

/*public class ClassroomView {
            private String name;
            private  String code;
            private String description;


    public ClassroomView() {
    }

    public ClassroomView(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
*/
public class ClassroomView {

    private String name;
    private Integer code;
    private String description;
    private String date;
    private  String teacherID;

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }


    public ClassroomView(String name, Integer code, String description, String date, String teacherID) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.date = date;
        this.teacherID=teacherID;
    }

    public ClassroomView() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}



