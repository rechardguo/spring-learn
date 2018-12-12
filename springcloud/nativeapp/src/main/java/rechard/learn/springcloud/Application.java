package rechard.learn.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import rechard.learn.springcloud.bean.User;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource("classpath:myApp.properties")
public class Application{


    @Value("${name:rechard33}")
     private String name;

    @Value("${age:33}")
     private int age;


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args ) {

        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("rechard");
        parentContext.registerBean("parentBean", String.class, "Hello World");
        Environment env = parentContext.getEnvironment();
        MutablePropertySources sources = ((AbstractEnvironment) env).getPropertySources();

        Properties p = new Properties();
        p.setProperty("name", "rechard");
        p.setProperty("id", "1");
        PropertiesPropertySource propertiesPropertySource = new PropertiesPropertySource("myPropertySource", p);
        sources.addFirst(propertiesPropertySource);
        parentContext.refresh();

        AnnotationConfigServletWebServerApplicationContext currentContext =
                (AnnotationConfigServletWebServerApplicationContext) new SpringApplicationBuilder(Application.class)
                        .parent(parentContext).run(args);
        String myBean = (String) currentContext.getBean("myBean");

        boolean isContain = currentContext.getEnvironment().containsProperty("name");
        System.out.println(isContain);

        String nameValue = currentContext.getEnvironment().getProperty("name");
        System.out.println(nameValue);
        System.out.println(myBean);

        Application application = currentContext.getBean(Application.class);
        System.out.println(application.getAge());
        application.users.forEach(user->System.out.println(user));
    }

    @Bean
    @Qualifier("myBean")
    public String myBean(String parentBean){
        return parentBean + " 2018";
    }


    @Autowired(required = false)
    public List<User> users = Collections.emptyList();




    @Bean
    @Order(1)
    User user1(){
        User user = new User(1,"rechard");
        return user;
    }

    @Bean
    @Order(2)
     User user2(){
        User user = new User(2,"rechard2");
        return user;
    }
}
