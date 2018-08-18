package com.bridgelabz.eureka.util;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.eureka.model.Note;


/*********************************************************************************
 * Created By:Medini P.D Date:- 03/07/2018 Purpose:NoteElasticRepository class
 * which extends the ElasticSearchRepository
 ************************************************************************************/

public interface NoteElasticRepository extends ElasticsearchRepository<Note, String> {

	List<Note> findNotesByUserId(String userId);
	Note findByUserId(String userId);
	Note findByid(String noteId);

}
