package fr.epita.quiz.test.integration;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.business.QuestionDataService;
import fr.epita.quiz.services.dao.ExamDAO;
import fr.epita.quiz.services.dao.MCQChoiceDAO;
import fr.epita.quiz.services.dao.QuestionDAO;

/**
 * Question data service test
 * Class that handles the test of all methods in question data service class
 * @author juanc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestQuestionDataService {
	
	@Inject
	ExamDAO examDao;
	
	@Inject
	QuestionDAO questionDao;
	
	@Inject
	MCQChoiceDAO mcqChoiceDao;
	
	@Inject 
	QuestionDataService questionDs;
	
	
	/**
	 * Method that handles the creation of a question
	 * @throws Exception
	 */
	@Test
	public void testCreateQuestion() throws Exception {
	
		//given
		Exam exam = new Exam();
		exam.setTitle("Test");
		examDao.create(exam);
		
		Question question = new Question();
		question.setTitle("What is java");
		question.setExam(exam);
		
		List<MCQChoice> mcqChoices = new ArrayList<MCQChoice>();
		MCQChoice choice = new MCQChoice();
		choice.setChoice("Language");
		choice.setValid(true);
		mcqChoices.add(choice);
		MCQChoice choice1 = new MCQChoice();
		choice1.setChoice("Drink");
		choice1.setValid(false);
		mcqChoices.add(choice1);
		
		//When
		questionDs.createQuestion(question, mcqChoices);
		
		
		//Then
		Question fetchedQuestion = questionDao.getById(question.getId());
		
		Assert.assertEquals(fetchedQuestion.getTitle(), question.getTitle()); 
		
		
	}
	
	/**
	 * Method that handles the test of updating a question
	 * @throws Exception
	 */
	@Test
	public void TestUpdateQuestion() throws Exception {
		
		//given
		Exam exam = new Exam();
		exam.setTitle("Test");
		examDao.create(exam);
		
		Question question = new Question();
		question.setTitle("What is java");
		question.setExam(exam);
		questionDao.create(question);
		
		List<MCQChoice> mcqChoices = new ArrayList<MCQChoice>();
		MCQChoice choice = new MCQChoice();
		choice.setChoice("Language");
		choice.setValid(true);
		choice.setQuestion(question);
		mcqChoiceDao.create(choice);
		MCQChoice choice1 = new MCQChoice();
		choice1.setChoice("Drink");
		choice1.setValid(false);
		choice1.setQuestion(question);
		mcqChoiceDao.create(choice1);
		MCQChoice choice2 = new MCQChoice();
		choice2.setChoice("Foo");
		choice2.setValid(false);
		choice2.setQuestion(question);
		mcqChoiceDao.create(choice2);
		
		
		
		//when 
		MCQChoice choice3 = new MCQChoice();
		choice3.setChoice("Food");
		choice3.setValid(false);
		choice3.setQuestion(question);
		mcqChoices.add(choice);
		mcqChoices.add(choice3);
		questionDs.updateQuestion(question, mcqChoices);
		
		//Then
		Question fetchedQuestion = questionDao.getById(question.getId());
		Assert.assertEquals(fetchedQuestion.getMcqChoices().size(), mcqChoices.size());
	}
	
	/**
	 * Method that handles the search of questions
	 * @throws Exception
	 */
	@Test
	public void TestSearch() throws Exception {
		//given
		Exam exam = new Exam();
		exam.setTitle("Test");
		examDao.create(exam);
		
		Question question = new Question();
		question.setTitle("What is java");
		question.setExam(exam);
		questionDao.create(question);
		
		//when 
		List<Question> questions = questionDs.searchQuestion(exam.getId());
		
		//Then
		Assert.assertEquals(questions.size(), 1);
	}
	
	/**
	 * Method that handles the search by id of a question
	 * @throws Exception
	 */
	@Test
	public void TestGetById() throws Exception {
		//given
		Exam exam = new Exam();
		exam.setTitle("Test");
		examDao.create(exam);
		
		Question question = new Question();
		question.setTitle("What is java");
		question.setExam(exam);
		questionDao.create(question);
		
		//when 
		Question fetchedQuestion = questionDs.searchQuestionById(question.getId());
		
		//Then
		Assert.assertEquals(fetchedQuestion.getId(), question.getId());
	}

}
