package rechard.learn.springbatch.sample.simple;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SimpleWithDsDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =  new AnnotationConfigApplicationContext();
        ctx.register(SimpleBatchConfiguration.class);
        ctx.refresh();
        JobLauncher launcher = (JobLauncher)ctx.getBean("jobLauncher");
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addLong("readCountPerTime",10L);
        JobParameters jobParameters = jobParametersBuilder.toJobParameters();
        //每次读取2条
        try {
            launcher.run((Job)ctx.getBean("simpleJob"),jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mariadb://localhost:3306/ssmdb");
        dataSource.setUsername("root");
        dataSource.setPassword("devuser");
        return dataSource;
    }

}
