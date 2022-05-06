package hr.leapwise.rsshottopic.persistence.service;

import hr.leapwise.rsshottopic.domain.dbo.Analyse;
import hr.leapwise.rsshottopic.persistence.entity.AnalyseEntity;

public interface AnalyseRepositoryService {

    AnalyseEntity save(Analyse analyse);

    AnalyseEntity findById(String analyseId);
}
