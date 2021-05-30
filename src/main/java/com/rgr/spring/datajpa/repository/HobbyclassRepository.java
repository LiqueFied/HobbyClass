package com.rgr.spring.datajpa.repository;

import com.rgr.spring.datajpa.model.Hobbyclass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HobbyclassRepository extends JpaRepository<Hobbyclass, Long> {
	List<Hobbyclass> findByTitleContaining(String title);
}
