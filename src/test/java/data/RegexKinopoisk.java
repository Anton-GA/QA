package data;

public class RegexKinopoisk {
    public static final String regexUrlTodayInCinema = "https:\\/\\/www\\.kinopoisk\\.ru\\/afisha\\/new\\/city";

    public static final String regexOpenUrlToday = "(https:\\/\\/www\\.kinopoisk\\.ru\\/afisha\\/new\\/city)\\/([0-9]{3})\\/";

    public static final String regexFilmTitle = "([А-Яа-яёA-Za-z\\s\\№?:.!$,(){0-9}+-]+)";

    public static final String regexYearAndGenre = "(([2][0][0-2][0-9]\\s–\\s[2][0][0-2][0-9])|([2][0][0-2][0-9]))\\,\\s([а-яА-Я]{3,})";

    public static final String regexHrefSnippets = "https:\\/\\/www.kinopoisk.ru\\/film\\/\\d+\\/";

    public static final String regexRating = "\\d\\.\\d|\\d{2}\\%";

    public static final String regexSkipMovie = "^МУЛЬТ|^Союз";
}
