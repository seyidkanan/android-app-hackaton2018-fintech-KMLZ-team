package com.kmlz.optcredit.network.responses;

public class MainResponse {

    private String code;

    private String token;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "MainResponse{" +
                "code='" + code + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
