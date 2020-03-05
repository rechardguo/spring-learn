package com.example.demo.spel;

import com.example.demo.bean.Hobby;
import com.example.demo.bean.User;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.List;
import java.util.Map;

public class SpelDemo {
    public static void main(String[] args) {
        /*ExpressionParser parser = new SpelExpressionParser();
        String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
        System.out.println(helloWorld);*/
        ExpressionParser parser = new SpelExpressionParser();
        User user=new User();
        user.setFirstName("rechard");


        Hobby hobby=new Hobby("badminton","001");
        Hobby hobby2=new Hobby("basketball","002");
        Hobby[] hobbies=new Hobby[2];
        hobbies[0]=hobby;
        hobbies[1]=hobby2;
        user.setHobbies(hobbies);
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding()
                .withRootObject(user)
                .build();
        String firstName = parser.parseExpression("hobbies[1].code").getValue(context,String.class);
        System.out.println(firstName);


        EvaluationContext context2 = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        Map hobbyMap = (Map) parser.parseExpression("{'badminton':'001','basketball':'002'}").getValue(context2);
        System.out.println(hobbyMap.get("badminton"));


        //new List<List<Stirng>>
        EvaluationContext context3 = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        List numbers = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(context3);
        System.out.println(numbers.size());



        EvaluationContext context4 = SimpleEvaluationContext.forReadOnlyDataBinding().withRootObject(new Hobby("football","003")).build();
         parser.parseExpression("setName()").setValue(context4,"pingpong");
        System.out.println(((Hobby)context4.getRootObject().getValue()).getName());
    }
}
