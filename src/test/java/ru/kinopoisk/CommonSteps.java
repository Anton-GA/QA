package ru.kinopoisk;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import data.RegexKinopoisk;
import org.testng.Assert;

import java.util.regex.Pattern;

import static com.codeborne.selenide.WebDriverRunner.url;

public class CommonSteps {
    public static void regexAssertHref(String regex, SelenideElement element) {
        boolean reg = Pattern
                .matches(regex, element.getAttribute("href"));
        Assert.assertTrue(reg);
    }

    public static void regexAssertUrl(String regex, SelenideElement element) {
        element.click();
        boolean reg = Pattern.matches(regex, url());
        System.out.println(regex);
        System.out.println(url());
        Assert.assertTrue(reg);
    }
}
