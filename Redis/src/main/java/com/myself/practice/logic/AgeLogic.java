package com.myself.practice.logic;

import com.myself.practice.AnnotationValidations.Age;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class AgeLogic implements  ConstraintValidator<Age, Integer>{

	@Override
	public boolean isValid(Integer age, ConstraintValidatorContext context) {
		
		return age>=5;
	}



}
