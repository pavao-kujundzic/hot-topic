package hr.leapwise.rsshottopic.domain.service.impl;

import hr.leapwise.rsshottopic.domain.dbo.Analyse;
import hr.leapwise.rsshottopic.domain.dbo.Topic;
import hr.leapwise.rsshottopic.domain.exception.AnalyseNotFoundException;
import hr.leapwise.rsshottopic.domain.exception.InvalidRequestException;
import hr.leapwise.rsshottopic.domain.mapper.Mapper;
import hr.leapwise.rsshottopic.domain.rss.RssFeedTopicExtractor;
import hr.leapwise.rsshottopic.domain.dbo.RssFeedItem;
import hr.leapwise.rsshottopic.domain.rss.RssFeedReader;
import hr.leapwise.rsshottopic.domain.service.AnalyseService;
import hr.leapwise.rsshottopic.domain.service.RssFeedItemService;
import hr.leapwise.rsshottopic.domain.service.TopicService;
import hr.leapwise.rsshottopic.persistence.service.AnalyseRepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AnalyseServiceImpl implements AnalyseService {


    public static final int MINIMUM_NUMBER_OF_RSS_URLS = 2;

    private AnalyseRepositoryService analyseRepositoryService;
    private final RssFeedItemService rssFeedItemService;
    private final TopicService topicService;
    private final RssFeedTopicExtractor rssFeedTopicExtractor;

    @Override
    @Transactional
    public String analyseRssFeeds(List<String> rssUrlList) {
        log.info("Starting analyse of Rss feeds");
        List<RssFeedItem> rssFeedItemList = readRssFeed(rssUrlList);
        List<Topic> topicList = analyseTopics(rssFeedItemList);
        return createAnalyse(rssFeedItemList, topicList);
    }

    @Override
    public Analyse getAnalyse(String analyseId) {
        try {
            return Mapper.map(analyseRepositoryService.findById(analyseId), Analyse.class);
        } catch (NoSuchElementException e) {
            throw new AnalyseNotFoundException("No analyse found for id: " + analyseId);
        }
    }

    private List<RssFeedItem> readRssFeed(List<String> rssUrlList) {
        validateRequest(rssUrlList);
        return RssFeedReader.readItemFromUrls(rssUrlList);
    }

    private void validateRequest(List<String> rssUrlList) {
        log.info("Validating Rss Feed urls.");
        if (Objects.isNull(rssUrlList) || rssUrlList.isEmpty())
            throw new InvalidRequestException("RSS urls must be present for analyse.");

        if (rssUrlList.size() < MINIMUM_NUMBER_OF_RSS_URLS)
            throw new InvalidRequestException("Minimum number of RSS urls must be " + MINIMUM_NUMBER_OF_RSS_URLS);

        log.info("Rss Feed urls are valid.");
    }

    private List<Topic> analyseTopics(List<RssFeedItem> rssFeedItemList) {
        return createTopics(rssFeedTopicExtractor.extractTopics(rssFeedItemList.stream().map(RssFeedItem::getTitle).collect(Collectors.toList())));
    }

    private List<Topic> createTopics(List<String> topicList) {
        log.info("Creating topics");
        Map<String, Integer> topicMap = new HashMap<>();
        topicList.forEach(topic -> {
            int count = topicMap.containsKey(topic) ? topicMap.get(topic).intValue() + 1 : 1;
            topicMap.put(topic, count);
        });
        log.info("Created {} topics", topicMap.size());
        return topicMap.entrySet().stream().map(entry -> new Topic(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    private String createAnalyse(List<RssFeedItem> rssFeedItemList, List<Topic> topicList) {
        log.info("Creating analyse from  {} titles with {} hot topics", rssFeedItemList.size(), topicList.size());
        String analyseId = analyseRepositoryService.save(new Analyse()).getId();
        rssFeedItemService.saveAll(rssFeedItemList, analyseId);
        topicService.saveAll(topicList, analyseId);
        log.info("Analyse created with id: {}", analyseId);
        return analyseId;
    }
}
