package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.entity.enums.AgeRestriction;

import java.util.ArrayList;
import java.util.List;

public class AgeRestrictionService {
    public static List<String> getAgeRestrictions() {
        List<String> ageRestrictions = new ArrayList<>();

        for (AgeRestriction ageRestriction : AgeRestriction.values()) {
            ageRestrictions.add(ageRestriction.toString());
        }
        ageRestrictions.remove(AgeRestriction.EMPTY.toString());

        return ageRestrictions;
    }
}
