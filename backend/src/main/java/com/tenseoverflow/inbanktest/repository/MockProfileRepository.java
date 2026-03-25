package com.tenseoverflow.inbanktest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tenseoverflow.inbanktest.model.Profile;

@Repository
public class MockProfileRepository {
    // Took these from the task PDF.
    private List<Profile> profiles = new ArrayList<>(
            List.of(new Profile("49002010965", 100, 1f), new Profile("49002010976", 100, 1f),
                    new Profile("49002010987", 300, 1f), new Profile("49002010998", 1000, 0f)));

    public List<Profile> getProfiles() {
        return profiles;
    }
}
