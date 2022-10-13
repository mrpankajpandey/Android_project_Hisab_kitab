package com.pankaj.hisabkitab.model;

import java.util.ArrayList;

public class UserloginResponse {
    public int code;
    public String message;
    public boolean status;
    public ArrayList<User> data;
    public boolean error;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public  ArrayList<User> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }
}
