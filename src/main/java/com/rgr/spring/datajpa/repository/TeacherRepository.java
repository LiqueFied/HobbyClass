package com.rgr.spring.datajpa.repository;

import com.rgr.spring.datajpa.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	List<Teacher> findByTitleContaining(String title);
}
