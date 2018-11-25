package com.kmlz.optcredit.network.responses;

import java.util.List;

public class CategoryListResponses extends MainResponse {

    private List<CategoryResponse> resp;

    public List<CategoryResponse> getResp() {
        return resp;
    }

    public void setResp(List<CategoryResponse> resp) {
        this.resp = resp;
    }

    @Override
    public String toString() {
        return "CategoryListResponses{" +
                "resp=" + resp +
                '}';
    }
}
