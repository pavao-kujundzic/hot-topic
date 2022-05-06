package hr.leapwise.rsshottopic.domain.service.impl;

import hr.leapwise.rsshottopic.api.dto.RssFeedItemDto;
import hr.leapwise.rsshottopic.api.dto.TopicDto;
import hr.leapwise.rsshottopic.api.response.HotTopicResponse;
import hr.leapwise.rsshottopic.domain.dbo.Analyse;
import hr.leapwise.rsshottopic.domain.service.AnalyseService;
import hr.leapwise.rsshottopic.domain.service.FrequencyService;
import hr.leapwise.rsshottopic.domain.service.RssFeedItemService;
import hr.leapwise.rsshottopic.domain.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FrequencyServiceImpl implements FrequencyService {

    private final RssFeedItemService rssFeedItemService;
    private final TopicService topicService;
    private AnalyseService analyseService;


    @Override
    public HotTopicResponse getTopicFrequencies(String analyseId) {
        Analyse analyse = analyseService.getAnalyse(analyseId);
        log.info("Found analyse with id: {}", analyseId);
        List<TopicDto> hottestTopics = topicService.getHottestTopics(analyse.getTopics());
        log.info("Fetched topics with most occurrences");
        List<RssFeedItemDto> relatedRssFeedItems = rssFeedItemService.getRssFeedItems(analyse.getRssFeedItems(), hottestTopics.stream().map(TopicDto::getName).collect(Collectors.toList()));
        log.info("Fetched related rss feed items");
        return HotTopicResponse.builder()
                .topicDtoList(hottestTopics)
                .rssFeedItemDtoList(relatedRssFeedItems)
                .build();

    }
}
