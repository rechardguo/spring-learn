package rechard.learn.springboot.schedual;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rechard.learn.quartz.MyJob;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RestController
public class schedualController {
    @Autowired
    Scheduler scheduler;

    @PostMapping("/schedual/add")
    public void addSchedual(@RequestBody JobBean jobBean){
        try {
            JobDetail jobDetail = newJob(MyJob.class)
                    .withIdentity(jobBean.getJobName(), "group1")
                    .usingJobData("jobName", jobBean.getJobName())
                    .storeDurably()
                    .build();
            scheduler.addJob(jobDetail,true);
            // Trigger the job to run now, and then repeat every 2 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger-"+jobBean.getJobName(), "group1")
                    .forJob(jobDetail)
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(jobBean.getIntervalSecond())
                            .repeatForever())
                    .build();
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/schedual/{key}/delete")
    public void deleteSchedual(@PathVariable String key){
        JobKey jobKey = new JobKey(key,"group1");
        try {
            if(scheduler.checkExists(jobKey)){
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }


    @PostMapping("/schedual/list")
    public void deleteSchedual(){
    }
}
