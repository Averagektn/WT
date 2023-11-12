package by.bsuir.mycoolsite.bean.enums;

public enum AgeRestriction {
    EMPTY(""),
    ZERO("0+"),
    THREE("3+"),
    SIX("6+"),
    TWELVE("12+"),
    EIGHTEEN("18+"),
    TWENTY_ONE("21+");

    private final String stringValue;

    AgeRestriction(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public static AgeRestriction getAgeRestrictionFromString(String ageString) {
        for (AgeRestriction ageRestriction : AgeRestriction.values()) {
            if (ageRestriction.toString().equals(ageString)) {
                return ageRestriction;
            }
        }
        return null;
    }

}
