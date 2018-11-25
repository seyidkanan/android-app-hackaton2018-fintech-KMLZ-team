package com.kmlz.optcredit.network;

import com.google.gson.JsonObject;
import com.kmlz.optcredit.network.request.CreditCalcRequest;
import com.kmlz.optcredit.network.request.ExpenseRequest;
import com.kmlz.optcredit.network.request.LoginRequest;
import com.kmlz.optcredit.network.request.MainRequestBody;
import com.kmlz.optcredit.network.request.RegisterRequest;
import com.kmlz.optcredit.network.responses.CategoryListResponses;
import com.kmlz.optcredit.network.responses.CreditTypesResponse;
import com.kmlz.optcredit.network.responses.ExpensensResponse;
import com.kmlz.optcredit.network.responses.LoginResponse;
import com.kmlz.optcredit.network.responses.MainResponse;
import com.kmlz.optcredit.network.responses.OffersResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("login/")
    Call<LoginResponse> login(@Body LoginRequest request);

    @POST("askregister/")
    Call<JsonObject> register(@Body RegisterRequest registerRequest);

    @GET("listTypes/")
    Call<CreditTypesResponse> getCreditTypes();


    @POST("calculator/")
    Call<MainResponse>  calculateCredit (@Body CreditCalcRequest creditCalcRequest);

    @POST("getOffers")
    Call<OffersResponse> getOffers(@Body MainRequestBody requestBody);

    @POST("addExpense")
    Call<MainResponse> addExpense(@Body ExpenseRequest expenseRequest);

    @GET("listCategories")
    Call<CategoryListResponses> getCategories();

    @POST("getExpenses/")
    Call<ExpensensResponse> getExpenses(@Body MainRequestBody requestBody);


//    @POST("dede")
//    Call<> getCreditTypes(@POST MainRequestBody requestBody);
//
//
//    @POST("dede")
//    Call<> getExpenceTypes(@POST MainRequestBody requestBody);

}
