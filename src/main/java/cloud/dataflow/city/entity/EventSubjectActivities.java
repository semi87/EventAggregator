package cloud.dataflow.city.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventSubjectActivities {
    private String type;
    private int past7daysCount;
    private int past7daysUniqueCount;
    private int past30daysCount;
    private int past30daysUniqueCount;
}
