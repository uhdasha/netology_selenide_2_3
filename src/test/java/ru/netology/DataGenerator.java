package ru.netology;

import com.github.javafaker.Faker;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.time.LocalDate;

public class DataGenerator {
    private Faker faker;

    public DataGenerator() {
        this.faker = new Faker(new Locale("ru"));
    }

    public String getName() {
        return faker.name().fullName();
    }

    public String getCity() {
        return this.faker.address().city();
    }

    public String getDate() {
        LocalDate date = LocalDate.now();
        Random random = new Random();

        date = date.plusDays(random.nextInt(10) + 3);

        String pattern = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(date);
    }

    public String getPhone() {
        return "+7" + faker.phoneNumber().cellPhone().
                replace("-", "").
                replace("(", "").
                replace(")", "").
                replace(" ", "");
    }
}
