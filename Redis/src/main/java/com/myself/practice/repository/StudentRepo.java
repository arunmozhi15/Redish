package com.myself.practice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.myself.practice.entity.StudentEntity;

import jakarta.transaction.Transactional;
@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {
	@Transactional
	@Modifying
    @Query("update StudentEntity set isDelete=true where studentId =:studentId")
	int studentIdDeleteById(int studentId);
	List<StudentEntity> findAllByIsDeleteFalse();
	Page<StudentEntity> findAllByIsDelete(boolean b, PageRequest pageable);
	
	Page<StudentEntity>findAllByIsDeleteFalseAndStudentNameContainingIgnoreCase(String searchText,PageRequest pageable);


}
