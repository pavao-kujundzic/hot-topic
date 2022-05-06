package hr.leapwise.rsshottopic.persistence.repository;

import hr.leapwise.rsshottopic.persistence.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, String> {
}
