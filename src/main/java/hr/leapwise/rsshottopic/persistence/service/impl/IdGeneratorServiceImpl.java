package hr.leapwise.rsshottopic.persistence.service.impl;

import hr.leapwise.rsshottopic.persistence.service.IdGeneratorService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdGeneratorServiceImpl implements IdGeneratorService {

    @Override
    public UUID generateId() {
        return UUID.randomUUID();
    }
}
