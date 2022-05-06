package hr.leapwise.rsshottopic.api.controller;


import hr.leapwise.rsshottopic.api.response.HotTopicResponse;
import hr.leapwise.rsshottopic.domain.service.FrequencyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/frequency")
@AllArgsConstructor
public class FrequencyController {

    private final FrequencyService frequencyService;

    @GetMapping("/{id}")
    public HotTopicResponse getFrequencies(@PathVariable(name = "id") String analyseId) {
        return frequencyService.getTopicFrequencies(analyseId);
    }

}
