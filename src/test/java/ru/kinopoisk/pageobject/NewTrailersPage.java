package ru.kinopoisk.pageobject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class NewTrailersPage {

    private final ElementsCollection snippets = $$(By.cssSelector("[class *= 'film-trailer']"));
    private final SelenideElement newTrailersBlock = $(By.cssSelector("#new-trailers-block+div"));

    public void scrollToBlock() {
        newTrailersBlock.scrollTo();
    }

    public void checkDisplaySnippets() {
        for(int i = 0; i < snippets.size(); i++) {
            System.out.println(snippets.get(i));
            snippets.get(i).should(Condition.visible);
        }
    }

}
