package rechard.learn.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DisallowConcurrentExecution
public class MyJob implements org.quartz.Job{

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String JobName;

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public MyJob() {
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        logger.info(key.getName());
        logger.info(getJobName()+" : [Hello World!  MyJob is executing.]");
    }
}
