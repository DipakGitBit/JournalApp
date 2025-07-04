package com.JournalCompany.jesob.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JournalCompany.jesob.Entity.JournalEntity;
import com.JournalCompany.jesob.Entity.User;
import com.JournalCompany.jesob.Service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalController {

	@Autowired
	private com.JournalCompany.jesob.Service.JournalService jService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<JournalEntity>> getJournal() {
		return new ResponseEntity<>(jService.showRepository(), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<JournalEntity> postJournal(@RequestBody JournalEntity jEntity) {
		jEntity.setCreatedDate(LocalDateTime.now());
		jService.saveRepository(jEntity);
		return new ResponseEntity<>(HttpStatus.CREATED);

	}

	@GetMapping("id/{myid}")
	public ResponseEntity<JournalEntity> getJournalByID(@PathVariable ObjectId myid) {
		Optional<JournalEntity> jEntity = jService.showSingleRepository(myid);
		if (jEntity.isPresent()) {
			return new ResponseEntity<>(jEntity.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@PutMapping("id/{myid}")
	public ResponseEntity<JournalEntity> updateJournal(@PathVariable ObjectId myid,
			@RequestBody JournalEntity jEntityNew) {

		Optional<JournalEntity> jEntity = Optional.of(jService.updateRepository(myid, jEntityNew));
		if (jEntity.isPresent()) {
			return new ResponseEntity<>(jEntity.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("id/{myid}")
	public ResponseEntity<JournalEntity> deleteJournal(@PathVariable ObjectId myid) {
		Optional<JournalEntity> jEntity = jService.showSingleRepository(myid);
		if (jEntity.isPresent()) {
			jService.deleteRepository(myid);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	// /journal/userName

	@GetMapping("{userName}")
	public ResponseEntity<List<JournalEntity>> getJournalDetailsByUser(@PathVariable String userName) {
		User userGot = userService.findByUserName(userName);
		List<JournalEntity> listOfJournalEntitiesFromUser = userGot.getJournalEntities();
		if (listOfJournalEntitiesFromUser != null) {
			return new ResponseEntity<>(listOfJournalEntitiesFromUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@Transactional
	@PostMapping("{userName}")
	public ResponseEntity<JournalEntity> postJournalDetailsforUser(@RequestBody JournalEntity jEntity,
			@PathVariable String userName) {
		User userGot = userService.findByUserName(userName);
		// System.out.println(userGot);

		if (userGot != null) {
			jEntity.setCreatedDate(LocalDateTime.now());
			jService.saveRepository(jEntity);
			userGot.getJournalEntities().add(jEntity);
			userService.saveUser(userGot);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		// System.out.println(userGot);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	@Transactional
	@DeleteMapping("user/{userName}/journalID/{journalID}")
	public ResponseEntity<JournalEntity> deleteJournalfromUser(@PathVariable ObjectId journalID,
			@PathVariable String userName) {
		Optional<JournalEntity> jEntity = jService.showSingleRepository(journalID);
		if (jEntity.isPresent()) {
			jService.deleteRepository(journalID);

			User userGot = userService.findByUserName(userName);
			userGot.getJournalEntities().removeIf(x -> x.getId().equals(journalID));
			userService.saveUser(userGot);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Transactional
	@PutMapping("user/{userName}/journalID/{journalID}")
	public ResponseEntity<JournalEntity> updateJournalDetailsforUser(@RequestBody JournalEntity jEntity,
			@PathVariable String userName, @PathVariable ObjectId journalID) {
		User userGot = userService.findByUserName(userName);
		System.out.println("userGot : " + userGot);

		if (userGot != null) {

			List<JournalEntity> foundJournal = userGot.getJournalEntities().stream()
					.filter(x -> x.getId().equals(journalID)).collect(Collectors.toList());
			System.out.println("foundJournal : " + foundJournal);

			if (!foundJournal.isEmpty()) {
				JournalEntity oldEntity = jService.showSingleRepository(journalID).get();
				oldEntity.setContent(jEntity.getContent());
				oldEntity.setDescription(jEntity.getDescription());
				jService.saveRepository(oldEntity);

				return new ResponseEntity<>(HttpStatus.ACCEPTED);

			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		// System.out.println(userGot);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
