package rechard.learn.springcloud.annotation;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyFeignClientsRegistrar.class)
public @interface
EnableMyFeignClients {

    Class<?>[] clients() default {};

}
