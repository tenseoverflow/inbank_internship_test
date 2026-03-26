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

    private final MockProfileRepository profileRepository;

    public LoanService(MockProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public LoanResponse getLoanAmount(LoanRequest request) {
        Profile profile = profileRepository.getProfileByPersonalCode(request.getPersonalCode());

        if (profile == null) {
            throw new ProfileNotFoundException(request.getPersonalCode());
        }

        if (profile.getDebt() > 0) {
            return new LoanResponse(false, 0.0f);
        }

        float creditScore = (profile.getCreditModifier() / request.getAmount()) * request.getPeriod();

        float maxLoan = profile.getCreditModifier() * request.getPeriod();

        if (maxLoan > MAX_AMOUNT) {
            maxLoan = MAX_AMOUNT;
        }

        if (creditScore < 1) {
            Integer period = request.getPeriod();

            while (maxLoan < MIN_AMOUNT && period >= MIN_PERIOD) {
                maxLoan = profile.getCreditModifier() * period;
                period -= 1;
            }

            if (maxLoan > MAX_AMOUNT) {
                maxLoan = MAX_AMOUNT;
            }

            return new LoanResponse(false, maxLoan);
        } else {
            return new LoanResponse(true, maxLoan);
        }
    }
}
