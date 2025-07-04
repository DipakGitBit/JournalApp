package com.JournalCompany.jesob.Entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "journalEntry")
@Data
public class JournalEntity {
	
	@Id
	private ObjectId id;
	private String description;
	private String content;
	private LocalDateTime createdDate;
	
	
	

}
