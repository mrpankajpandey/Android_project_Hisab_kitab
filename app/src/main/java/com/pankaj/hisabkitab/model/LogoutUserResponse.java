package com.pankaj.hisabkitab.model;

import java.util.ArrayList;

public class LogoutUserResponse {
    private int code;
    private String message;
    private boolean status;
    private ArrayList<LogoutUserResponse> data = null;
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

    public ArrayList<LogoutUserResponse> getData() {
        return data;
    }

    public boolean isError() {
        return error;
    }
}
