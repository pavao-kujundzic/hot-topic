package hr.leapwise.rsshottopic.domain.service;

import hr.leapwise.rsshottopic.api.response.HotTopicResponse;

public interface FrequencyService {
    HotTopicResponse getTopicFrequencies(String analyseId);
}
