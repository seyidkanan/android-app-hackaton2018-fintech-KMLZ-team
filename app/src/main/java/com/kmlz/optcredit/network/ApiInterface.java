package com.kmlz.optcredit.network;

import com.kmlz.optcredit.network.request.LoginRequest;
import com.kmlz.optcredit.network.request.RegisterRequest;
import com.kmlz.optcredit.network.responses.CreditTypesResponse;
import com.kmlz.optcredit.network.responses.LoginResponse;
import com.kmlz.optcredit.network.responses.MainResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("login/")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("askregister/")
    Call<MainResponse> register(@Body RegisterRequest registerRequest);

    @GET("listTypes")
    Call<CreditTypesResponse> getCreditTypes();


//    @POST("dede")
//    Call<> getCreditTypes(@POST MainRequestBody requestBody);
//
//
//    @POST("dede")
//    Call<> getExpenceTypes(@POST MainRequestBody requestBody);

}
