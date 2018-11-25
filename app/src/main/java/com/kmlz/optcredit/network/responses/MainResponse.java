package com.kmlz.optcredit.network.responses;

public class MainResponse {

    private String code;

    private String token;



    public String getCode() {
        return code;
    }


    public String getToken() {
        return token;
    }



    @Override
    public String toString() {
        return "MainResponse{" +
                "code='" + code + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
