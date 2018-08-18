package com.bridgelabz.eureka.service;

import java.io.IOException;
/****************************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose: Note Service implementation class for the login and registration
 *****************************************************************************************************/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.bridgelabz.eureka.model.Note;
import com.bridgelabz.eureka.model.NoteDTO;
import com.bridgelabz.eureka.repository.NoteRepository;
import com.bridgelabz.eureka.util.Description;
import com.bridgelabz.eureka.util.JsoupImpl;
import com.bridgelabz.eureka.util.Label;
import com.bridgelabz.eureka.util.LabelElasticRepository;
import com.bridgelabz.eureka.util.LabelRepository;
import com.bridgelabz.eureka.util.Link;
import com.bridgelabz.eureka.util.NoteElasticRepository;
import com.bridgelabz.eureka.util.NoteExceptionHandler;
import com.bridgelabz.eureka.util.UserExceptionHandler;

@Service
public class NoteServiceImpl<labelRepository> implements NoteService {
	static Logger logger= LoggerFactory.getLogger(NoteServiceImpl.class);

	@Autowired
	NoteElasticRepository noteElasticRepository;

	@Autowired
	LabelElasticRepository labelElasticRepository;

	@Autowired
	NoteRepository noteRepository;

	@Autowired
	LabelRepository labelRepository;

	@Autowired
	ModelMapper modelMapper;

	/**
	 * @param note
	 * @param userId
	 * @throws IOException
	 */
	@Override
	public void createNote(NoteDTO note, String userId) throws IOException {
		if (note.getTitle() == null && note.getDescription() == null)
			throw new NoteExceptionHandler("Note cannot be created with empty title and description");
		Note notes = new Note();
		if (!note.getDescription().equals("")) {
			String noteDescription = note.getDescription();
			notes.setDescription(makeDescription(noteDescription));
		}
		notes.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		notes.setUserId(userId);
		notes.setTitle(note.getTitle());
		notes.setTrash("false");
		notes.setColor("white");
		notes.setPin("false");
		notes.setArchive("false");
		try {
			noteRepository.save(notes);
			noteElasticRepository.save(notes);
			logger.info("Note saved properly");
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NoteExceptionHandler("invalid token");
		}

	}

	/**
	 * @param noteDescription
	 * @return
	 * @throws IOException
	 */
	public static Description makeDescription(String noteDescription) throws IOException {
		Description desc = new Description();
		List<Link> linkList = new ArrayList<>();
		List<String> simpleList = new ArrayList<>();
		String[] descriptionArray = noteDescription.split(" ");
		for (int i = 0; i < descriptionArray.length; i++) {
			if (descriptionArray[i].startsWith("http://") || descriptionArray[i].startsWith("https://")) {
				Link link = new Link();
				link.setLinkTitle(JsoupImpl.getTitle(descriptionArray[i]));
				link.setLinkDomainName(JsoupImpl.getDomain(descriptionArray[i]));
				link.setLinkImage(JsoupImpl.getImage(descriptionArray[i]));
				System.out.println(link);
				linkList.add(link);
			} else if (!descriptionArray[i].equals("")
					&& (!descriptionArray[i].startsWith("http://") || !descriptionArray[i].startsWith("https://"))) {
				simpleList.add(descriptionArray[i]);
			}
		}
		desc.setSimpleDescription(simpleList);
		desc.setLinkDescription(linkList);
		return desc;
	}

	/**
	 * @param noteId
	 */
	@Override
	public void deleteNote(String noteId) {
		Optional<Note> note = noteElasticRepository.findById(noteId);
		if (note.get().getTrash() == "false") {
			note.get().setTrash("true");
			noteRepository.save(note);
			return;
		}
		if (!note.isPresent()) {
			throw new NoteExceptionHandler("User not Found");
		}
		noteRepository.deleteById(noteId);
		noteElasticRepository.deleteById(noteId);
		logger.info("Requested noteId is deleted from the repositories");
	}

	/**
	 * @param noteId
	 * @param note
	 * @throws IOException
	 */
	@Override
	public void updateNote(String noteId, NoteDTO note) throws IOException {
		Optional<Note> note1 = noteElasticRepository.findById(note.getId());
		if (!note1.isPresent()) {
			throw new UserExceptionHandler("User not Found");
		}
		Note notes = note1.get();
		note.setTitle(notes.getTitle());
		// note.setDescription(description);
		if (!note.getDescription().equals(" ")) {
			String noteDescription = note.getDescription();
			notes.setDescription(makeDescription(noteDescription));
		}
		noteRepository.save(notes);
		noteElasticRepository.save(notes);
		logger.info("Note is updated successfully");
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public List<Note> displayAllNotes(String userId) {
		List<Note> noteList = noteElasticRepository.findNotesByUserId(userId);
		System.out.println(noteList);
		return noteList;
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public List<Note> displayAllpin(String userId) {
		List<Note> noteList = noteElasticRepository.findNotesByUserId(userId);
		if (noteList == null) {
			throw new NoteExceptionHandler("note not found");
		}
		List<Note> pinnedlist = new ArrayList<Note>();
	/*	for (Note n : noteList) {
			if (n.getPin() == "true") {
				pinnedlist.add(n);
			}
		}
		return pinnedlist;*/  
		List<Note>  stream= noteList.stream().filter(abc-> abc.getPin()=="true").collect( Collectors.toList());
		return stream;	
}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public List<Note> displayAllarchive(String userId) {
		List<Note> noteList = noteElasticRepository.findNotesByUserId(userId);
		if (noteList == null) {
			throw new NoteExceptionHandler("note not found");
		}
		logger.info("Displaying the all archive notes");
		List<Note> archiveList = new ArrayList<Note>();
		/*for (Note n : noteList) {
			if (n.getArchive() == "true") {
				archiveList.add(n);
			}
		}
		return archiveList;*/
		List<Note>  stream= noteList.stream().filter(abc-> abc.getArchive()=="true").collect( Collectors.toList());
		return stream;	
	}

	/**
	 * @param userId
	 * @param id
	 * @param reminderTime
	 * @throws UserException
	 * @throws ParseException
	 */
	@Override
	public void setReminder(String userId, String id, String reminderTime) throws UserException, ParseException {
		Optional<Note> note = noteElasticRepository.findById(id);
		Timer timer = null;
		if (note.isPresent()) {
			Date reminder = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(reminderTime);
			long timeDifference = reminder.getTime() - new Date().getTime();
			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
				}
			}, timeDifference);
		}
	}

	/**
	 * @param label
	 * @param userId
	 */
	@Override
	public void createLabel(Label label, String userId) {
		if (label.getLabelName() == null)
			throw new NoteExceptionHandler("Label cannot be created with empty title and description");
		try {
			label.setUserId(userId);
			label.setLabelName(label.getLabelName());
			labelRepository.save(label);
			labelElasticRepository.save(label);
			logger.info("Created label is saved into the repository successfully");
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NoteExceptionHandler("Note cannot be created due to database error, please try again later");
		}
	}

	/**
	 * @param labelName
	 * @param noteid
	 */
	@Override
	public void addLabel(String labelName, String noteid) {
		Label label = new Label();
		Optional<Note> note = noteElasticRepository.findById(noteid);
		if (note.get().getLabelName() == null) {
			List<Label> newLabelList = new ArrayList<>();
			note.get().setLabelName(newLabelList);
		}
		Iterable<Label> list = labelElasticRepository.findAll();
		for (Label l : list) {

			if (l.getLabelName().equals(labelName)) {
				Optional<Note> optionalnote = noteElasticRepository.findById(noteid);
				if (!optionalnote.isPresent()) {
					throw new UserExceptionHandler("note not found");
				}
				label.setId(optionalnote.get().getId());
				label.setId(label.getId());
				label.setLabelName(labelName);
				note.get().getLabelName().add(label);
				noteRepository.save(note.get());
				noteElasticRepository.save(note.get());
				logger.info("Label is added to the respected note");
			}
		}
	}

	/**
	 * @param labelName
	 * @param labelid
	 * @param token
	 * @throws Exception
	 */
	
	@Override
	public void updateLabel(String labelName, String labelid, String token) throws Exception {
		Optional<Label> label = labelElasticRepository.findById(labelid);
		if (!label.isPresent()) {
			throw new Exception("User not Found");
		}
		Label label1 = label.get();
		label1.setLabelName(labelName);
		labelRepository.save(label1);
		labelElasticRepository.save(label1);
		logger.info("Label is updated successfully");
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public List<Label> displayAllLabels(String userId) {
		List<Label> labelList = labelElasticRepository.findLabelsByUserId(userId);
		if (labelList != null) {
			List<Label> List = new ArrayList<Label>();
			for (Label n : labelList) {
				if (n.getId().equals(userId)) {
					List.add(n);
				}
			}
		}
		return labelList;
	}

	/**
	 * @param id
	 * @throws Exception
	 */
	@Override
	public void deleteLabel(String id) throws Exception {
		Optional<Label> optionallabel = labelElasticRepository.findById(id);
		if (!optionallabel.isPresent()) {
			throw new Exception("Label not Found");
		}
		labelRepository.deleteById(id);
		labelElasticRepository.deleteById(id);
		logger.info("requested label is deleted from the label list");
	}

	/**
	 * @param noteId
	 * @param color
	 * @param userId
	 */
	@Override
	public void changeColor(String noteId, String color, String userId) {
		try {
			Note note = noteElasticRepository.findByUserId(userId);
			if (note == null)
				throw new NoteExceptionHandler("Note not found");
			note.setColor(color);
			noteRepository.save(note);
			noteElasticRepository.save(note);
		} catch (NullPointerException e) {
			throw new NoteExceptionHandler("Note not found");
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NoteExceptionHandler("Color cannot be changed due to database error, please try again later");
		}
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public List<Note> getAllTrashedNotes(String userId) {
		List<Note> noteList = noteElasticRepository.findNotesByUserId(userId);
		List<Note> TrashedList = new ArrayList<Note>();
		for (Note n : noteList) {
			if (n.getTrash().equals("yes")) {
				TrashedList.add(n);
			}
		}
		return TrashedList;
	}

	/**
	 * @param noteId
	 * @param userId
	 */
	@Override
	public void pinnote(String noteId, String userId) {
		try {
			Note note = noteElasticRepository.findByid(noteId);
			if (note == null)
				throw new NoteExceptionHandler("Note not found");
			if (note.getPin() == " false") {
				note.setPin("true");
				noteRepository.save(note);
				noteElasticRepository.save(note);
				return;
			} else {
				// note.setPin("false");
				noteRepository.save(note);
				noteElasticRepository.save(note);
				return;
			}
		} catch (NullPointerException e) {
			throw new NoteExceptionHandler("Note not found");
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NoteExceptionHandler("Note cannot be pinned due to database error, please try again later");
		}
	}

	/**
	 * @param noteId
	 * @param userId
	 */
	@Override
	public void archivenote(String noteId, String userId) {
		try {
			Note note = noteElasticRepository.findByid(noteId);
			if (note == null)
				throw new NoteExceptionHandler("Note not found");
			if (note.getArchive() == "false") {
				note.setArchive("true");
				noteRepository.save(note);
				noteElasticRepository.save(note);
				return;
			} else {
				// note.setArchive("false");
				noteRepository.save(note);
				noteElasticRepository.save(note);
				return;
			}
		} catch (NullPointerException e) {
			throw new NoteExceptionHandler("Note not found");
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NoteExceptionHandler("Note cannot be archived due to database error, please try again later");
		}
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public List<Note> sortbynoteId(String userId) {
		List<Note> list = noteElasticRepository.findNotesByUserId(userId);
		if (list == null) {
			throw new NoteExceptionHandler("note not found");
		}
		List<Note> sortedlist = list.stream().sorted((o1, o2) -> o1.getId().compareToIgnoreCase(o2.getId()))
				.collect(Collectors.toList());
		return sortedlist;
	}

	/**
	 * @param title
	 * @param userId
	 * @return
	 */
	@Override
	public List<Note> sortbytitle(String title, String userId) {
		List<Note> list = noteElasticRepository.findNotesByUserId(userId);
		if (list == null) {
			throw new NoteExceptionHandler("note not found");
		}
		List<Note> sortedlist = list.stream().sorted((o1, o2) -> o1.getTitle().compareToIgnoreCase(o2.getTitle()))
				.collect(Collectors.toList());
		return sortedlist;
	}
}
