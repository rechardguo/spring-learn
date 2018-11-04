package rechard.learn.springboot.springstatemachine.demo.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DemoController {

    @Autowired
    Persist persist;

    @GetMapping("/order/create")
    public void listOrders(@RequestBody Order order){
          persist.create(order);
    }

    @GetMapping("/orders")
    @ResponseBody
    public String listOrders(){
       return  persist.listDbEntries();
    }

    @GetMapping("/order/{id}/{action}")
    @ResponseBody
    public boolean handleOrder(@PathVariable("id") Integer orderId, @PathVariable("action") String event){
       return  persist.change(orderId,event);
    }
}
