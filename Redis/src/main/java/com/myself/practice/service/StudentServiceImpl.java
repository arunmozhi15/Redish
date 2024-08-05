package com.myself.practice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myself.practice.entity.StudentEntity;
import com.myself.practice.globalexception.CustomExceptionHandler;
import com.myself.practice.model.StudentModel;
import com.myself.practice.repository.StudentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentRepo studentRepo;

	private final ModelMapper modelMapper;
	Date date = new Date();

	@Override
	public StudentModel createStudent(StudentModel studentModel) {
		StudentEntity studentEntity = new StudentEntity();
		BeanUtils.copyProperties(studentModel, studentEntity);

		studentEntity = studentRepo.save(studentEntity);
		if (studentEntity != null) {
			BeanUtils.copyProperties(studentEntity, studentModel);
			return studentModel;
		}
		throw new CustomExceptionHandler("Invalid Data or Missing property ");
	}

	@Override
	public StudentModel viewStudent(int studentId) {
		StudentModel studentModel = new StudentModel();
		StudentEntity studentEntity = new StudentEntity();
		studentEntity = studentRepo.findById(studentId).orElse(null);
		if (studentEntity != null) {
			BeanUtils.copyProperties(studentEntity, studentModel);
			return studentModel;
		}
		throw new CustomExceptionHandler("Invalid Data or Missing property:'StudentId' ");
	}

	@Override
	public boolean deleteStudent(int studentId) {
		if (studentId > 0) {
			//StudentEntity s = studentRepo.findById(studentId).get();
			studentRepo.studentIdDeleteById(studentId);
			return true;
		}
		throw new CustomExceptionHandler("Invalid Data :'StudentId' ");
	}

	@Override
	public StudentModel updateStudent(StudentModel studentModel) {
		StudentEntity studentEntity = studentRepo.findById(studentModel.getStudentId()).get();
		// studentEntity.setModifiedOn((java.sql.Date) new Date());
		BeanUtils.copyProperties(studentModel, studentEntity);
		StudentEntity student = studentRepo.save(studentEntity);
		if (student != null) {
			BeanUtils.copyProperties(studentEntity, studentModel);
			return studentModel;
		}
		throw new CustomExceptionHandler("Invalid Data or Missing property:'StudentId' ");
	}

	@Override
	public List<StudentModel> getAllStudent() {
		List<StudentModel> studentModelList = new ArrayList<>();
		List<StudentEntity> studentEntityList = studentRepo.findAllByIsDeleteFalse();
		if (studentEntityList != null) {
//		StudentModel   studentModel	=modelMapper.map(studentEntityList, StudentModel.class);
//		studentModelList	=studentEntityList.stream().map(this::studentModel).collect(Collectors.toList());
			studentModelList = studentEntityList.stream()
					.map(studentEntity -> modelMapper.map(studentEntity, StudentModel.class))
					.collect(Collectors.toList());
			return studentModelList;
		}
		return null;
	}

	@Override
	public Page<StudentEntity> paginationList(int pageIndex, int pageSize, String sortOrder, String searchText,
			String columnName) {

		Page<StudentModel> pg = null;
		Sort sort;
		if (sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equals("asc")) {
				sort = Sort.by(columnName).ascending();
			} else {
				sort = Sort.by(columnName).descending();

			}
		} else {
			sort = Sort.unsorted();

		}
		PageRequest pgRequest = PageRequest.of(pageIndex, pageSize, sort);
		if (searchText != null && !searchText.isEmpty()) {
			return studentRepo.findAllByIsDeleteFalseAndStudentNameContainingIgnoreCase(searchText, pgRequest);
		} else {
			return studentRepo.findAll(pgRequest);
		}
	}

}
