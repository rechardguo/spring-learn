package rechard.learn.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceDSController {

    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping("/services")
    public List getAllServices(){
        List<String>  list = discoveryClient.getServices();
      /* final StringBuffer services=new StringBuffer();
        list.forEach(serviceName->{
            services.append(serviceName);
            services.append(",");
       });*/
        return list;
    }

    @RequestMapping("/services/{instanceName}")
    public String getAllServiceInstance(@PathVariable String instanceName) {
        List<ServiceInstance> list = discoveryClient.getInstances(instanceName);
        final StringBuffer services=new StringBuffer();
        list.forEach(serviceInstance->{
            services.append(serviceInstance.getUri());
            services.append(",");
        });
        return services.toString();
    }
}
