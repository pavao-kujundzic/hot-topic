package hr.leapwise.rsshottopic.domain.rss;


import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import hr.leapwise.rsshottopic.domain.dbo.RssFeedItem;
import hr.leapwise.rsshottopic.domain.exception.RssFeedReaderException;
import hr.leapwise.rsshottopic.domain.mapper.Mapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
public class RssFeedReader {


    public static List<RssFeedItem> readItemFromUrls(List<String> rssUrlList) {
        List<RssFeedItem> rssFeedItems = new ArrayList<>();
        log.info("Started RSS feed reader");
        for (String rssUrl : rssUrlList) {
            rssFeedItems.addAll(readItems(rssUrl));
        }
        log.info("Ended RSS feed reader");
        return rssFeedItems;
    }

    private static List<RssFeedItem> readItems(String rssUrl) {
        List<RssFeedItem> rssFeedItems = new ArrayList<>();
        log.info("Reading feeds from RSS url: {}", rssUrl);
        try {
            URL feedSource = new URL(rssUrl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            feed.getEntries().forEach(syndEntry -> rssFeedItems.add(Mapper.map(syndEntry, RssFeedItem.class)));
            log.info("Found {} items", rssFeedItems.size());

        } catch (MalformedURLException e) {
            throw new RssFeedReaderException("Exception while creating URL for :" + rssUrl);
        } catch (IOException e) {
            throw new RssFeedReaderException("Exception while reading with XML Reader from link: " + rssUrl);
        } catch (FeedException e) {
            throw new RssFeedReaderException("Exception while building RSS feed from link: " + rssUrl);
        }
        return rssFeedItems;
    }
}
