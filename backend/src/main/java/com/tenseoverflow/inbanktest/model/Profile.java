package com.tenseoverflow.inbanktest.model;

public class Profile {
    private Long personalCode;
    private Integer creditModifier;
    private Boolean debt; // I was contemplating between using float or boolean, however, since the task
                          // mentioned "has debt", I went with boolean.

    public Profile(Long personalCode, Integer creditModifier, Boolean debt) {
        this.personalCode = personalCode;
        this.creditModifier = creditModifier;
        this.debt = debt;
    }

    public Long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Long personalCode) {
        this.personalCode = personalCode;
    }

    public Integer getCreditModifier() {
        return creditModifier;
    }

    public void setCreditModifier(Integer creditModifier) {
        this.creditModifier = creditModifier;
    }

    public Boolean hasDebt() {
        return debt;
    }

    public void setDebt(Boolean debt) {
        this.debt = debt;
    }
}
