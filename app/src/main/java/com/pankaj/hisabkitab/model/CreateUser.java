package com.pankaj.hisabkitab.model;

import java.util.ArrayList;

public class CreateUser {
    private int code;
    private String message;
    private boolean status;
    private ArrayList<CreateUser> data = null;
    private boolean error;
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public ArrayList<CreateUser> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }
}
