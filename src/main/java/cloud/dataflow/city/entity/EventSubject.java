package cloud.dataflow.city.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.StringJoiner;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventSubject implements Serializable {
    private Long id;
    private String type;
    private List<EventSubjectActivities> eventSubjectActivitiesList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventSubject that = (EventSubject) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EventSubject.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("type='" + type + "'")
                .toString();
    }
}
