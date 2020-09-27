package com.example.login;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

//    SharedPreferences LoginPreferences;
//    SharedPreferences.Editor editor;
//    Context context;

    private static String uname;
    private static String e_mail;
    private static String pwd;
    private static String type;


    public SessionManager() {}

    public SessionManager(String un,String email,String pwd,String type) {
        this.uname = un;
        this.e_mail = email;
        this.pwd =pwd;
        this.type = type;

    }

    public static String getUname() {
        return uname;
    }


    public static void setUname(String uname) {
        SessionManager.uname = uname;
    }

    public static String getE_mail() {
        return e_mail;
    }

    public static void setE_mail(String e_mail) {
        SessionManager.e_mail = e_mail;
    }

    public static String getPwd() {
        return pwd;
    }

    public static void setPwd(String pwd) {
        SessionManager.pwd = pwd;
    }
    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        SessionManager.type = type;
    }
}
