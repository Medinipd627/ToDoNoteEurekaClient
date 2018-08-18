package com.bridgelabz.eureka.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.omg.CORBA.UserException;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import com.bridgelabz.eureka.model.Note;
import com.bridgelabz.eureka.model.NoteDTO;
import com.bridgelabz.eureka.util.Label;

/*************************************************************************************************
 * Created By:Medini P.D 
 * Date:- 16/07/2018 
 * Purpose: Note service class for the login and registration
 **************************************************************************************************/

@RibbonClient(name="forex-service")
public interface NoteService {

	public void deleteNote(String noteId) throws Exception;

	public void setReminder(String token, String id, String reminderTime) throws UserException, ParseException;

	public void deleteLabel(String Id) throws Exception;

	public void updateLabel(String labelName, String id, String token) throws Exception;

	public void addLabel(String labelName, String noteid);

	public List<Label> displayAllLabels(String userId);

	public void createLabel(Label label, String userId);

	public void changeColor(String noteId, String color, String userId);

	public List<Note> getAllTrashedNotes(String userId);

	public List<Note> displayAllNotes(String userId);

	public void createNote(NoteDTO note, String userId) throws IOException;

	public void pinnote(String noteId, String userId);

	public void archivenote(String noteId, String userId);

	public List<Note> displayAllarchive(String userId);

	public List<Note> displayAllpin(String userId);

	public List<Note> sortbynoteId(String userId);

	public List<Note> sortbytitle(String title, String userId);
	
	void updateNote(String noteId, NoteDTO note) throws IOException;
}