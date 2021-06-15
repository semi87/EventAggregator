package cloud.dataflow.city.schema.generator;

import cloud.dataflow.city.common.FileUtil;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

import java.io.IOException;

public class GenerateAvsvSchemaFiles {
    public static void main(String[] args) throws IOException {
        Schema eventStatisticSubjectActivities = SchemaBuilder.record("EventStatisticSubjectActivities")
                .namespace("cloud.dataflow.city.entity")
                .fields()
                    .requiredString("type")
                    .requiredInt("past7daysCount")
                    .requiredInt("past7daysUniqueCount")
                    .requiredInt("past30daysCount")
                    .requiredInt("past30daysUniqueCount")
                .endRecord();

        Schema eventStatisticSubject = SchemaBuilder.record("EventStatisticSubject")
                .namespace("cloud.dataflow.city.entity")
                .fields()
                    .requiredLong("id")
                    .requiredString("type")
                    .name("activities")
                        .type().array().items().type(eventStatisticSubjectActivities).noDefault()
                .endRecord();

        Schema eventStatistic = SchemaBuilder.record("EventStatistic")
                .namespace("cloud.dataflow.city.entity")
                .fields()
                    .name("subjects")
                        .type().array().items().type(eventStatisticSubject).noDefault()
                .endRecord();

        System.out.println(eventStatistic.toString());
        FileUtil.saveAvscToFile(eventStatistic.toString(), "EventStatistic");
    }
}
