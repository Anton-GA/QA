package ru.kinopoisk.tests;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.kinopoisk.BaseTest;
import ru.kinopoisk.pageobject.NewTrailersPage;

public class NewTrailers extends BaseTest {

    NewTrailersPage newTrailersPage = new NewTrailersPage();

    @BeforeClass
    public void scroll() {
        newTrailersPage.scrollToBlock();
    }

    @Test
    @Description("Тест проверяет сниппеты в карусели, кнопки проигрывания, дискавери виджет, год и жанр, название трейлеров")
    public void checkCarouselElements() {
        newTrailersPage
                .checkDisplaySnippets()
                .checkDisplayPlayButton()
                .checkDisplayDiscoveryWidget()
                .checkYearAndGenre()
                .checkTitleOfTrailers();
    }

}
