package ru.kinopoisk.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.RegexKinopoisk;
import org.openqa.selenium.By;
import ru.kinopoisk.CommonSteps;

import static com.codeborne.selenide.Selenide.*;

public class NewTrailersPage {

    private final ElementsCollection snippets = $$(By.cssSelector("#new-trailers-block + div > div > h3 + div > div > div > div"));
    private final SelenideElement newTrailersBlock = $(By.cssSelector("#new-trailers-block+div"));
    private final ElementsCollection yearAndGenre = $$(By.cssSelector("[class *= 'film-trailer'] > span"));


    public void scrollToBlock() {
        newTrailersBlock.scrollTo();
    }

    public NewTrailersPage checkDisplaySnippets() {
        for(int i = 0; i < snippets.size(); i++) {
            snippets.get(i).scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"end\"}");
            snippets.get(i).should(Condition.visible);
        }
        return this;
    }

    public NewTrailersPage checkYearAndGenre() {
        for (int i = 0; i < snippets.size(); i++) {
            snippets.get(i).scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"end\"}");
            System.out.println(yearAndGenre.get(i).getText());
            CommonSteps.regexAssertElementText(RegexKinopoisk.regexYearAndGenre, yearAndGenre.get(i));
        }
        return this;
    }
}
