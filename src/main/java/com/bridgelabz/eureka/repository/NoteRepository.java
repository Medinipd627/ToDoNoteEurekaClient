package com.bridgelabz.eureka.repository;

/**********************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose: Note repository class for the login and registration
 ***********************************************************************************************/
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.eureka.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
	// @Query("{description:?0 }")
	// public List<Note> findNotesBydescription(String description);
	// public List<Note> findNotesByUserId(String userId);

	public Note findByid(String userId);

	public Note findByUserId(String userId);

	public void save(String note);

	public void save(Optional<Note> note);

}
