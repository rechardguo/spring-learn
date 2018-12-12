package rechard.learn.springcloud.feign.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rechard.learn.springcloud.annotation.MyFeignClient;

import javax.servlet.http.HttpServletRequest;

@MyFeignClient(value="${remote.service.name}")
@Service
public interface HelloServiceAPI2 {
    @RequestMapping("/hello")
    public String call(@RequestParam(name = "msg", required = false) String msg);

}
