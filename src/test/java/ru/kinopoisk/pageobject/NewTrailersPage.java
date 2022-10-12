package ru.kinopoisk.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.RegexKinopoisk;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.kinopoisk.CommonSteps;

import static com.codeborne.selenide.Selenide.*;

public class NewTrailersPage {

    private final ElementsCollection snippets = $$(By.cssSelector("#new-trailers-block + div > div > h3 + div > div > div > div"));
    private final SelenideElement newTrailersBlock = $(By.cssSelector("#new-trailers-block+div"));
    private final ElementsCollection yearAndGenre = $$(By.cssSelector("[class *= 'film-trailer'] > span"));
    private final ElementsCollection titleOfTrailers = $$(By.cssSelector("[class *= 'film-trailer'] > div + a"));
    private final ElementsCollection playTrailerButton = $$(By.cssSelector("[class *= 'film-trailer'] > div > div > button"));
    private final SelenideElement displayDiscoveryWidget = $(By.cssSelector("[class *= 'discovery-trailers-iframe']"));
    private final SelenideElement closeDiscoveryWidgetButton = $(By.cssSelector("[class *= discovery-trailers-closer]"));

    public void scrollToBlock() {
        newTrailersBlock.scrollTo();
    }

    @Step(value = "Проверка отображения сниппетов в карусели")
    public NewTrailersPage checkDisplaySnippets() {
        for(int i = 0; i < snippets.size(); i++) {
            snippets.get(i).scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"end\"}");
            snippets.get(i).should(Condition.visible);
        }
        return this;
    }

    @Step(value = "Проверка отображения кнопки проигрывания трейлера в карусели")
    public NewTrailersPage checkDisplayPlayButton() {
        for(int i = 0; i < snippets.size(); i++) {
            playTrailerButton.get(i).should(Condition.exist);

        }
        return this;
    }

    @Step(value = "Проверка отображения виджета дискавери с плеером")
    public NewTrailersPage checkDisplayDiscoveryWidget() {
        for (int i = 0; i < snippets.size(); i++) {
            playTrailerButton.get(i).click();
            displayDiscoveryWidget.should(Condition.visible);
            closeDiscoveryWidgetButton.click();
        }
        return this;
    }

    @Step(value = "Проверка корректности названия трейлеров")
    public NewTrailersPage checkTitleOfTrailers() {
        for(int i = 0; i < snippets.size(); i++) {
            CommonSteps.regexAssertElementText(RegexKinopoisk.regexFilmTitle, titleOfTrailers.get(i));
        }
        return this;
    }

    @Step(value = "Проверка корректности года и жанра")
    public NewTrailersPage checkYearAndGenre() {
        for (int i = 0; i < snippets.size(); i++) {
            CommonSteps.regexAssertElementText(RegexKinopoisk.regexYearAndGenre, yearAndGenre.get(i));
        }
        return this;
    }
}
