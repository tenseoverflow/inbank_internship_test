package com.tenseoverflow.inbanktest.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class LoanRequest {
    @NotNull
    @Min(10000000000L)
    @Max(99999999999L)
    private Long personalCode;

    @NotNull
    @DecimalMin("2000")
    @DecimalMax("10000")
    private Float amount;

    @NotNull
    @Min(12)
    @Max(60)
    private Integer period;

    public LoanRequest() {
    }

    public LoanRequest(Long personalCode, Float amount, Integer period) {
        this.personalCode = personalCode;
        this.amount = amount;
        this.period = period;
    }

    public Long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Long personalCode) {
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
