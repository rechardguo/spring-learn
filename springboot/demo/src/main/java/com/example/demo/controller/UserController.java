package com.example.demo.controller;

import com.example.demo.bean.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Properties;
import java.util.Set;

@Controller
public class UserController{

    public static void main(String args[]){
        Properties p=System.getProperties();
        Set set=p.entrySet();
    }

    @Value("#{ systemProperties['user.country'] }")
    private String defautlocale;

    @RequestMapping("/user/locale")
    public String local(){
        return defautlocale;
    }



    @RequestMapping("/user/save")
    public String save(@Valid @ModelAttribute("user") User user){
        return "user_detail";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserValidator());
    }

    static class UserValidator implements org.springframework.validation.Validator{
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        public void validate(Object target, Errors errors) {
            User  user = (User) target;
            if (user.isHasEmail() && StringUtils.isEmpty(user.getMail())) {
                errors.reject("field.email.empty",
                        "the email can not empty");
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required","password can not be empty");
        }
    }


}
