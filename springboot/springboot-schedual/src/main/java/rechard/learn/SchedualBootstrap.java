package rechard.learn;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.listeners.SchedulerListenerSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
public class SchedualBootstrap {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public static void main(String[] args) {
        SpringApplicationBuilder builder =new SpringApplicationBuilder(SchedualBootstrap.class);
        builder.run(args);
    }

   @Autowired
   Scheduler scheduler;

   /* @ConfigurationProperties("spring.datasource")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }
*/

/*   @Bean
   public SchedulerFactoryBeanCustomizer customizer(DataSource ds){
       return (schedulerFactoryBean)->{
           schedulerFactoryBean.setDataSource(ds);
       };
   }*/

    @PostConstruct
    public void init(){
       try {
            scheduler.getListenerManager().addSchedulerListener(new SchedulerListenerSupport(){
                @Override
                public void jobDeleted(JobKey jobKey) {
                    logger.info(jobKey.getName()+" is deleted");
                }
                public void jobAdded(JobDetail jobDetail) {
                    logger.info(jobDetail.getKey().getName()+" is add");
                }
                public void jobScheduled(Trigger trigger) {
                    logger.info(trigger.getJobKey()+" is trigger");
                }
            });
            /*scheduler.getListenerManager().addTriggerListener(new TriggerListener(){
                @Override
                public String getName() {
                    return "trigger name";
                }

                @Override
                public void triggerFired(Trigger trigger, JobExecutionContext context) {
                    JobDataMap dataMap = context.getMergedJobDataMap();
                    logger.info("trigger [ "+trigger.getKey().getName()+"] fired");
                    logger.info("jobName [ "+dataMap.getString("jobName")+"]");
                }

                @Override
                public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
                    return false;
                }

                @Override
                public void triggerMisfired(Trigger trigger) {

                }

                @Override
                public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
                    JobDataMap dataMap = context.getMergedJobDataMap();
                    logger.info("trigger [ "+trigger.getKey().getName()+"] completed");
                    logger.info("jobName [ "+dataMap.getString("jobName")+"]");
                    logger.info(triggerInstructionCode.name());
                }
            });
            scheduler.getListenerManager().addJobListener(new JobListener(){
                @Override
                public String getName() {
                    return "listern-1";
                }

                // jobDetail 执行之前
                public void jobToBeExecuted(JobExecutionContext context) {
                    JobDataMap dataMap = context.getMergedJobDataMap();
                    logger.info("jobName [ "+dataMap.getString("jobName")+"] to be executed");
                }

                @Override
                public void jobExecutionVetoed(JobExecutionContext context) {
                    logger.info("jobExecutionVetoed");
                }

                // jobDetail 执行之后
                public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
                    JobDataMap dataMap = context.getMergedJobDataMap();
                    logger.info("jobName [ "+dataMap.getString("jobName")+"] was executed");
                }
            });*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

   /* @Bean
    public JobDetail jobDetail(){
        JobDetail job = newJob(MyJob.class)
                .storeDurably()
                .withIdentity("job1", "group1")
                .build();
        return job;
    }

    @Bean
    public Trigger trigger(JobDetail detail){
        // Trigger the job to run now, and then repeat every 2 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .forJob(detail)
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();
        return trigger;
    }*/
}
