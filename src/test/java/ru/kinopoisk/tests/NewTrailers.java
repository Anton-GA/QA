package ru.kinopoisk.tests;

import org.testng.annotations.BeforeClass;
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
    public void checkCarouselElements() {
        newTrailersPage
                .checkDisplaySnippets()
                .checkYearAndGenre();
    }

}
