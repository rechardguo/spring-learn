package rechard.learn.springbatch.sample.simple;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonProcessor {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    public void print(Person p){
        logger.debug(p.toString());
    }
}
