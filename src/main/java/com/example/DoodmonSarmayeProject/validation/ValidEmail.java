package com.example.DoodmonSarmayeProject.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidEmail {
    String message() default "ایمیل نامعتبر است";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
