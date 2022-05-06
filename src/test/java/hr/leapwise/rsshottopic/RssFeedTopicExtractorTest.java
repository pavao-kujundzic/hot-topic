package hr.leapwise.rsshottopic;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.*;
import hr.leapwise.rsshottopic.domain.rss.RssFeedTopicExtractor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

@Slf4j
@SpringBootTest
public class RssFeedTopicExtractorTest {

    @Autowired
    StanfordCoreNLP pipeline;

    @Autowired
    RssFeedTopicExtractor rssFeedTopicExtractor;
    
    private String combinedTitles = "White House scrambles for ways to protect abortion . Attacks on Mariupol steelworks intensify as Russia looks to end standoff  fate of Ukraine s Donbas in the balance . Trump justices accused of going back on their word on Roe – but did they . Democrats use leaked SCOTUS opinion to push bill legalizing some abortions for all 9 months . Search for Alabama corrections officer and inmate enters 7th day as officials piece together why they disappeared . U S  intelligence helping Ukraine kill Russian generals  NY Times . How J D  Vance Won in Ohio  A Trump Endorsement  a Fox News Stage and Money . Mayor Wheeler reacts to damage following protest   I m not anti protest  I m anti stupid . Gas  food prices will continue to climb with ‘no end in sight ’ economics professor warns . 2022 Midterm Primary Elections Are Underway  Here s What to Know . Russia intensifies attacks on railways  taking aim at Ukraine s lifeline to the outside world . Northern Ireland poised to make history as UK votes . China s Protracted Covid Lockdowns Draw Criticism From Fauci . Pentagon says Russia s attempts to hit Western weapons flowing into Ukraine having  no impact . Oil giant Shell reports highest quarterly profit since 2008 on soaring commodity prices . Facebook plans to reduce hiring as revenue growth slows and inflation concerns increase . Uber tried to outrun the fears Lyft created  but its stock is getting smacked anyway . Mainland China stocks rise on return to trade  as Asia stocks mostly climb following Fed rate hike . 7 amazing Google Maps tricks you had no idea existed . iPhone City goes into immediate lockdown after COVID 19 outbreak  recruitment suspended . Google Pixel Could Be Coming Soon In Foldable Avatar . Elden Ring Player Has Unintentionally Epic Fight With Crucible Knight . Inside ‘Top Gun  Maverick’s’ San Diego Premiere Spectacle  Including Tom Cruise’s Helicopter Arrival . Ray J Claims Sex Tape with Kim Kardashian was a Partnership with Kris Jenner . What to know about late ‘Toddlers and Tiaras’ star Kailia Posey   She will be remembered for her sweet spirit . Man accused of tackling comic Dave Chappelle on stage is charged with assault . Suns vs  Mavericks  Luka Doncic has to be held back from confronting fan during heated Game 2 in Phoenix . Cowboys owner Jerry Jones hospitalized after car crash . God needs to come and explain it   How the football world reacted to Real Madrid s extraordinary Champions League semifinal victory . Why Miami GP is having the last laugh over fake marina memes . Cosmonaut assumes command of ISS ahead of Thursday morning departure of SpaceX s Crew 3 mission . Eta Aquarid Meteor Shower  How and When to Watch . Rocket Lab CEO touts successful helicopter catch of rocket as key toward reusable goals . Piece Falls Off Boeing Starliner as It Trundles Toward Launchpad . Omicron as severe as previous COVID variants  large study finds . Mysterious liver illness in kids continues to spread  N J  parents should look for these signs . Cognitive Impact of Severe COVID Is Equivalent to 20 Years of Aging  Study Finds . DOH investigating hepatitis case in Brown County child . White House scrambles for ways to protect abortion . Attacks on Mariupol steelworks intensify as Russia looks to end standoff  fate of Ukraine s Donbas in the balance . Trump justices accused of going back on their word on Roe – but did they . Democrats use leaked SCOTUS opinion to push bill legalizing some abortions for all 9 months . Search for Alabama corrections officer and inmate enters 7th day as officials piece together why they disappeared . U S  intelligence helping Ukraine kill Russian generals  NY Times . How J D  Vance Won in Ohio  A Trump Endorsement  a Fox News Stage and Money . Mayor Wheeler reacts to damage following protest   I m not anti protest  I m anti stupid . Gas  food prices will continue to climb with ‘no end in sight ’ economics professor warns . 2022 Midterm Primary Elections Are Underway  Here s What to Know . Russia intensifies attacks on railways  taking aim at Ukraine s lifeline to the outside world . Northern Ireland poised to make history as UK votes . China s Protracted Covid Lockdowns Draw Criticism From Fauci . Pentagon says Russia s attempts to hit Western weapons flowing into Ukraine having  no impact . Oil giant Shell reports highest quarterly profit since 2008 on soaring commodity prices . Facebook plans to reduce hiring as revenue growth slows and inflation concerns increase . Uber tried to outrun the fears Lyft created  but its stock is getting smacked anyway . Mainland China stocks rise on return to trade  as Asia stocks mostly climb following Fed rate hike . 7 amazing Google Maps tricks you had no idea existed . iPhone City goes into immediate lockdown after COVID 19 outbreak  recruitment suspended . Google Pixel Could Be Coming Soon In Foldable Avatar . Elden Ring Player Has Unintentionally Epic Fight With Crucible Knight . Inside ‘Top Gun  Maverick’s’ San Diego Premiere Spectacle  Including Tom Cruise’s Helicopter Arrival . Ray J Claims Sex Tape with Kim Kardashian was a Partnership with Kris Jenner . What to know about late ‘Toddlers and Tiaras’ star Kailia Posey   She will be remembered for her sweet spirit . Man accused of tackling comic Dave Chappelle on stage is charged with assault . Suns vs  Mavericks  Luka Doncic has to be held back from confronting fan during heated Game 2 in Phoenix . Cowboys owner Jerry Jones hospitalized after car crash . God needs to come and explain it   How the football world reacted to Real Madrid s extraordinary Champions League semifinal victory . Why Miami GP is having the last laugh over fake marina memes . Cosmonaut assumes command of ISS ahead of Thursday morning departure of SpaceX s Crew 3 mission . Eta Aquarid Meteor Shower  How and When to Watch . Rocket Lab CEO touts successful helicopter catch of rocket as key toward reusable goals . Piece Falls Off Boeing Starliner as It Trundles Toward Launchpad . Omicron as severe as previous COVID variants  large study finds . Mysterious liver illness in kids continues to spread  N J  parents should look for these signs . Cognitive Impact of Severe COVID Is Equivalent to 20 Years of Aging  Study Finds . DOH investigating hepatitis case in Brown County child . ";


    private List<String> titles = List.of("White House scrambles for ways to protect abortion.",
            "Attacks on Mariupol steelworks intensify as Russia looks to end standoff  fate of Ukraine s Donbas in the balance.",
            "Trump justices accused of going back on their word on Roe – but did they .",
            "Democrats use leaked SCOTUS opinion to push bill legalizing some abortions for all 9 months.",
            "Search for Alabama corrections officer and inmate enters 7th day as officials piece together why they disappeared .",
            "Man accused of tackling comic Dave Chappelle on stage is charged with assault .",
            "Suns vs  Mavericks  Luka Doncic has to be held back from confronting fan during heated Game 2 in Phoenix .");


    @Test
    public void rssReedTopicExtractViewTest() {
        rssFeedTopicExtractor.extractTopics(titles).forEach(topic -> log.info(topic));
    }











    //Unit test implemented while developing logic
    @Test
    public void nlpNamedEntityMentionsTest() {
        CoreDocument coreDocument = new CoreDocument(combinedTitles);
        pipeline.annotate(coreDocument);

        List<String> tokens = new ArrayList<>();
        for (CoreSentence sentence : coreDocument.sentences()) {
            for (CoreEntityMention entityMention : sentence.entityMentions()) {
                if (isNamedEntityMention(entityMention)) {
                    tokens.add(entityMention.text());
                }
            }
        }

        tokens.forEach(token->log.debug(token));
    }

    private boolean isNamedEntityMention(CoreEntityMention entityMention) {
        for (CoreLabel token : entityMention.tokens()) {
            if (!token.get(CoreAnnotations.PartOfSpeechAnnotation.class).startsWith("NNP"))
                return false;
        }
        return true;
    }
}

