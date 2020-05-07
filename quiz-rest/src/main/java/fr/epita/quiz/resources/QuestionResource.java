package fr.epita.quiz.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.dto.MCQChoicesDTO;
import fr.epita.quiz.dto.QuestionDTO;
import fr.epita.quiz.services.business.QuestionDataService;

/**
 * Question resource
 * Class that handles the operations of the transfer of the question to the data services and frontend 
 * @author juanc
 *
 */
@Path("/question")
public class QuestionResource {
	
	@Inject
	QuestionDataService questionDs;
	
	/**
	 * Method that handles the creation of a question
	 * @param questionDto
	 * @return
	 */
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response createQuestion(@RequestBody QuestionDTO questionDto) {
		
		try {
			Exam exam = new Exam();
			exam.setId(questionDto.getExamId());
			
			Question question = new Question();
			question.setTitle(questionDto.getTitle());
			question.setExam(exam);
			
			List<MCQChoice> mcqChoices = new ArrayList<MCQChoice>();
			if(questionDto.getChoices().size() > 0) {
				for (MCQChoicesDTO choice : questionDto.getChoices()) {
					MCQChoice choiceModel = new MCQChoice();
					choiceModel.setChoice(choice.getChoice());
					choiceModel.setValid(choice.getValid());
					mcqChoices.add(choiceModel);
				}
			}
			
			questionDs.createQuestion(question, mcqChoices);
			
			if(question.getId() > 0) {
				return Response.ok().build();
			}
			else {
				return Response.serverError().build();
			}
		}
		catch (Exception e) {
			// TODO Handle things properly
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	}
	
	/**
	 * Method that handles the get of a question by its id
	 * @param questionId
	 * @return
	 */
	@GET
	@Path("/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getQuestionById(@PathParam("id") long questionId) {

		try {
			
			Question question = questionDs.searchQuestionById(questionId);
			
			QuestionDTO questionDto = new QuestionDTO();
			questionDto.setExamId(question.getExam().getId());
			questionDto.setId(question.getId());
			questionDto.setTitle(question.getTitle());
			
			List<MCQChoicesDTO> mcqChoicesDto = new ArrayList<MCQChoicesDTO>();
			if(question.getMcqChoices().size() > 0) {
				for (MCQChoice mcqChoice : question.getMcqChoices()) {
					MCQChoicesDTO mcqChoiceDto = new MCQChoicesDTO();
					mcqChoiceDto.setChoice(mcqChoice.getChoice());
					mcqChoiceDto.setId(mcqChoice.getId());
					mcqChoiceDto.setValid(mcqChoice.getValid());
					mcqChoicesDto.add(mcqChoiceDto);
				}
			}
			
			questionDto.setChoices(mcqChoicesDto);
			
			return Response.ok(questionDto).build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	}
	
	/**
	 * Method that handles the get of all questions
	 * @param examId
	 * @return
	 */
	@GET
	@Path("/exam/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response getQuestions(@PathParam("id") long examId) {
		
		
		try {
			List<Question> questions = questionDs.searchQuestion(examId);
			List<QuestionDTO> questionsDto = new ArrayList<QuestionDTO>();
			
			for (Question question : questions) {
				QuestionDTO questionDto = new QuestionDTO();
				questionDto.setExamId(question.getExam().getId());
				questionDto.setId(question.getId());
				questionDto.setTitle(question.getTitle());
				List<MCQChoicesDTO> mcqChoicesDto = new ArrayList<MCQChoicesDTO>();
				if(question.getMcqChoices().size() > 0) {
					for (MCQChoice mcqChoice : question.getMcqChoices()) {
						MCQChoicesDTO mcqChoiceDto = new MCQChoicesDTO();
						mcqChoiceDto.setChoice(mcqChoice.getChoice());
						mcqChoiceDto.setId(mcqChoice.getId());
						mcqChoiceDto.setValid(mcqChoice.getValid());
						mcqChoiceDto.setQuestionId(question.getId());
						mcqChoicesDto.add(mcqChoiceDto);
					}
					questionDto.setChoices(mcqChoicesDto);
				}
				questionsDto.add(questionDto);
			}
			
			return Response.ok(questionsDto).build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	}
	
	
	/**
	 * Method that handles the update of a question 
	 * @param questionDto
	 * @return
	 */
	@PUT
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response updateQuestion(@RequestBody QuestionDTO questionDto) {
		

		try {
			Exam exam = new Exam();
			exam.setId(questionDto.getExamId());
			
			Question question = new Question();
			question.setId(questionDto.getId());
			question.setTitle(questionDto.getTitle());
			question.setExam(exam);
			
			List<MCQChoice> mcqChoices = new ArrayList<MCQChoice>();
			if(questionDto.getChoices().size() > 0) {
				for (MCQChoicesDTO choice : questionDto.getChoices()) {
					MCQChoice choiceModel = new MCQChoice();
					choiceModel.setId(choice.getId());
					choiceModel.setChoice(choice.getChoice());
					choiceModel.setValid(choice.getValid());
					mcqChoices.add(choiceModel);
				}
			}
			
			questionDs.updateQuestion(question, mcqChoices);
			
			if(question.getId() > 0) {
				return Response.ok().build();
			}
			else {
				return Response.serverError().build();
			}
		}
		catch (Exception e) {
			// TODO Handle things properly
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	
	}
	
	/**
	 * Method that habndles the deleting of a question by its id
	 * @param questionId
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response deleteQuestion(@PathParam("id") long questionId) {
		
		try {
			
			questionDs.deleteQuestion(questionId);
			
			return Response.ok().build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.serverError().build();
	
	}

}
