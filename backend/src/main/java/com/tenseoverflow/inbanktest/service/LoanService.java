package com.tenseoverflow.inbanktest.service;

import org.springframework.stereotype.Service;

import com.tenseoverflow.inbanktest.dto.LoanRequest;
import com.tenseoverflow.inbanktest.dto.LoanResponse;
import com.tenseoverflow.inbanktest.exception.ProfileNotFoundException;
import com.tenseoverflow.inbanktest.model.Profile;
import com.tenseoverflow.inbanktest.repository.MockProfileRepository;

@Service
public class LoanService {
    private static final float MAX_AMOUNT = 10000;
    private static final float MIN_AMOUNT = 2000;
    private static final int MIN_PERIOD = 12;
    private static final int MAX_PERIOD = 60;

    private final MockProfileRepository profileRepository;

    public LoanService(MockProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public LoanResponse getLoanAmount(LoanRequest request) {
        Profile profile = profileRepository.getProfileByPersonalCode(request.getPersonalCode());

        if (profile == null) {
            throw new ProfileNotFoundException(request.getPersonalCode());
        }

        if (profile.hasDebt()) {
            return new LoanResponse(false, 0.0f);
        }

        Integer creditModifier = profile.getCreditModifier();
        Float requestedAmount = request.getAmount();
        Integer requestedPeriod = request.getPeriod();

        if (creditModifier == null || requestedAmount == null || requestedAmount <= 0 || requestedPeriod == null) {
            return new LoanResponse(false, 0.0f);
        }

        float creditScore = (creditModifier / requestedAmount) * requestedPeriod;

        if (requestedPeriod >= MIN_PERIOD && requestedPeriod <= MAX_PERIOD) {
            float approvedAmount = creditModifier * requestedPeriod;
            if (approvedAmount > MAX_AMOUNT) {
                approvedAmount = MAX_AMOUNT;
            }

            if (approvedAmount >= MIN_AMOUNT) {
                return new LoanResponse(creditScore >= 1, approvedAmount);
            }
        }

        float bestAlternative = 0.0f;

        for (int period = MIN_PERIOD; period <= MAX_PERIOD; period++) {
            if (period == requestedPeriod) {
                continue;
            }

            float candidateAmount = creditModifier * period;
            if (candidateAmount > MAX_AMOUNT) {
                candidateAmount = MAX_AMOUNT;
            }

            if (candidateAmount >= MIN_AMOUNT && candidateAmount > bestAlternative) {
                bestAlternative = candidateAmount;
            }
        }

        if (bestAlternative > 0.0f) {
            return new LoanResponse(false, bestAlternative);
        }

        return new LoanResponse(false, 0.0f);
    }
}
