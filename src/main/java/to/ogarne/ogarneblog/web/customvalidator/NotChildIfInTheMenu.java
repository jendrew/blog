package to.ogarne.ogarneblog.web.customvalidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotChildIfInTheMenuValidator.class})
public @interface NotChildIfInTheMenu {

    String message() default "This field should be null";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
