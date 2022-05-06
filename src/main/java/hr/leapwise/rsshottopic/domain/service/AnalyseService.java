package hr.leapwise.rsshottopic.domain.service;


import hr.leapwise.rsshottopic.domain.dbo.Analyse;
import java.util.List;


public interface AnalyseService {
    String analyseRssFeeds(List<String> rssUrlList);
    Analyse getAnalyse(String analyseId);
}
