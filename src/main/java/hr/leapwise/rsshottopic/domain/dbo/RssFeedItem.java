package hr.leapwise.rsshottopic.domain.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RssFeedItem {
    private String id;
    private String title;
    private String link;
    private String analyseId;
}
