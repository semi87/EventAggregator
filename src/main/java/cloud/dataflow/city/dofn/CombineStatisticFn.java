package cloud.dataflow.city.dofn;

import cloud.dataflow.city.entity.*;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class CombineStatisticFn extends DoFn<KV<String, Iterable<Event>>, EventStatistic> {
    @ProcessElement
    public void processElement(ProcessContext c, OutputReceiver<EventStatistic> receiver) {
        Iterable<Event> ctx = c.element().getValue();
        int past7DaysCount;
        int past30DaysCount;
        int past7DaysUniqueCount;
        int past30DaysUniqueCount;

        Map<Long, List<Event>> map = StreamSupport.stream(ctx.spliterator(), false)
                .collect(Collectors.groupingBy(a -> a.getEventSubject().getId()));
        List<EventStatisticSubject> eventStatisticSubjectList = new ArrayList<>();
        for (Map.Entry<Long, List<Event>> groupEvents : map.entrySet()) {
            past7DaysCount = 0;
            past30DaysCount = 0;
            past7DaysUniqueCount = 0;
            past30DaysUniqueCount = 0;

            EventStatisticSubject eventStatisticSubject = new EventStatisticSubject();
            List<EventStatisticSubjectActivities> eventSubjectActivitiesList = new ArrayList<>();
            EventStatisticSubjectActivities eventSubjectActivities = new EventStatisticSubjectActivities();
            for (Event event : groupEvents.getValue()) {
                past7DaysCount++;
                past30DaysCount++;
                if (event.getTimestamp() <= 30) {
                    past30DaysUniqueCount++;
                    if (event.getTimestamp() <= 7)
                        past7DaysUniqueCount++;
                }
                eventSubjectActivities.setType(event.getEventType());
                eventStatisticSubject.setId(event.getEventSubject().getId());
                eventStatisticSubject.setType(event.getEventSubject().getType());
            }
            eventSubjectActivities.setPast7daysCount(past7DaysCount);
            eventSubjectActivities.setPast7daysUniqueCount(past7DaysUniqueCount);
            eventSubjectActivities.setPast30daysCount(past30DaysCount);
            eventSubjectActivities.setPast30daysUniqueCount(past30DaysUniqueCount);

            eventSubjectActivitiesList.add(eventSubjectActivities);
            eventStatisticSubject.setActivities(eventSubjectActivitiesList);
            eventStatisticSubjectList.add(eventStatisticSubject);
        }

        EventStatistic eventStatistic = EventStatistic.newBuilder().setSubjects(eventStatisticSubjectList)
                .build();
        receiver.output(eventStatistic);
    }
}