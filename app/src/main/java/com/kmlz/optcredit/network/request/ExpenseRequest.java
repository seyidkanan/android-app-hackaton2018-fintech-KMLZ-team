package com.kmlz.optcredit.network.request;

import com.google.gson.annotations.SerializedName;

public class ExpenseRequest {

    private String token;
    @SerializedName("expense_amount")
    private double expenseAmount;
    @SerializedName("expense_category")
    private int expenseCategory;
    @SerializedName("expense_name")
    private String expenseName;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public int getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(int expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    @Override
    public String toString() {
        return "ExpenseRequest{" +
                "token='" + token + '\'' +
                ", expenseAmount=" + expenseAmount +
                ", expenseCategory=" + expenseCategory +
                ", expenseName='" + expenseName + '\'' +
                '}';
    }
}
