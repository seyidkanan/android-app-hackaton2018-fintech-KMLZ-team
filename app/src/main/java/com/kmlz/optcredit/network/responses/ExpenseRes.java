package com.kmlz.optcredit.network.responses;

public class ExpenseRes {
    String CAT_NAME;
    String EXPENSE_NAME;
    String EXPENSE_AMOUNT;

    public String getCAT_NAME() {
        return CAT_NAME;
    }

    public String getEXPENSE_NAME() {
        return EXPENSE_NAME;
    }

    public String getEXPENSE_AMOUNT() {
        return EXPENSE_AMOUNT;
    }
    /**
     * "CAT_NAME": "Nəqliyyat",
     *         	"EXPENSE_NAME": "avtabus",
     *         	"EXPENSE_AMOUNT": "1.00",
     */
}
