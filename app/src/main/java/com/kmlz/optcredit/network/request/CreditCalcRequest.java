package com.kmlz.optcredit.network.request;

public class CreditCalcRequest extends MainRequestBody {
    int cal_type;
    double cal_loan;
    double cal_percent;
    int cal_period;

    public int getCal_type() {
        return cal_type;
    }

    public void setCal_type(int cal_type) {
        this.cal_type = cal_type;
    }

    public double getCal_loan() {
        return cal_loan;
    }

    public void setCal_loan(double cal_loan) {
        this.cal_loan = cal_loan;
    }

    public double getCal_percent() {
        return cal_percent;
    }

    public void setCal_percent(double cal_percent) {
        this.cal_percent = cal_percent;
    }

    public int getCal_period() {
        return cal_period;
    }

    public void setCal_period(int cal_period) {
        this.cal_period = cal_period;
    }
}
