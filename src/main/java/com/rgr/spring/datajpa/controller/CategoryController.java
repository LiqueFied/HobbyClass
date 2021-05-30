package com.rgr.spring.datajpa.controller;

import com.rgr.spring.datajpa.model.Category;
import com.rgr.spring.datajpa.repository.CategoryRepository;
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
public class CategoryController {

	@Autowired
	CategoryRepository repository;

	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			List<Category> categoryList = new ArrayList<Category>();

			if (title == null)
				repository.findAll().forEach(categoryList::add);
			else
				repository.findByTitleContaining(title).forEach(categoryList::add);

			if (categoryList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(categoryList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping("/category/{id}")
	public ResponseEntity<Category> getTutorialById(@PathVariable("id") long id) {
		Optional<Category> tutorialData = repository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/categoryadmin")
	public ResponseEntity<Category> createTutorial(@RequestBody Category hobbyclass) {
		try {
			Category _category = repository
					.save(new Category(hobbyclass.getTitle()));
			return new ResponseEntity<>(_category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/categoryadmin/{id}")
	public ResponseEntity<Category> updateTutorial(@PathVariable("id") long id, @RequestBody Category hobbyclass) {
		Optional<Category> tutorialData = repository.findById(id);

		if (tutorialData.isPresent()) {
			Category _category = tutorialData.get();
			_category.setTitle(hobbyclass.getTitle());
			return new ResponseEntity<>(repository.save(_category), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/categoryadmin/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/categoryadmin")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
