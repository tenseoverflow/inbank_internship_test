package com.tenseoverflow.inbanktest.dto;

public class LoanResponse {
    private Boolean decision;
    private Float loanAmount;

    public LoanResponse() {
    }

    public LoanResponse(Boolean decision, Float loanAmount) {
        this.decision = decision;
        this.loanAmount = loanAmount;
    }

    public Boolean getDecision() {
        return decision;
    }

    public void setDecision(Boolean decision) {
        this.decision = decision;
    }

    public Float getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Float loanAmount) {
        this.loanAmount = loanAmount;
    }
}
