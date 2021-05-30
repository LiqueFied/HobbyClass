package com.rgr.spring.datajpa.controller;

import com.rgr.spring.datajpa.model.Teacher;
import com.rgr.spring.datajpa.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/api")
public class TeacherController {
	
	@Autowired
	TeacherRepository repository;

	@GetMapping("/teacher")
	public ResponseEntity<List<Teacher>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Teacher> teacherList = new ArrayList<Teacher>();

			if (title == null)
				repository.findAll().forEach(teacherList::add);
			else
				repository.findByTitleContaining(title).forEach(teacherList::add);

			if (teacherList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(teacherList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/teacher/{id}")
	public ResponseEntity<Teacher> getTutorialById(@PathVariable("id") long id) {
		Optional<Teacher> tutorialData = repository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/teacheradmin")
	public ResponseEntity<Teacher> createTutorial(@RequestBody Teacher hobbyclass) {
		try {
			Teacher _teacher = repository
					.save(new Teacher(hobbyclass.getTitle()));
			return new ResponseEntity<>(_teacher, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/teacheradmin/{id}")
	public ResponseEntity<Teacher> updateTutorial(@PathVariable("id") long id, @RequestBody Teacher hobbyclass) {
		Optional<Teacher> tutorialData = repository.findById(id);

		if (tutorialData.isPresent()) {
			Teacher _teacher = tutorialData.get();
			_teacher.setTitle(hobbyclass.getTitle());
			return new ResponseEntity<>(repository.save(_teacher), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/teacheradmin/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/teacheradmin")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
