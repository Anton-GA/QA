package ru.kinopoisk;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import data.RegexKinopoisk;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;


public class TodayInCinemaPage {

    private final SelenideElement header = $(By.cssSelector(("div > section > h3 > a[href='/afisha/new/city']"))); //селектор Билеты в кино
    private final SelenideElement blockToday = $(By.cssSelector("#today-in-cinema-block+div")); //блок Сегондя
    private final SelenideElement displayCarousel = $(By.cssSelector("#today-in-cinema-block+div > section > h3+div")); //селектор карусели

    private final ElementsCollection snippets = $$(By.cssSelector("#today-in-cinema-block+div>section>div>div>div>[class*='styles_poster']")); //коллекция для каждого сниппета Сегодня в кино
    private final ElementsCollection titleOfSnippets = $$(By.cssSelector("[class *='captions']>[class *='title']> span > span")); //коллекция названий у сниппетов Сегодня в кино
    private final ElementsCollection yearAndGenre = $$(By.cssSelector("[class*='subtitle']>span")); //коллекция для года и жанра сниппетов Сегодня в кино
    private final ElementsCollection hrefOfSnippets = $$(By.xpath("//a[contains(@href , '/new/city')]/parent::div/div/div/a"));
    private final ElementsCollection rating = $$(By.cssSelector("[class *='styles_rating']"));

    //    private final ElementsCollection popOverMovieTitle = $$(By.cssSelector("[class *='styles_originMovieTitle']")); //коллекция для названия на английском в поповер
    //    private final SelenideElement popOverYear = $("[class *='styles_originMovieTitle'] + span"); //коллекция для года в поповер

    private final SelenideElement inMainRoles = $(By.cssSelector("[class *='styles_additionalInfo'] > [class *= 'styles_roles']"));
    private final SelenideElement producer = $(By.cssSelector("[class *='styles_additionalInfo'] > [class *= 'styles_roles'] + div"));
    private final SelenideElement leftButton = $(By.cssSelector("#today-in-cinema-block+div>section>h3+div>div+button"));
    private final SelenideElement rightButton = $(By.cssSelector("#today-in-cinema-block+div>section>h3+div>div+button+button"));


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

    /**
     *Метод для проверки года и жанра сниппетов в карусели Сегодня в кино
     */
    public TodayInCinemaPage checkYearAndGenre() {
        for(int i = 0; i < yearAndGenre.size(); i++) {
            System.out.println(yearAndGenre.get(i).getText());
            CommonSteps.regexAssertYearAndGenre(RegexKinopoisk.regexYearAndGenre, yearAndGenre.get(i));
        }
        return this;
    }

    /**
     *Метод для проверки ссылок у каждого сниппета
     */
    public TodayInCinemaPage checkHrefOfSnippets() {
        for(int i = 0; i < hrefOfSnippets.size(); i++) {
            System.out.println(hrefOfSnippets.get(i).getAttribute("href"));
            CommonSteps.regexAssertHref(RegexKinopoisk.regexHrefSnippets, hrefOfSnippets.get(i));
        }
        return this;
    }

    /**
     *Метод для проверки отображения в главной роли и режиссера в поповер
     */
    public TodayInCinemaPage checkInMainRolesAndProducer() {
        for(int i = 0; i < titleOfSnippets.size(); i++) {
            titleOfSnippets.get(i).hover();
            inMainRoles.shouldBe(Condition.visible);
            producer.shouldBe(Condition.visible);
        }
        return this;
    }

    /**
     *Метод для проверки рейтинга у сниппетов
     */
    public TodayInCinemaPage checkRating() {
        for(int i = 0; i < rating.size(); i++) {
            System.out.println(rating.get(i).getText());
            CommonSteps.regexAssertRating(RegexKinopoisk.regexRating, rating.get(i));
        }
        return this;
    }

    /**
     *Метод для проверки отображения кнопки влево, после нажатия на кнопку вправо
     */
    public TodayInCinemaPage checkRightAndLeftButtons() {
        rightButton.click();
        leftButton.shouldBe(Condition.visible);
        return this;
    }

//    public TodayInCinemaPage displayedEnTitleAndYearPopover() {
//        System.out.println(titleOfSnippets.size());
//        for(int i = 0; i < titleOfSnippets.size(); i++) {
//            titleOfSnippets.get(i).hover();
//            System.out.println(popOverYear.getText());
////            System.out.println(popOverYear.getText());
//            popOverYear.shouldBe(Condition.visible, Duration.ofSeconds(10));
//        }
//        return this;
//    }

}
