package hr.leapwise.rsshottopic.persistence.service;


import hr.leapwise.rsshottopic.domain.dbo.RssFeedItem;
import hr.leapwise.rsshottopic.persistence.entity.RssFeedItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RssFeedItemRepositoryService {
    List<RssFeedItemEntity> saveAll(List<RssFeedItem> rssFeedItemList);


}
