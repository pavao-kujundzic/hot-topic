package hr.leapwise.rsshottopic.domain.service;

import hr.leapwise.rsshottopic.api.dto.TopicDto;
import hr.leapwise.rsshottopic.domain.dbo.Topic;

import java.util.List;

public interface TopicService {

    List<Topic> saveAll(List<Topic> hotTopics, String analyseId);

    List<TopicDto> getHottestTopics(List<Topic> topicList);
}
