package hr.leapwise.rsshottopic.domain.service.impl;

import hr.leapwise.rsshottopic.api.dto.TopicDto;
import hr.leapwise.rsshottopic.domain.dbo.Topic;
import hr.leapwise.rsshottopic.domain.mapper.Mapper;
import hr.leapwise.rsshottopic.domain.service.TopicService;
import hr.leapwise.rsshottopic.persistence.service.TopicRepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private static final Integer HOTTEST_TOPIC_SIZE = 3;
    private final TopicRepositoryService topicRepositoryService;

    @Override
    public List<Topic> saveAll(List<Topic> topics, String analyseId) {
        topics.stream().forEach(topic -> topic.setAnalyseId(analyseId));
        return topicRepositoryService.saveAll(topics).stream().map(topicEntity->Mapper.map(topicEntity,Topic.class)).toList();
    }

    @Override
    public List<TopicDto> getHottestTopics(List<Topic> topicList) {
        return topicList.stream()
                .sorted(Comparator.reverseOrder())
                .limit(HOTTEST_TOPIC_SIZE)
                .map(topic -> Mapper.map(topic, TopicDto.class))
                .toList();
    }


}
