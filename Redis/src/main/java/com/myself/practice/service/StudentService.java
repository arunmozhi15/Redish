package com.myself.practice.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;

import com.myself.practice.entity.StudentEntity;
import com.myself.practice.model.StudentModel;

public interface StudentService {
	
	
	public StudentModel createStudent(StudentModel studentModel);
@Cacheable(value ="RestApi",key = "#studentId")
	StudentModel viewStudent(int studentId);
@CacheEvict(value ="RestApi",key = "#studentId")
	boolean deleteStudent(int studentId);
@CachePut(value ="RestApi",key = "#studentModel.studentId")
	StudentModel updateStudent(StudentModel studentModel);
@Cacheable(value = "RestApi")
	List<StudentModel> getAllStudent();
@Cacheable(value = "RestApi",key = "{#pageIndex,#pageSize,#sortOrder,#searchText,#columnName}")
	Page<StudentEntity> paginationList(int pageIndex, int pageSize, String sortOrder, String searchText,
			String columnName);
}
