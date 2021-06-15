package cloud.dataflow.city.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.StringJoiner;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event implements Serializable {
    private Long id;
    private Long userId;
    private String city;
    private String eventType;
    private Long timestamp;
    private EventSubject eventSubject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (userId != null ? !userId.equals(event.userId) : event.userId != null) return false;
        if (city != null ? !city.equals(event.city) : event.city != null) return false;
        if (eventType != null ? !eventType.equals(event.eventType) : event.eventType != null) return false;
        if (timestamp != null ? !timestamp.equals(event.timestamp) : event.timestamp != null) return false;
        return eventSubject != null ? eventSubject.equals(event.eventSubject) : event.eventSubject == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (eventSubject != null ? eventSubject.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Event.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userId=" + userId)
                .add("city='" + city + "'")
                .add("eventType='" + eventType + "'")
                .add("timestamp=" + timestamp)
                .add("eventSubject=" + eventSubject)
                .toString();
    }
}
