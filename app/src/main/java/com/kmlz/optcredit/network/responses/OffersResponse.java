package com.kmlz.optcredit.network.responses;

import java.util.List;

public class OffersResponse extends MainResponse {
    List<OfferObj> body;

    public List<OfferObj> getBody() {
        return body;
    }

    public void setBody(List<OfferObj> body) {
        this.body = body;
    }
}
