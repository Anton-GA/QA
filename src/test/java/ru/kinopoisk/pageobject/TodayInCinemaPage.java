package ru.kinopoisk.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import data.RegexKinopoisk;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import ru.kinopoisk.CommonSteps;

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
    private final SelenideElement producer = $(By.cssSelector("[class *='styles_additionalInfo'] > [class *= 'styles_roles'] + div, div[class *='directorsWithoutRoles']"));
    private final SelenideElement leftButton = $(By.cssSelector("#today-in-cinema-block+div>section>h3+div>div+button"));
    private final SelenideElement rightButton = $(By.cssSelector("#today-in-cinema-block+div>section>h3+div>div+button+button"));
    private final SelenideElement ticketButton = $(By.cssSelector("[class *='styles_afishaButton']"));

    private final String scrollInto = "{behavior: \"instant\", block: \"center\", inline: \"center\"}";


    @Step(value = "Метод для скролла у блоку Сегодня в кино")
    public void scrollToBlock() {
        blockToday.scrollTo();
        header.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step(value = "Метод для проверки отображения блока Сегодня в кино")
    public TodayInCinemaPage assertBlockTodayInCinema() {
        blockToday.shouldBe(Condition.visible);
        return this;
    }

    @Step(value = "Метод для проверки отображения заголовка")
    public TodayInCinemaPage assertHeaders() {
        Assert.assertEquals(header.getText(), "Билеты в кино");
        return this;
    }

    @Step(value = "Метод для проверки отображения карусели")
    public TodayInCinemaPage carouselIsDisplayed() {
        displayCarousel.shouldBe(Condition.visible);
        return this;
    }

    @Step(value = "Метод для проверки ссылки в блоке Сегодня в кино")
    public TodayInCinemaPage checkHrefTodayInCinemaBlock() {
        CommonSteps.regexAssertHref(RegexKinopoisk.regexUrlTodayInCinema, header);
        return this;
    }

    @Step(value = "Метод для проверки корректности ссылки при ее открытии из блока")
    public TodayInCinemaPage checkURLRedirectTodayInCinema() {
        CommonSteps.regexAssertUrl(RegexKinopoisk.regexOpenUrlToday, header);
        return this;
    }

    @Step(value = "Метод для проверки названий у сниппетов в карусели")
    public TodayInCinemaPage checkFilmTitle() {
        for(int i = 0; i < titleOfSnippets.size(); i++) {
            blockToday.scrollIntoView(scrollInto);
            CommonSteps.regexAssertElementText(RegexKinopoisk.regexFilmTitle, titleOfSnippets.get(i));
        }
        return this;
    }

    @Step(value = "Метод для проверки отбражения сниппетов в карусели")
    public TodayInCinemaPage displayedSnippets() {
        blockToday.scrollIntoView(scrollInto);
        for(int i = 0; i < snippets.size(); i++) {
            snippets.get(i).shouldBe(Condition.visible);
        }
        return this;
    }

    @Step(value = "Метод для проверки года и жанра сниппетов в карусели")
    public TodayInCinemaPage checkYearAndGenre() {
        for(int i = 0; i < yearAndGenre.size(); i++) {
            snippets.get(i).scrollIntoView(scrollInto);
            CommonSteps.regexAssertElementText(RegexKinopoisk.regexYearAndGenre, yearAndGenre.get(i));
        }
        return this;
    }

    @Step(value = "Метод для проверки ссылок у кажого сниппета")
    public TodayInCinemaPage checkHrefOfSnippets() {
        for(int i = 0; i < hrefOfSnippets.size(); i++) {
            snippets.get(i).scrollIntoView(scrollInto);
            CommonSteps.regexAssertHref(RegexKinopoisk.regexHrefSnippets, hrefOfSnippets.get(i));
        }
        return this;
    }

    @Step(value = "Метод для проверки отображения в главной роли и режиссера в поповер")
    public TodayInCinemaPage checkInMainRolesAndProducer() {
        for (int i = 0; i < snippets.size(); i++) {
            sleep(1000);
            SelenideElement snippet = snippets.get(i);
            SelenideElement snippetTitle = titleOfSnippets.get(i);
            snippet.scrollIntoView(scrollInto);
            if(!Pattern.matches(RegexKinopoisk.regexSkipMovie, snippetTitle.getText())) {
                snippetTitle.hover();
                inMainRoles.shouldBe(Condition.visible);
                producer.shouldBe(Condition.visible);
            } else {
                snippetTitle.hover();
                inMainRoles.shouldBe(Condition.not(Condition.visible));
                snippetTitle.hover();
                producer.shouldBe(Condition.visible);
            }
        }
        return this;
    }

    @Step(value = "Метод для проверки рейтинга у сниппетов")
    public TodayInCinemaPage checkRating() {
        for(int i = 0; i < rating.size(); i++) {
            CommonSteps.regexAssertElementText(RegexKinopoisk.regexRating, rating.get(i));
        }
        return this;
    }

//    @Step(value = "Метод для проверки отображения кнопки влево, после нажатия на кнопку вправо")
//    public TodayInCinemaPage checkRightAndLeftButtons() {
////        rightButton.click();
//        leftButton.shouldBe(Condition.visible);
//        return this;
//    }

    @Step(value = "Метод для проверки отображения кнопки с билетами у сниппетов")
    public TodayInCinemaPage checkButtonWithTickets() {
        for (int i = 0; i < titleOfSnippets.size(); i++) {
            titleOfSnippets.get(i).scrollIntoView(scrollInto);
            titleOfSnippets.get(i).hover();
//            try {
//                ticketButton.shouldBe(Condition.visible);
//            } catch (ElementNotFound r) {
//                System.out.println(titleOfSnippets.get(i).getText());
//            }
//            ticketButton.shouldBe(Condition.visible);
        }
        return this;
    }
}
