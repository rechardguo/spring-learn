package rechard.learn.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import rechard.learn.springcloud.annotation.EnableMyFeignClients;
import rechard.learn.springcloud.feign.clients.HelloServiceAPI;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableCircuitBreaker
@EnableAspectJAutoProxy
@EnableFeignClients(clients={HelloServiceAPI.class})
//@EnableMyFeignClients(clients = {HelloServiceAPI.class})
public class LoadBalanceClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(LoadBalanceClientApplication.class)
		      //.application().setApplicationContextClass(WebApplicationType.SERVLET)
				.run(args);
	}


	@Bean
	ClientHttpRequestInterceptor interceptor(){
		return new CustomClientHttpRequestInterceptor();
	}


	@Bean
    @LoadBalanced
    RestTemplate lbRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

	@Bean
	@Autowired
	RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getInterceptors().add(0,interceptor);
		return restTemplate;
	}


	static class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

        @Autowired
        DiscoveryClient discoveryClient;

        private volatile Map<String,List<ServiceInstance>>serviceInstanceCache = new ConcurrentHashMap<>();

        @Scheduled(fixedDelay=2000)
        private void updateServiceInstanceCache(){
            discoveryClient.getServices().forEach(serviceName->{
                serviceInstanceCache.put(serviceName,discoveryClient.getInstances(serviceName));
            });
        }

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body,
											ClientHttpRequestExecution execution) throws IOException {
            URI uri = request.getURI();
            String[] uriStrs = uri.toString().substring(1).split("/");
            String serviceName =uriStrs[0];
            String serviceMethod =uriStrs[1];
            List<ServiceInstance> serviceInstanceList = serviceInstanceCache.get(serviceName);
            int ServiceInstanceSize = serviceInstanceList.size();
            Random random = new Random();
            int randomIndex = random.nextInt(ServiceInstanceSize);
            ServiceInstance serviceInstance=serviceInstanceList.get(randomIndex);
            try {
                uri = new URI(serviceInstance.getUri().toString()+"/"+serviceMethod);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            System.out.println("call uri:"+uri);
            HttpRequest newRequest =new CustomClientHttpRequest(uri,request.getMethodValue(),request.getHeaders());
            return execution.execute(newRequest,body);
		}
	}

   static  class CustomClientHttpRequest implements HttpRequest{
        URI uri;
	    String methodValue;
        HttpHeaders headers;
        public CustomClientHttpRequest(URI uri, String methodValue, HttpHeaders headers) {
            this.uri = uri;
            this.methodValue = methodValue;
            this.headers = headers;
        }

        @Override
        public String getMethodValue() {
            return this.methodValue;
        }

        @Override
        public URI getURI() {
            return this.uri;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.headers;
        }
    }


}
