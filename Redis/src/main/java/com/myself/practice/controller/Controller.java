package com.myself.practice.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myself.practice.model.StudentModel;
import com.myself.practice.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("Student")
@RequiredArgsConstructor
public class Controller {

	private final StudentService studentService;

	@PostMapping("/create-student")
	public ResponseEntity<?> createStudent( @Valid @RequestBody StudentModel studentModel,
		BindingResult result) {
			if (result.hasErrors()) {
				return (ResponseEntity<?>) ResponseEntity.status((HttpStatusCode) result);
			}
		return ResponseEntity.ok(studentService.createStudent(studentModel));
	}

	@GetMapping("/get-allStuent")
	public ResponseEntity<?> getAllStudent() {
		return ResponseEntity.ok(studentService.getAllStudent());
	}

	@GetMapping("/get-pagination-list")
	public ResponseEntity<?> getPaginationList(@RequestParam(value = "pageIndex", required = true) int pageIndex,
			@RequestParam(value = "pageSize", required = true) int pageSize,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "searchText", required = false) String searchText,
			@RequestParam(value = "columnName", required = false) String columnName) {
		return ResponseEntity.ok(studentService.paginationList(pageIndex, pageSize, sortOrder, searchText, columnName));
	}

	@GetMapping("/view-student/{studentId}")
	public ResponseEntity<?> viewStudent(@PathVariable("studentId") int studentId) {
		return ResponseEntity.ok(studentService.viewStudent(studentId));
	}

	@PutMapping("/update-student")
	public ResponseEntity<?> updateStudent(@RequestBody StudentModel studentModel) {
		return ResponseEntity.ok(studentService.updateStudent(studentModel));
	}

	@DeleteMapping("/delete-student/{studentId}")
	public ResponseEntity<?> deleteStudent(@PathVariable("studentId") int studentId) {
		return ResponseEntity.ok(studentService.deleteStudent(studentId));
	}

}
