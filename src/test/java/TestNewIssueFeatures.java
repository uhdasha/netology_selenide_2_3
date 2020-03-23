package ru.netology;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Keys;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TestNewIssueFeatures {
    @Test
    @DisplayName("This we test new features for issue card")
    public void test_new_features() {

        open("http://127.0.0.1:9999");

        Faker faker = new Faker(new Locale("ru"));

        String city = faker.address().city();

        $("[data-test-id='city']").find("input").setValue(city);

        $("[data-test-id='date']").find("input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id='date']").find("input").sendKeys("\b");

        $("[data-test-id='date']").find("input").setValue(genarate_date_string());

        $("[data-test-id='name']").find("input").val(faker.name().fullName());

        String phone = "+7" + faker.phoneNumber().cellPhone().
                replace("-", "").
                replace("(", "").
                replace(")", "").
                replace(" ", "");

        $("[data-test-id='phone']").find("input").setValue(phone);

        $("[data-test-id='agreement']").click();

        $(".button_view_extra").click();

        $("[data-test-id='success-notification']").waitWhile(hidden, 15000).
                find(".notification__title").shouldHave(text("Успешно!"));

        $("[data-test-id='date']").find("input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id='date']").find("input").sendKeys("\b");
        $("[data-test-id='date']").find("input").setValue(genarate_date_string());

        $(".button_view_extra").click();

        $("[data-test-id='replan-notification']").waitWhile(hidden, 15000).
                find(".notification__title").shouldHave(text("Необходимо подтверждение"));

        $("[data-test-id='replan-notification']").find(".button_view_extra").click();

        $("[data-test-id='success-notification']").waitWhile(hidden, 15000).
                find(".notification__title").shouldHave(text("Успешно!"));
    }

    private String genarate_date_string() {
        Calendar date = new GregorianCalendar();

        Random random = new Random();

        date.add(Calendar.DAY_OF_MONTH, random.nextInt(31) + 3);
        String day = String.valueOf(date.get(Calendar.DAY_OF_MONTH));
        if (date.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + day;
        }
        String mon = String.valueOf(date.get(Calendar.MONTH) + 1);
        if (date.get(Calendar.MONTH) + 1 < 10) {
            mon = "0" + mon;
        }
        String dateStr = day + "." + mon + "." + date.get(Calendar.YEAR);
        return dateStr;
    }
}