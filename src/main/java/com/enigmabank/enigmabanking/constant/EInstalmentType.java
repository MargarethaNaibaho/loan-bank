package com.enigmabank.enigmabanking.constant;

public enum EInstalmentType {
    ONE_MONTH,
    THREE_MONTHS,
    SIXTH_MONTHS,
    NINE_MONTHS,
    TWELVE_MONTHS;

    public static boolean contains(String value) {
        for (EInstalmentType type : EInstalmentType.values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
