package fr.epita.quiz.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.dto.ExamDTO;
import fr.epita.quiz.services.dao.ExamDAO;

/**
 * Exam resource
 * Class that handles the operations of the transfer of the exam to the data services and frontend 
 * @author juanc
 *
 */
@Path("/exam")
public class ExamResource {
	
	@Inject
	ExamDAO examDao;
	
	/**
	 * Method that handles the creation of a exam 
	 * @param exam
	 * @return
	 */
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response addExam(@RequestBody ExamDTO exam) {
		
		Exam examEntity = new Exam();
		examEntity.setTitle(exam.getTitle());
	
		examDao.create(examEntity);
		
		if(examEntity.getId() > 0) {
			ExamDTO examDto = new ExamDTO();
			examDto.setId(examEntity.getId());
			examDto.setTitle(examEntity.getTitle());
			
			return Response.ok(examDto).build();
		}
		else {
			return Response.serverError().build();
		}
	}
	
	/**
	 * Method that handles the get of an exam by its id
	 * @param examId
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getExam(@PathParam("id") long examId) {
		
		Exam exam = examDao.getById(examId);
		
		if(exam.getId() > 0) {
			ExamDTO examDto = new ExamDTO();
			examDto.setId(exam.getId());
			examDto.setTitle(exam.getTitle());
			
			
			return Response.ok(examDto).build();
		}
		else {
			
			return Response.serverError().build();
		}
	}
	
	/**
	 * Method that handles the get of all exams
	 * @return
	 */
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getExams() {
		
		//beggining dummy implementation
		List<Exam> exams = examDao.search(new Exam());
		if(exams != null) {
			List<ExamDTO> examsDto = new ArrayList<ExamDTO>();
			for (Exam exam : exams) {
				ExamDTO examDto = new ExamDTO(exam);
				examsDto.add(examDto);
			}
			
			return Response.ok(Arrays.asList(examsDto)).build();
		}
		else {
			return Response.serverError().build();
		}
		
	}
	
	/**
	 * Method that handles the update of all the exams
	 * @param exam
	 * @return
	 */
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response updateExam(@RequestBody ExamDTO exam) {
		
		Exam examEntity = new Exam();
		examEntity.setTitle(exam.getTitle());
		examEntity.setId(exam.getId());
	
		examDao.update(examEntity);
		
		if(examEntity.getId() > 0) {
			ExamDTO examDto = new ExamDTO();
			examDto.setId(examEntity.getId());
			examDto.setTitle(examEntity.getTitle());
			
			return Response.ok(examDto).build();
		}
		else {
			return Response.serverError().build();
		}
	}


}
