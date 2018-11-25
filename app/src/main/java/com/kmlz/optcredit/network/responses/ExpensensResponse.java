package com.kmlz.optcredit.network.responses;

import java.util.List;

public class ExpensensResponse extends  MainResponse {
    List<ExpenseRes> body;

    public List<ExpenseRes> getBody() {
        return body;
    }
}
