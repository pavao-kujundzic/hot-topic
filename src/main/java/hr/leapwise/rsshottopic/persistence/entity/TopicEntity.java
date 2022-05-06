package hr.leapwise.rsshottopic.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "topic")
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Integer count;

    @JsonIgnoreProperties("topics")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyse_id", nullable = false)
    private AnalyseEntity analyse;


}
