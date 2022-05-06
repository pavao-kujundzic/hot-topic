package hr.leapwise.rsshottopic.persistence.service.impl;

import hr.leapwise.rsshottopic.domain.dbo.RssFeedItem;
import hr.leapwise.rsshottopic.domain.mapper.Mapper;
import hr.leapwise.rsshottopic.persistence.entity.RssFeedItemEntity;
import hr.leapwise.rsshottopic.persistence.repository.RssFeedItemRepository;
import hr.leapwise.rsshottopic.persistence.service.IdGeneratorService;
import hr.leapwise.rsshottopic.persistence.service.RssFeedItemRepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RssFeedItemRepositoryServiceImpl implements RssFeedItemRepositoryService {
    private IdGeneratorService idGeneratorService;
    private RssFeedItemRepository rssFeedItemRepository;


    @Override
    public List<RssFeedItemEntity> saveAll(List<RssFeedItem> rssFeedItemList) {
        log.info("Saving {} rss Feed items", rssFeedItemList.size());
        List<RssFeedItemEntity> rssFeedItemEntityList = new ArrayList<>();
        rssFeedItemList.forEach(rssFeedItem -> {
            rssFeedItem.setId(idGeneratorService.generateId().toString());
            rssFeedItemEntityList.add(rssFeedItemRepository.save(Mapper.map(rssFeedItem, RssFeedItemEntity.class)));
        });
        log.info("Saved {} rss Feed Items", rssFeedItemEntityList.size());
        return rssFeedItemEntityList;
    }

}
