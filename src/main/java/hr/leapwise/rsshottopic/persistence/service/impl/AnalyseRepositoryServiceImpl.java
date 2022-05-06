package hr.leapwise.rsshottopic.persistence.service.impl;

import hr.leapwise.rsshottopic.domain.dbo.Analyse;
import hr.leapwise.rsshottopic.domain.mapper.Mapper;
import hr.leapwise.rsshottopic.persistence.entity.AnalyseEntity;
import hr.leapwise.rsshottopic.persistence.repository.AnalyseRepository;
import hr.leapwise.rsshottopic.persistence.service.AnalyseRepositoryService;
import hr.leapwise.rsshottopic.persistence.service.IdGeneratorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@AllArgsConstructor
public class AnalyseRepositoryServiceImpl implements AnalyseRepositoryService {
    private IdGeneratorService idGeneratorService;
    private AnalyseRepository analyseRepository;

    @Override
    public AnalyseEntity save(Analyse analyse) {
       log.info("Saving analyse");
        AnalyseEntity analyseEntity = Mapper.map(analyse, AnalyseEntity.class);
        analyseEntity.setId(idGeneratorService.generateId().toString());
        return analyseRepository.save(analyseEntity);
    }

    @Override
    public AnalyseEntity findById(String analyseId) {
        return analyseRepository.findById(analyseId).orElseThrow();
    }
}
