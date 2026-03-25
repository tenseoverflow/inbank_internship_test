package com.tenseoverflow.inbanktest.model;

public class Profile {
    private Long personalCode;
    private Integer creditModifier;
    private Float debt; // Currently this var is not used fully, may require substituting it with
                        // hasDebt boolean

    public Profile(Long personalCode, Integer creditModifier, Float debt) {
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

    public Float getDebt() {
        return debt;
    }

    public void setDebt(Float debt) {
        this.debt = debt;
    }
}
