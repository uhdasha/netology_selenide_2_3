package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Keys;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class NewIssueFeaturesTestClass {
    @Test
    @DisplayName("This we test new features for issue card")
    public void testNewFeatures() {

        open("http://127.0.0.1:9999");
        // get instance
        DataGenerator dataGen = new DataGenerator();

        $("[data-test-id='city']").find("input").setValue(dataGen.getCity());

        $("[data-test-id='date']").find("input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id='date']").find("input").sendKeys("\b");
        $("[data-test-id='date']").find("input").setValue(dataGen.getDate());

        $("[data-test-id='name']").find("input").val(dataGen.getName());
        $("[data-test-id='phone']").find("input").setValue(dataGen.getPhone());

        $("[data-test-id='agreement']").click();

        $(".button_view_extra").click();

        $("[data-test-id='success-notification']").waitWhile(hidden, 15000).
                find(".notification__title").shouldHave(text("Успешно!"));

        $("[data-test-id='date']").find("input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id='date']").find("input").sendKeys("\b");
        $("[data-test-id='date']").find("input").setValue(dataGen.getDate());

        $(".button_view_extra").click();

        $("[data-test-id='replan-notification']").waitWhile(hidden, 15000).
                find(".notification__title").shouldHave(text("Необходимо подтверждение"));

        $("[data-test-id='replan-notification']").find(".button_view_extra").click();

        $("[data-test-id='success-notification']").waitWhile(hidden, 15000).
                find(".notification__title").shouldHave(text("Успешно!"));
    }
}