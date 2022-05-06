package hr.leapwise.rsshottopic.persistence.repository;

import hr.leapwise.rsshottopic.persistence.entity.AnalyseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyseRepository extends JpaRepository<AnalyseEntity, String> {
}
