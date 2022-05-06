package hr.leapwise.rsshottopic.domain.dbo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic implements Comparable<Topic> {

    private String id;
    private String name;
    private Integer count;
    private String analyseId;

    public Topic(String key, Integer value) {
        this(null, key, value, null);
    }

    @Override
    public int compareTo(Topic topic) {
        int compare = this.getCount() - topic.getCount();
        return compare == 0 ? this.getName().compareTo(topic.getName()) : compare;
    }

}
