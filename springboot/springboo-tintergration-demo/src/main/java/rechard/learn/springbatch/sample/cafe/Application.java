package rechard.learn.springbatch.sample.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

        Cafe cafe = ctx.getBean(Cafe.class);
        for (int i = 1; i <= 100; i++) {
            Order order = new Order(i);
            order.addItem(DrinkType.LATTE, 2, false);
            order.addItem(DrinkType.MOCHA, 3, true);
            cafe.placeOrder(order);
        }

        System.out.println("Hit 'Enter' to terminate");
        System.in.read();
        ctx.close();
    }

    @MessagingGateway
    public interface Cafe {
        @Gateway(requestChannel = "orders.input")
        void placeOrder(Order order);
    }


   /* @Bean("barista.prepareColdDrink")
    @ServiceActivator(inputChannel = "coldDrinks")
    void coldDrinks() {
    }

    @Bean("barista.prepareHotDrink")
    @ServiceActivator(inputChannel = "hotDrinks")
    void hotDrinks() {
    }*/


}
