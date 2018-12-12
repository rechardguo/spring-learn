package rechard.learn.springbatch.bean.validation.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = CardNumberConstraintValidator.class)
public @interface CardNumberValidator {

    String message() default "{card.number.validation.constraints.invalid.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String prefix();

}
