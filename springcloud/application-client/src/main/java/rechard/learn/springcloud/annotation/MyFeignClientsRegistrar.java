package rechard.learn.springcloud.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

public class MyFeignClientsRegistrar  implements ImportBeanDefinitionRegistrar,ApplicationContextAware {
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata,
                                        BeanDefinitionRegistry registry) {

        Map<String, Object> attrs = metadata
                .getAnnotationAttributes(EnableMyFeignClients.class.getName(), true);

        final Class<?>[] clients = attrs == null ? null
                : (Class<?>[]) attrs.get("clients");

        Stream.of(clients).filter(aClass -> {
           return aClass.isInterface() && aClass.isAnnotation() && aClass==MyFeignClient.class;
        }).distinct().forEach(aClass->{
            //jdk 动态代理
           Object proxy =  Proxy.newProxyInstance(aClass.getClassLoader(),aClass.getInterfaces(),new InvocationHandler(){
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    RestTemplate restTemplate =  applicationContext.getBean(RestTemplate.class);
                /*    @MyFeignClient(value="${remote.service.name}")
                    public interface HelloServiceAPI2 {
                        @RequestMapping("/hello")
                        public String hello(@RequestParam(name = "msg", required = false) String msg);*/
                   MyFeignClient myFeignClient = proxy.getClass().getAnnotation(MyFeignClient.class);
                   String remoteServiceName = myFeignClient.value();
                   String [] request =  method.getAnnotation(RequestMapping.class).value();
                    String path = request[0];
                    String result = restTemplate.getForObject("http://"+remoteServiceName+"/"+path,String.class,args);
                    return result;
                }
            });
            //注册到register里
            ((DefaultListableBeanFactory)registry).registerSingleton("myFeignClient"+aClass.getSimpleName(),proxy);
        });
    }
}
