package rechard.learn.springboot.schedual;

public class JobBean {
    private String jobName;
    private int intervalSecond;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getIntervalSecond() {
        return intervalSecond;
    }

    public void setIntervalSecond(int intervalSecond) {
        this.intervalSecond = intervalSecond;
    }
}
