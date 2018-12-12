package rechard.learn.springcloud.controller;

import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rechard.learn.springcloud.feign.clients.HelloServiceAPI;
import rechard.learn.springcloud.feign.clients.HelloServiceAPI2;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class RemoteCallController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    RestTemplate lbRestTemplate;

   // @Autowired
    //HelloServiceAPI2 helloServiceAPI2;

    @Autowired
    HelloServiceAPI helloServiceAPI;

    @Value("${remote.service.name:application-service}")
    String remoteServiceName;

    private volatile List<ServiceInstance> serviceInstanceCache = new ArrayList();

    @Scheduled(fixedDelay=2000)
    void updateServiceInstanceCache(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(remoteServiceName);
        List oldServiceInstanceCache = this.serviceInstanceCache;
        this.serviceInstanceCache = serviceInstances;
        oldServiceInstanceCache.clear();
    }

    @RequestMapping("/call")
    public String call(){
        int index = serviceInstanceCache.size();
        Random randomIndex = new Random();
        ServiceInstance serviceInstance = serviceInstanceCache.get(randomIndex.nextInt(index));
        URI uri = null;
        try {
            uri = new URI(serviceInstance.getUri().toString()+"/hello");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String result = restTemplate.getForObject(uri,String.class);
        return result;
    }


    @RequestMapping("/loadbalance/ribbon/{service-name}/call")
    public String lbRibboncall( @PathVariable("service-name") String ServiceName,HttpServletRequest request){
        String path = "http://"+ServiceName+"/hello?"+ (request.getQueryString()!=null?"?"+request.getQueryString():"");
        String result = lbRestTemplate.getForObject(path,String.class, request.getParameterMap());
        return result;
    }

    //之前的实现
    @RequestMapping("/{service-name}/call")
    public String call2( @PathVariable("service-name") String ServiceName,HttpServletRequest request){
        String path = "/"+ServiceName+"/hello"+ (request.getQueryString()!=null?"?"+request.getQueryString():"");
        String result = restTemplate.getForObject(path,String.class, request.getParameterMap());
        return result;
    }


    //整合feign后的实现
    @RequestMapping("/feign/call")
    public String feignCall(@RequestParam(name = "msg", required = false) String msg){
        return helloServiceAPI.hello(msg);
    }

    //自己实现的feign后的实现
   // @RequestMapping("/myfeign/call")
    //public String myfeignCall(@RequestParam(name = "msg", required = false) String msg){
     //   return helloServiceAPI2.call(msg);
   // }

}
