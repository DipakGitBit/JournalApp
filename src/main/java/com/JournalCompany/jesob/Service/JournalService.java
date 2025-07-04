package com.JournalCompany.jesob.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.JournalCompany.jesob.Entity.JournalEntity;
import com.JournalCompany.jesob.Repository.JournalInterface;

@Service
public class JournalService {

	@Autowired
	private JournalInterface jRepository;

	public List<JournalEntity> showRepository() {

		return jRepository.findAll();

	}

	public void saveRepository(JournalEntity jEntity) {

		jRepository.save(jEntity);

	}

	public Optional<JournalEntity> showSingleRepository(ObjectId id) {

		return jRepository.findById(id);

	}

	public JournalEntity updateRepository(@PathVariable ObjectId objectId, @RequestBody JournalEntity jEntityNew) {

		JournalEntity jEntityOLD = jRepository.findById(objectId).orElse(null);

		if (jEntityOLD != null) {
			if (jEntityNew.getContent() != null && !jEntityNew.getContent().equals("")) {
				jEntityOLD.setContent(jEntityNew.getContent());
			}

			if (jEntityNew.getDescription() != null && !jEntityNew.getDescription().equals("")) {
				jEntityOLD.setDescription(jEntityNew.getDescription());
			}

			jEntityOLD.setCreatedDate(LocalDateTime.now());
			jRepository.save(jEntityOLD);
		}

		return jEntityOLD;

	}

	public void deleteRepository(@PathVariable ObjectId objectId) {

		jRepository.deleteById(objectId);

	}

}
