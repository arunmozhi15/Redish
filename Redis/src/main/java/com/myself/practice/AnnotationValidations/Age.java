package com.myself.practice.AnnotationValidations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.myself.practice.logic.AgeLogic;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD,ElementType.PARAMETER}) //level
@Retention(RetentionPolicy.RUNTIME) //time
@Documented   //represent document (Some comment to view doc)
@Constraint(validatedBy =AgeLogic.class) //logic canitains in the class
public @interface Age {
	public String message() default "Age must be greater than 10";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};

}
