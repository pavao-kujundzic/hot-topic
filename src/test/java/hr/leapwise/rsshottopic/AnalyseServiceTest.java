package hr.leapwise.rsshottopic;

import hr.leapwise.rsshottopic.domain.exception.AnalyseNotFoundException;
import hr.leapwise.rsshottopic.domain.exception.InvalidRequestException;
import hr.leapwise.rsshottopic.domain.service.AnalyseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static hr.leapwise.rsshottopic.domain.service.impl.AnalyseServiceImpl.MINIMUM_NUMBER_OF_RSS_URLS;

@SpringBootTest
public class AnalyseServiceTest {

    private final String NO_URL_EXCEPTION_MESSAGE = "RSS urls must be present for analyse.";
    private final String NOT_ENOUGH_URLS_EXCEPTION_MESSAGE = "Minimum number of RSS urls must be " + MINIMUM_NUMBER_OF_RSS_URLS;

    private final String NOT_EXISTING_ID = "i-dont-exist";
    private final String NOT_EXISTIND_ID_EXCEPTION_MESSAGE = "No analyse found for id: " + NOT_EXISTING_ID;
    private List<String> NO_URLS = List.of();
    private List<String> URl = List.of("https://news.google.com/news?cf=all&hl=en&pz=1&ned=us&output=rss");


    @Autowired
    private AnalyseService analyseService;


    @Test
    public void noUrlsTest() {
        InvalidRequestException exception = Assertions.assertThrows(InvalidRequestException.class, () -> {
            analyseService.analyseRssFeeds(NO_URLS);
        });
        Assertions.assertEquals(NO_URL_EXCEPTION_MESSAGE, exception.getMessage());
    }


    @Test
    public void notEnoughUrlsTest() {
        InvalidRequestException exception = Assertions.assertThrows(InvalidRequestException.class, () -> {
            analyseService.analyseRssFeeds(URl);
        });
        Assertions.assertEquals(NOT_ENOUGH_URLS_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    public void notExistingAnalyseIdTest() {
        AnalyseNotFoundException exception = Assertions.assertThrows(AnalyseNotFoundException.class, () -> {
            analyseService.getAnalyse(NOT_EXISTING_ID);
        });
        Assertions.assertEquals(NOT_EXISTIND_ID_EXCEPTION_MESSAGE, exception.getMessage());
    }


}
