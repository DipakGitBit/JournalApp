package com.JournalCompany.jesob.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.JournalCompany.jesob.Entity.User;


@Repository
public interface UserInterface extends MongoRepository<User, ObjectId> {
	User findByUserName(String username);
	
	void deleteByUserName(String username);
}
