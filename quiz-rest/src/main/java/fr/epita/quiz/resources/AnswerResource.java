package fr.epita.quiz.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.MCQAnswer;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.dto.AnswerDTO;
import fr.epita.quiz.dto.MCQChoicesDTO;
import fr.epita.quiz.dto.QuestionDTO;
import fr.epita.quiz.services.business.ExamDataService;
import fr.epita.quiz.services.business.QuestionDataService;

/**
 * Answer resource
 * Class that handles the operations of the transfer of the answer to the data services and frontend 
 * @author juanc
 *
 */
@Path("/answer")
public class AnswerResource {

	@Inject
	ExamDataService examDs;

	/**
	 * Method that handle the creation of answers
	 * @param studentAnswer
	 * @return
	 */
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response createAnswers(@RequestBody AnswerDTO studentAnswer) {
		
		try {
			Exam exam = new Exam();
			exam.setTitle(studentAnswer.getExam().getTitle());
			exam.setId(studentAnswer.getExam().getId());
			
			User user = new User();
			user.setId(studentAnswer.getStudent().getId());
			
			List<MCQAnswer> mcqAnswers = new ArrayList<MCQAnswer>();
			for (MCQChoicesDTO choice : studentAnswer.getChoices()) {
				MCQChoice choiceModel = new MCQChoice();
				choiceModel.setChoice(choice.getChoice());
				choiceModel.setValid(choice.getValid());
				choiceModel.setId(choice.getId());
				MCQAnswer mcqAnswer= new MCQAnswer(user, choiceModel, exam);
				mcqAnswers.add(mcqAnswer);
			}
			
			examDs.answersExam(mcqAnswers);

			return Response.ok().build();
		}
		catch (Exception e) {
			// TODO Handle things properly
			e.printStackTrace();
			return Response.serverError().build();
		}
		
		
		
	}
	
	/**
	 * Method that handle the get of answers by the exam and user id
	 * @param examId
	 * @param userId
	 * @return
	 */
	@GET
	@Path("/{id}/{userId}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getAnswersByExam(@PathParam("id") long examId, @PathParam("userId") String userId) {

		try {
			
			
			List<MCQAnswer> answers = examDs.getAnswers(examId, userId);
			
			List<MCQChoicesDTO> answersDto = new ArrayList<MCQChoicesDTO>();
			
			if(answers.size() > 0) {
				
				for (MCQAnswer mcqAnswer : answers) {
					MCQChoicesDTO mcqAnswerDto = new MCQChoicesDTO();
					mcqAnswerDto.setChoice(mcqAnswer.getMcqChoice().getChoice());
					mcqAnswerDto.setId(mcqAnswer.getMcqChoice().getId());
					mcqAnswerDto.setValid(mcqAnswer.getMcqChoice().getValid());
					answersDto.add(mcqAnswerDto);
				}
				
			}
			
			
			return Response.ok(answersDto).build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	}
	
}
