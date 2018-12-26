package rechard.learn.springbatch.bean.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class CardNumberConstraintValidator implements ConstraintValidator<CardNumberValidator,String> {

    private String validPrefix;
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //ConstraintValidatorContextImpl context1 = (ConstraintValidatorContextImpl)context;
       // String validPrefix = (String)context1.getConstraintDescriptor().getAttributes().get("prefix");
        if(value==null)
            return false;

        StringTokenizer token = new StringTokenizer(value,"-");

        if(token.countTokens()!=2)
            return false;

        String cardNumberPrefix = token.nextToken();
        String cardNumber = token.nextToken();

        boolean isNumber = Pattern.matches("/d", cardNumber);

        if(!cardNumberPrefix.equals(validPrefix) && isNumber)
          return false;

        return true;
    }

    public void initialize(CardNumberValidator constraintAnnotation) {
        this.validPrefix = constraintAnnotation.prefix();
    }
}
