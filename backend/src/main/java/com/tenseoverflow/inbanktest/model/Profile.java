package com.tenseoverflow.inbanktest.model;

public class Profile {
    private String personalId;
    private Integer creditModifier;
    private Float debt; // Currently this var is not used fully, may require substituting it with
                        // hasDebt boolean

    public Profile(String personalId, Integer creditModifier, Float debt) {
        this.personalId = personalId;
        this.creditModifier = creditModifier;
        this.debt = debt;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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
