package com.example.ramengo.enums;

public enum ProteinEnum {
    KARAAGUE("Karaague"),
    CHASU("Chasu"),
    YASAI_VEGETARIAN("Yasai Vegetable");


    private final String name;

    ProteinEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
