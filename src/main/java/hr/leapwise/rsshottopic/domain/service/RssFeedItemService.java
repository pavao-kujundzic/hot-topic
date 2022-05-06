package hr.leapwise.rsshottopic.domain.service;

import hr.leapwise.rsshottopic.api.dto.RssFeedItemDto;
import hr.leapwise.rsshottopic.domain.dbo.RssFeedItem;

import java.util.List;

public interface RssFeedItemService {
    void saveAll(List<RssFeedItem> rssFeedItemList, String analyseId);

    List<RssFeedItemDto> getRssFeedItems(List<RssFeedItem> rssFeedItemList, List<String> topicList);
}
