package com.kmlz.optcredit.network.responses;

import com.google.gson.annotations.SerializedName;

public class CategoryResponse {

    @SerializedName("CAT_ID")
    private String catId;
    @SerializedName("CAT_NAME")
    private String catName;


    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
