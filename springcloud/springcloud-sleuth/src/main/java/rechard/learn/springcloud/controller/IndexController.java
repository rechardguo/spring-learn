package rechard.learn.springcloud.controller;

import org.bouncycastle.asn1.x509.sigi.PersonalData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import rechard.learn.springcloud.SpringCloudSleuthApplication;
import rechard.learn.springcloud.bean.Person;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;

@RestController
public class IndexController{
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String hi(HttpServletRequest request){
        logger.info("request uri:{}",request.getRequestURL());
        return "hello,world!";
    }

    @RequestMapping("/to/zuul/person-client/person/list")
    public Collection<Person> toZullPersonList(HttpServletRequest request){
        logger.info("IndexController#toZullPersonList");
        String url = "http://spring-cloud-zuul/person-client/person/list";
        return restTemplate.postForObject(url,request,Collection.class);
    }

    @Autowired
    DiscoveryClient discoveryClient;


    @RequestMapping("/service/list")
    public List serviceList(){
        return discoveryClient.getServices();
    }


   /* @RequestMapping("/to/zuul/person-client/person/save")
    public Collection<Person> personList(HttpServletRequest request,@RequestBody Person person){
        logger.info("request uri:{}",request.getRequestURL());

        String url = "http://spring-cloud-zuul/person-client/person/save";
        try {
            URI uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //URI url, HttpMethod method, @Nullable RequestCallback requestCallback,
        //@Nullable ResponseExtractor<T> responseExtractor
        restTemplate.execute()
    }*/
}
