package com.myself.practice.model;

import java.io.Serializable;

import com.myself.practice.AnnotationValidations.Age;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int studentId;
	@NotBlank(message = "name Must")
	private String studentName;
	@Age(groups= {CreateGroup.class,UpdateGroup.class})//customAnnotation
	private int age;
	@NotNull(message = "mobileNumber Must")
	private long mobileNumber;
	@NotBlank(message = "email Must")
	private String email;
	// Validation Groups :- 
	public interface CreateGroup {}
	public interface UpdateGroup {}

}
