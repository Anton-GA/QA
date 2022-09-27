package ru.kinopoisk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.RegexKinopoisk;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.time.Duration;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;


public class TodayInCinemaPage {

    private final SelenideElement header = $(By.cssSelector(("div > section > h3 > a[href='/afisha/new/city']"))); //селектор Билеты в кино
    private final SelenideElement blockToday = $(By.cssSelector("#today-in-cinema-block+div")); //блок Сегондя
    private final SelenideElement displayCarousel = $(By.cssSelector("#today-in-cinema-block+div > section > h3+div")); //селектор карусели

    private final ElementsCollection snippets = $$(By.cssSelector("#today-in-cinema-block+div>section>div>div>div>[class*='styles_poster']")); //коллекция для каждого сниппета Сегодня в кино
//    private final ElementsCollection titleOfSnippets = $$(By.cssSelector("#today-in-cinema-block+div>section>div>div>div>[class*='styles_poster']>div+a>span>span>span"));
    private final ElementsCollection titleOfSnippets = $$(By.cssSelector("[class *='captions']>[class *='title']> span > span")); //коллекция названий у сниппетов Сегодня в кино
    private final ElementsCollection yearAndGenre = $$(By.cssSelector("[class*='subtitle']>span"));//коллекция для года и жанра сниппетов Сегодня в кино


    /**
     * Метод для скролла к блоку Сегодня в кино
     */
    public void scrollToBlock() {
        blockToday.scrollTo();
        header.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    /**
     *Метод для проверки отображения блока Сегодня в кино
     */
    public TodayInCinemaPage assertBlockTodayInCinema() {
        blockToday.shouldBe(Condition.visible);
        return this;
    }

    /**
     *Метод для проверки отображения заголовка
     */
    public TodayInCinemaPage assertHeaders() {
        Assert.assertEquals(header.getText(), "Билеты в кино");
        return this;
    }


    /**
     *Метод для проверки отображения карусели
     */
    public TodayInCinemaPage carouselIsDisplayed() {
        displayCarousel.shouldBe(Condition.visible);
        return this;
    }

    /**
     *Метод для проверки ссылки в блоке Сегодня в кино
     */
    public TodayInCinemaPage checkHrefTodayInCinemaBlock() {
        CommonSteps.regexAssertHref(RegexKinopoisk.regexUrlTodayInCinema, header);
        return this;
    }

    /**
     *Метод для проверки корректности ссылки при ее открытии из блока Сегодня в кино
     */
    public TodayInCinemaPage checkURLRedirectTodayInCinema() {
        CommonSteps.regexAssertUrl(RegexKinopoisk.regexOpenUrlToday, header);
        return this;
    }

    /**
     *Метод для проверки корректности названий у сниппетов в карусле Сегодня в кино
     */
    public TodayInCinemaPage checkFilmTitle() {
        for(int i = 0; i < titleOfSnippets.size(); i++) {
            System.out.println(titleOfSnippets.get(i).getText());
            CommonSteps.regexAssertFilmTitle(RegexKinopoisk.regexFilmTitle, titleOfSnippets.get(i));
        }
        return this;
    }

    /**
     *Метод для проверки отображения каждого сниппета в карусели Сегодня в кино
     */
    public TodayInCinemaPage displayedSnippets() {
        for(int i = 0; i < snippets.size(); i++) {
            snippets.get(i).shouldBe(Condition.visible);
        }
        return this;
    }

    public TodayInCinemaPage checkYearAndGenre() {
        for(int i = 0; i < yearAndGenre.size(); i++) {
            System.out.println(yearAndGenre.get(i).getText());
            CommonSteps.regexAssertYearAndGenre(RegexKinopoisk.regexYearAndGenre, yearAndGenre.get(i));
        }
        return this;
    }

}
