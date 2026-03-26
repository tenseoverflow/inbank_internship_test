package com.tenseoverflow.inbanktest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenseoverflow.inbanktest.model.Profile;

@Repository
public class MockProfileRepository {
    // Took these from the task PDF. Since the task didn't specify on having a
    // database, I decided to hardcode them here. I think repository is fitting.
    private List<Profile> profiles = new ArrayList<>(
            List.of(
                    new Profile(49002010965l, 0, true),
                    new Profile(49002010976l, 100, false),
                    new Profile(49002010987l, 300, false),
                    new Profile(49002010998l, 1000, false)));

    public List<Profile> getProfiles() {
        return profiles;
    }

    // Helper function for service
    public Profile getProfileByPersonalCode(Long personalCode) {
        return profiles.stream()
                .filter(profile -> profile.getPersonalCode().equals(personalCode))
                .findFirst()
                .orElse(null);
    }
}
