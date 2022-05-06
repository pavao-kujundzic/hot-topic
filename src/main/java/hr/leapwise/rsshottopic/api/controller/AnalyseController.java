package hr.leapwise.rsshottopic.api.controller;

import hr.leapwise.rsshottopic.api.request.AnalyseServiceRequest;
import hr.leapwise.rsshottopic.api.response.AnalyseResponse;
import hr.leapwise.rsshottopic.domain.service.AnalyseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/analyse")
@AllArgsConstructor
public class AnalyseController {

    private final AnalyseService analyseService;

    @PostMapping("/new")
    public AnalyseResponse analyse(@RequestBody AnalyseServiceRequest analyseServiceRequest) {
        return AnalyseResponse.builder()
                .analyseId(analyseService.analyseRssFeeds(analyseServiceRequest.rssUrls()))
                .build();
    }

}
