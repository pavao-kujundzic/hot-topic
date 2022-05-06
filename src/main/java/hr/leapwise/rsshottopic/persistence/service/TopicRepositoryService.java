package hr.leapwise.rsshottopic.persistence.service;

import hr.leapwise.rsshottopic.domain.dbo.Topic;
import hr.leapwise.rsshottopic.persistence.entity.TopicEntity;

import java.util.List;

public interface TopicRepositoryService {
    List<TopicEntity> saveAll(List<Topic> topicList);


}
