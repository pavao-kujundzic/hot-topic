package hr.leapwise.rsshottopic.persistence.service.impl;

import hr.leapwise.rsshottopic.domain.dbo.Topic;
import hr.leapwise.rsshottopic.domain.mapper.Mapper;
import hr.leapwise.rsshottopic.persistence.entity.TopicEntity;
import hr.leapwise.rsshottopic.persistence.repository.TopicRepository;
import hr.leapwise.rsshottopic.persistence.service.IdGeneratorService;
import hr.leapwise.rsshottopic.persistence.service.TopicRepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class TopicRepositoryServiceImpl implements TopicRepositoryService {
    private IdGeneratorService idGeneratorService;
    private TopicRepository topicRepository;

    @Override
    public List<TopicEntity> saveAll(List<Topic> topicList) {
        log.info("Saving {} topics", topicList.size());
        List<TopicEntity> topicEntityList = new ArrayList<>();
        topicList.forEach(topic -> {
            topic.setId(idGeneratorService.generateId().toString());
            topicEntityList.add(topicRepository.save(Mapper.map(topic, TopicEntity.class)));
        });
        log.info("Saved {} topics", topicEntityList.size());
        return topicEntityList;
    }
}
