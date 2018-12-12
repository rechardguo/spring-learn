package rechard.learn.springbatch.sample.simple.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rechard.learn.batch")
public class MyBatchProperties {

    private boolean enable =false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
