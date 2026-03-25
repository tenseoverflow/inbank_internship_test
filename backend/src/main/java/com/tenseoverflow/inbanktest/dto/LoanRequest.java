package com.tenseoverflow.inbanktest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoanRequest {
    @NotBlank
    @Size(min = 11, max = 11)
    private Integer personalCode;

    @NotBlank
    @Size(min = 2000, max = 10000)
    private Float amount;

    @NotBlank
    @Size(min = 12, max = 60)
    private Integer period;

    public LoanRequest() {
    }

    public LoanRequest(Integer personalCode, Float amount, Integer period) {
        this.personalCode = personalCode;
        this.amount = amount;
        this.period = period;
    }

    public Integer getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Integer personalCode) {
        this.personalCode = personalCode;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
}
