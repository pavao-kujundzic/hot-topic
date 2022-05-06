package hr.leapwise.rsshottopic.api.request;

import java.util.List;

public record AnalyseServiceRequest(List<String> rssUrls) {
}
