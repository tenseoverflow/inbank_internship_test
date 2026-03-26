package com.tenseoverflow.inbanktest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenseoverflow.inbanktest.model.Profile;

@Repository
public class MockProfileRepository {
    // Took these from the task PDF.
    private List<Profile> profiles = new ArrayList<>(
            List.of(
                    new Profile(49002010965l, 100, 1f),
                    new Profile(49002010976l, 100, 0f),
                    new Profile(49002010987l, 300, 0f),
                    new Profile(49002010998l, 1000, 0f)));

    public List<Profile> getProfiles() {
        return profiles;
    }

    public Profile getProfileByPersonalCode(Long personalCode) {
        return profiles.stream()
                .filter(profile -> profile.getPersonalCode().equals(personalCode))
                .findFirst()
                .orElse(null);
    }
}
