package rechard.learn.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.adapter.ItemReaderAdapter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableBatchProcessing
public class SpringBatchSimpleDemoConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(step1())
                .preventRestart()
                .build();
    }

    @Bean
    public Step step1() {
        ItemReaderAdapter<String> itemReaderAdapter = new ItemReaderAdapter();
        itemReaderAdapter.setTargetObject(new InputStringReader());
        itemReaderAdapter.setTargetMethod("inputString");

        ItemWriterAdapter<String> itemWriterAdapter = new ItemWriterAdapter();
        itemWriterAdapter.setTargetObject(new OutputStringPrintWriter());
        itemWriterAdapter.setTargetMethod("process");

        return this.stepBuilderFactory.get("step1")
               .<String,String>chunk(new SimpleCompletionPolicy(5))
                .reader(itemReaderAdapter)
                .writer(itemWriterAdapter)
                .allowStartIfComplete(false)
                .build();
    }
}
