package com.rgr.spring.datajpa.controller;

import com.rgr.spring.datajpa.model.Hobbyclass;
import com.rgr.spring.datajpa.repository.HobbyclassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/api")
public class HobbyclassController {

	@Autowired
	HobbyclassRepository tutorialRepository;

	@PersistenceUnit
	private EntityManagerFactory faq;

	@GetMapping("/hobbyclass")
	public ResponseEntity<List<Hobbyclass>> getAllTutorials(@RequestParam(required = false) String title) {
		try {

			EntityManager fa = faq.createEntityManager();
			List<Hobbyclass> hobbyclasss = fa
					.createQuery("SELECT h.id, h.title, c.title, t.title FROM Hobbyclass h, Category c, Teacher t WHERE h.id_teacher =t.id and h.id_category =c.id")
					.getResultList();


//			List<Hobbyclass> hobbyclasss = new ArrayList<Hobbyclass>();
//
//			if (title == null)
//				tutorialRepository.findAll().forEach(hobbyclasss::add);
//			else
//				tutorialRepository.findByTitleContaining(title).forEach(hobbyclasss::add);
//
//			if (hobbyclasss.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}

			return new ResponseEntity<>(hobbyclasss, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/hobbyclass/{id}")
	public ResponseEntity<Hobbyclass> getTutorialById(@PathVariable("id") long id) {
		Optional<Hobbyclass> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/hobbyclassadmin")
	public ResponseEntity<Hobbyclass> createTutorial(@RequestBody Hobbyclass hobbyclass) {
		try {
			Hobbyclass _hobbyclass = tutorialRepository
					.save(new Hobbyclass(hobbyclass.getTitle(), hobbyclass.getId_teacher(), hobbyclass.getId_category()));
			return new ResponseEntity<>(_hobbyclass, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/hobbyclassadmin/{id}")
	public ResponseEntity<Hobbyclass> updateTutorial(@PathVariable("id") long id, @RequestBody Hobbyclass hobbyclass) {
		Optional<Hobbyclass> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Hobbyclass _hobbyclass = tutorialData.get();
			_hobbyclass.setTitle(hobbyclass.getTitle());
			return new ResponseEntity<>(tutorialRepository.save(_hobbyclass), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/hobbyclassadmin/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/hobbyclassadmin")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
