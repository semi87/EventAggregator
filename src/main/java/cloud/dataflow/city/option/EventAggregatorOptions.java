package cloud.dataflow.city.option;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;

public interface EventAggregatorOptions extends PipelineOptions {

    @Description("Path of the file to read form")
    @Default.String("src/main/resources/input/*.jsonl")
    String getInputFiles();

    void setInputFiles(String value);

    @Description("Path of the file to write to")
    @Default.String("src/main/resources/output/result")
    String getOutput();

    void setOutput(String value);
}
