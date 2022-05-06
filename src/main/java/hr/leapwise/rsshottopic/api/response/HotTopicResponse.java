package hr.leapwise.rsshottopic.api.response;

import hr.leapwise.rsshottopic.api.dto.RssFeedItemDto;
import hr.leapwise.rsshottopic.api.dto.TopicDto;
import lombok.Builder;

import java.util.List;

@Builder
public record HotTopicResponse(List<TopicDto> topicDtoList, List<RssFeedItemDto> rssFeedItemDtoList) {
}
