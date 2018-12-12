package rechard.learn.springbatch.sample.simple;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.adapter.ItemWriterAdapter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 从文件里读取内容，文件里一行代表人的信息
 * input : reader<String>  <String> 代表input就是文件里的一行内容
 * output: writer<Person>
 */
@Configuration
@ComponentScan("rechard.learn.springbatch.sample.simple")
@EnableBatchProcessing
public class SimpleBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public Job simpleJob(Step step){
        return jobBuilders.get("simpleJob").start(step).build();
    }

    @Bean
    protected Step step(ItemReader<String> reader,
                         ItemWriter<Person> writer) {
        return steps.get("step1")
                .<String, Person> chunk(10)
                .reader(reader)
                .writer(writer)
                .allowStartIfComplete(true)
                .build();
    }
    @Bean
    protected ItemReader<String> reader(){
        FlatFileItemReader reader=new FlatFileItemReader();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File("E:\\code\\my_github_code\\spring-learn\\springboot-web-integration\\springboot-batch\\src\\main\\java\\rechard\\learn\\springbatch\\sample\\simple\\person.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        reader.setResource( new InputStreamResource(fis));
        reader.setLineMapper((line,number)->{
            String[] str = line.split(",");
            Person p = new Person();
            p.setId(Integer.parseInt(str[0]));
            p.setName(str[1]);
            p.setAge(Integer.parseInt(str[2]));
            return p;
        });
        return reader;
    }

    @Bean
    protected ItemWriter<Person> writer(){
        ItemWriterAdapter<Person> adapter = new ItemWriterAdapter();
        adapter.setTargetMethod("print");
        adapter.setTargetObject(new PersonProcessor());
        return adapter;
    }

}
