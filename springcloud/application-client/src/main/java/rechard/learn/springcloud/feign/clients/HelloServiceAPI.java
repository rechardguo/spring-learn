package rechard.learn.springcloud.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


//远程的服务名称
@FeignClient(name="${remote.service.name}")
@Service
public interface HelloServiceAPI {
    //远程的服务的方法
    //URL  http://${remote.service.name}/call
    @RequestMapping("/hello")
    public String hello( @RequestParam(name="msg",required = false)  String msg);

}
