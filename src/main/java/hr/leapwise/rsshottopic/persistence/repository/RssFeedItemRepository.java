package hr.leapwise.rsshottopic.persistence.repository;

import hr.leapwise.rsshottopic.persistence.entity.RssFeedItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RssFeedItemRepository extends JpaRepository<RssFeedItemEntity, Long> {
}
