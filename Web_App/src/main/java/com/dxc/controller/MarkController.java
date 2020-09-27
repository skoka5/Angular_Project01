package com.dxc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.model.Mark;
import com.dxc.repository.MarkRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/")
@RestController
public class MarkController {
	@Autowired
	MarkRepository markRepository;
	
	@RequestMapping("showMarkForm")
	public String adMark1() {
		return "showMarkForm";
	}
	
	@RequestMapping(value="addMark", method= RequestMethod.POST)
	public String addmark(Mark mark) {
		markRepository.save(mark);
		return "home";
	}
	
	@GetMapping(path="marks/{id}")
	public Mark getMark(@PathVariable("id") int id) {
		Mark mark = markRepository.findById(id).orElse(new Mark());
			return mark;
	}
	
	@GetMapping("marks")
	public List<Mark> getMark() {
		List<Mark> mark = (List<Mark>) markRepository.findAll();
		return mark;
	}

	@PostMapping("marks")
	public Mark save(@RequestBody Mark mark) {
		return markRepository.save(mark);
	}

	
	@DeleteMapping(path="marks/{id}")
	public void deleteMark(@PathVariable("id") int id) {
		markRepository.deleteById(id);
	}
	
	@PutMapping("/marks/{id}")
	public ResponseEntity<Object> updateMark(@RequestBody Mark mark, @PathVariable int id) {
        Optional<Mark> mark1Optional = markRepository.findById(id);
        if (!mark1Optional.isPresent())
			return ResponseEntity.notFound().build();
            mark.setId(id);
            markRepository.save(mark);
            return ResponseEntity.noContent().build();
	}


}
