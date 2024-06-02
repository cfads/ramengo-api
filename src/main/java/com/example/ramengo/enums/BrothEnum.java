package com.example.ramengo.enums;

public enum BrothEnum {
    SALT("Salt"),
    SHOYU("Shoyu"),
    MISO("Miso");

    private final String name;

    BrothEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
