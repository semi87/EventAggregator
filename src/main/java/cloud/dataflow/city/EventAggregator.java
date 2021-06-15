package cloud.dataflow.city;

import cloud.dataflow.city.dofn.CombineStatisticFn;
import cloud.dataflow.city.entity.Event;
import cloud.dataflow.city.entity.EventStatistic;
import cloud.dataflow.city.option.EventAggregatorOptions;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.SerializableCoder;
import org.apache.beam.sdk.extensions.jackson.ParseJsons;
import org.apache.beam.sdk.io.AvroIO;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.PCollection;

public class Main {
    public static void main(String[] args) {
        EventAggregatorOptions options =
                PipelineOptionsFactory.fromArgs(args).withValidation().as(EventAggregatorOptions.class);

        runEventAggregator(options);
    }

    private static void runEventAggregator(EventAggregatorOptions options) {
        Pipeline p = Pipeline.create(options);
        PCollection<EventStatistic> eventStatistics = p.apply("Reading JSONl", TextIO.read().from(options.getInputFiles()))
                .apply(ParseJsons.of(Event.class)).setCoder(SerializableCoder.of(Event.class))
                .apply(WithKeys.of(new SerializableFunction<Event, String>() {
                    public String apply(Event event) {
                        return event.getCity();
                    }
                }))
                .apply(GroupByKey.create())
                .apply(ParDo.of(new CombineStatisticFn()));


        eventStatistics.apply("Write Analyzing results", AvroIO.write(EventStatistic.class)
                .to(options.getOutput())
        );

        p.run().waitUntilFinish();
    }
}
