package hr.leapwise.rsshottopic.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "analyse")
@NoArgsConstructor
@AllArgsConstructor
public class AnalyseEntity {

    @Id
    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "analyse")
    private List<RssFeedItemEntity> rssFeedItems;

    @OneToMany(mappedBy = "analyse")
    private List<TopicEntity> topics;

}
