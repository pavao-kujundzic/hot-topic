package hr.leapwise.rsshottopic.domain.rss;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class RssFeedTopicExtractor {

    public static final String TITLE_DELIMITER = " - ";
    private StanfordCoreNLP stanfordCoreNLP;

    public List<String> extractTopics(List<String> titles) {
        log.info("Extracting topics from {} titles", titles.size());
        List<String> topicList = new ArrayList<>();
        CoreDocument coreDocument = new CoreDocument(combineTitlesForAnalyse(titles));
        stanfordCoreNLP.annotate(coreDocument);

        for (CoreSentence sentence : coreDocument.sentences()) {
            for (CoreEntityMention entityMention : sentence.entityMentions()) {
                if (isNamedEntityMention(entityMention)) {
                    topicList.add(entityMention.text());
                }
            }
        }
        log.info("Extracted {} possible topics from {} titles", topicList.size(), titles.size());
        return topicList;
    }

    private String combineTitlesForAnalyse(List<String> titleList) {
        StringBuilder fullText = new StringBuilder();
        List<String> cleanedText = titleList.stream().map(title -> title.split(TITLE_DELIMITER)[0]).collect(Collectors.toList());
        cleanedText.stream().forEach(title -> fullText.append(title + " . "));
        return fullText.toString();
    }

    private Boolean isNamedEntityMention(CoreEntityMention entityMention) {
        if (Objects.isNull(entityMention)) return Boolean.FALSE;
        for (CoreLabel token : entityMention.tokens()) {
            if (!token.get(CoreAnnotations.PartOfSpeechAnnotation.class).startsWith("NNP"))
                return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
