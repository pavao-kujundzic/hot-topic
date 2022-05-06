package hr.leapwise.rsshottopic.domain.service.impl;

import hr.leapwise.rsshottopic.api.dto.RssFeedItemDto;
import hr.leapwise.rsshottopic.domain.dbo.RssFeedItem;
import hr.leapwise.rsshottopic.domain.mapper.Mapper;
import hr.leapwise.rsshottopic.domain.service.RssFeedItemService;
import hr.leapwise.rsshottopic.persistence.service.RssFeedItemRepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static hr.leapwise.rsshottopic.domain.rss.RssFeedTopicExtractor.TITLE_DELIMITER;

@Service
@AllArgsConstructor
public class RssFeedItemServiceImpl implements RssFeedItemService {
    private final RssFeedItemRepositoryService rssFeedItemRepositoryService;

    public void saveAll(List<RssFeedItem> rssFeedItemList, String analyseId) {
        rssFeedItemList.stream().forEach(rssFeedItem -> rssFeedItem.setAnalyseId(analyseId));
        rssFeedItemRepositoryService.saveAll(rssFeedItemList);
    }

    @Override
    public List<RssFeedItemDto> getRssFeedItems(List<RssFeedItem> rssFeedItems, List<String> topicList) {
        return rssFeedItems.stream()
                .filter(rssFeedItem -> contains(rssFeedItem.getTitle(), topicList))
                .map(rssFeedItem -> Mapper.map(rssFeedItem, RssFeedItemDto.class))
                .toList();
    }

    private Boolean contains(String sentence, List<String> words) {
        for (String word : words) {
            if (sentence.split(TITLE_DELIMITER)[0].toLowerCase().contains(word.toLowerCase())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
