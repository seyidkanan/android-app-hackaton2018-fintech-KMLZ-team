package com.kmlz.optcredit.network.responses;

import java.util.List;

public class CreditTypesResponse extends  MainResponse {
    List<CreditType> resp;

    public List<CreditType> getResp() {
        return resp;
    }

    public void setResp(List<CreditType> resp) {
        this.resp = resp;
    }
}
