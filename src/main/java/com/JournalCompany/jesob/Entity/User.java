package com.JournalCompany.jesob.Entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Builder;
import lombok.Data;


@Document(collection = "users")
@Data
@Builder
public class User {
	
	@Id
	private ObjectId id;
	@NonNull
	private String userName;
	@NonNull
	private String Password;
	
	
	
	@DBRef //reference to JournalEntity class
	private List<JournalEntity> journalEntities=new ArrayList<>();
	
	private List<String> roles;

}
