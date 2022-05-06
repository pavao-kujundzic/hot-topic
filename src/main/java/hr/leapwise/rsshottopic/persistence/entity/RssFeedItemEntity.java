package hr.leapwise.rsshottopic.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "rss_feed_item")
@NoArgsConstructor
@AllArgsConstructor
public class RssFeedItemEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    @JsonIgnoreProperties("rssFeedItems")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyse_id", nullable = false)
    private AnalyseEntity analyse;

}
