package com.utils;

import com.github.javafaker.Faker;

public class RandomDetails {

    private static final Faker faker = new Faker();

    public static String generateRandomName() {
        return faker.name().firstName();
    }

    public static String generateRandomGmail() {
        return faker.name().firstName().toLowerCase()
                + faker.number().digits(4)
                + "@gmail.com";
    }
}
