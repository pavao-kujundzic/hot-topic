package hr.leapwise.rsshottopic.domain.dbo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Analyse {
    private List<Topic> topics;
    private List<RssFeedItem> rssFeedItems;
}
