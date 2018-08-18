package com.bridgelabz.eureka.controller;

import java.io.IOException;
/*******************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose: Controller class for the note
 ******************************************************************************************/
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.eureka.model.Note;
import com.bridgelabz.eureka.model.NoteDTO;
import com.bridgelabz.eureka.service.NoteService;
import com.bridgelabz.eureka.service.NoteServiceImpl;
import com.bridgelabz.eureka.util.Label;
import com.bridgelabz.eureka.util.Response;

@RestController
@RequestMapping(value = "/note")
public class NoteController<UserService> {

	static Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

	@Autowired
	NoteService noteService;

	@Autowired
	FeignClientService service;

	/**
	 * @param note
	 * @param httpServletRequest
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
	public ResponseEntity<Response> createNote(@RequestBody NoteDTO note, HttpServletRequest httpServletRequest)
			throws IOException {
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		String userId = httpServletRequest.getHeader("Authorization");
		noteService.createNote(note, userId);
		return new ResponseEntity<>(new Response("Note Created", HttpStatus.CREATED), HttpStatus.OK);
	}
	/**
	 * @param noteId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletenote/{noteId:.+}", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteNote(@PathVariable String noteId) throws Exception {
		noteService.deleteNote(noteId);
		return new ResponseEntity<>(new Response("Note Deleted", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param note
	 * @param httpServletRequest
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/updatenote/{noteId:.+}", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateNote(@PathVariable String noteId, @RequestBody NoteDTO note,
			HttpServletRequest httpServletRequest) throws IOException {
		String token = httpServletRequest.getHeader("Authorization");
		noteService.updateNote(noteId, note);
		return new ResponseEntity<>(new Response("Note Updated", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/pinnote", method = RequestMethod.PUT)
	public ResponseEntity<Response> pinNote(@RequestParam("noteId") String noteId,
			HttpServletRequest httpServletRequest) {
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		String userId = httpServletRequest.getHeader("Authorization");
		noteService.pinnote(noteId, userId);
		return new ResponseEntity<>(new Response("Note Pinned", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/archivenote", method = RequestMethod.PUT)
	public ResponseEntity<Response> archiveNote(@RequestParam("noteId") String noteId,
			HttpServletRequest httpServletRequest) {
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		String userId = httpServletRequest.getHeader("Authorization");
		noteService.archivenote(noteId, userId);
		return new ResponseEntity<>(new Response("Note Archived", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/displaynotes", method = RequestMethod.GET)
	public List<Note> getAllNotes(HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		logger.info(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.displayAllNotes(userId);
		return list;
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/sortbynoteId", method = RequestMethod.GET)
	public List<Note> sortbynoteId(HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		logger.info(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.sortbynoteId(userId);
		return list;
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/sortbytitle", method = RequestMethod.GET)
	public List<Note> sortbytitle(HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		logger.info(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.sortbynoteId(userId);
		return list;
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/pinnednote", method = RequestMethod.GET)
	public List<Note> pinnedNote(HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		logger.info(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.displayAllpin(userId);
		return list;
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/archive-note", method = RequestMethod.GET)
	public List<Note> archiveNote(HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		logger.info(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.displayAllarchive(userId);
		return list;
	}

	/**
	 * @param httpServletRequest
	 * @param noteId
	 * @param reminderTime
	 * @return
	 * @throws ParseException
	 * @throws UserException
	 */
	@RequestMapping(value = "/setReminder", method = RequestMethod.GET)
	public ResponseEntity<Response> setReminder(HttpServletRequest httpServletRequest, @RequestParam String noteId,
			@RequestParam String reminderTime) throws ParseException, UserException {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		noteService.setReminder(userId, noteId, reminderTime);
		return new ResponseEntity<>(new Response("Response: reminder", HttpStatus.CREATED), HttpStatus.OK);
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/gettrashednotes", method = RequestMethod.GET)
	public List<Note> getAllTrashedNotes(HttpServletRequest httpServletRequest) {
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		String userId = httpServletRequest.getHeader("Authorization");
		List<Note> list = noteService.getAllTrashedNotes(userId);
		return list;
	}

	/**
	 * @param label
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/createlabel", method = RequestMethod.POST)
	public ResponseEntity<Response> createNote(@RequestBody Label label, HttpServletRequest httpServletRequest) {
		// String token = httpServletRequest.getHeader("Authorization");
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		logger.info(userId);
		noteService.createLabel(label, userId);
		return new ResponseEntity<>(new Response("Label Created", HttpStatus.CREATED), HttpStatus.OK);
	}

	/**
	 * @param labelName
	 * @param noteId
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/addlabel", method = RequestMethod.POST)
	public ResponseEntity<Response> addLabel(@RequestParam String labelName, @RequestParam String noteId,
			HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		noteService.addLabel(labelName, noteId);
		return new ResponseEntity<>(new Response("Label  added to the Note", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	/**
	 * @param labelName
	 * @param labelId
	 * @param httpServletRequest
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatelabel", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateLabel(@PathVariable String labelName, @PathVariable String labelId,
			HttpServletRequest httpServletRequest) throws Exception {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		noteService.updateLabel(labelName, labelId, userId);
		return new ResponseEntity<>(new Response("Label Updated", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	/**
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/displaylabels", method = RequestMethod.GET)
	public List<Label> getAllLabels(HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getHeader("Authorization");
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		List<Label> list = noteService.displayAllLabels(userId);
		return list;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteLabel", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deletelabel(@RequestBody String id) throws Exception {
		noteService.deleteLabel(id);
		return new ResponseEntity<>(new Response("Label  Deleted", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param color
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/colornote", method = RequestMethod.PUT)
	public ResponseEntity<Response> colorNote(@RequestParam String noteId, @RequestParam String color,
			HttpServletRequest httpServletRequest) {
		// String userId = (String) httpServletRequest.getAttribute("Authorization");
		String userId = httpServletRequest.getHeader("Authorization");
		noteService.changeColor(noteId, color, userId);
		return new ResponseEntity<>(new Response("Color Changed", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public List<?> getAllUsers() {
		List<?> list = service.getAllUser();
		return list;
	}
}