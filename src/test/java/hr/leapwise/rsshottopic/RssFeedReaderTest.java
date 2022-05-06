package hr.leapwise.rsshottopic;

import hr.leapwise.rsshottopic.configuration.NLPConf;
import hr.leapwise.rsshottopic.domain.dbo.RssFeedItem;

import hr.leapwise.rsshottopic.domain.rss.RssFeedReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class RssFeedReaderTest {


    private List<String> VALID_URLS = List.of("https://news.google.com/news?cf=all&hl=en&pz=1&ned=us&output=rss", "https://www.huffpost.com/section/front-page/feed?x=1");

    //test for implementation of fetching titles
    @Test
    public void titleReadShowTest() {
        RssFeedReader.readItemFromUrls(VALID_URLS).stream().map(RssFeedItem::getTitle).forEach(title -> log.debug(title));
    }


}
