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
import fr.epita.quiz.datamodel.MCQAnswer;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.business.ExamDataService;
import fr.epita.quiz.services.dao.ExamDAO;
import fr.epita.quiz.services.dao.MCQAnswerDAO;
import fr.epita.quiz.services.dao.MCQChoiceDAO;
import fr.epita.quiz.services.dao.QuestionDAO;
import fr.epita.quiz.services.dao.UserDAO;

/**
 * Exam data service test
 * Class that handles the test of all methods in exam data service class
 * @author juanc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestExamDataService {
	
	@Inject
	ExamDAO examDao;
	
	@Inject
	QuestionDAO questionDao;
	
	@Inject
	MCQChoiceDAO mcqChoiceDao;
	
	@Inject
	UserDAO userDao;
	
	@Inject
	MCQAnswerDAO mcqanswerDao;
	
	@Inject 
	ExamDataService examDs;

	/**
	 * Method that tst the creation of answers in a exam
	 * @throws Exception
	 */
	@Test
	public void testAnswersExam() throws Exception {
	
		//given
		Exam exam = new Exam();
		exam.setTitle("Test");
		examDao.create(exam);
		
		User user = new User();
		user.setId("123");
		user.setName("Juan");
		userDao.create(user);
		
		Question question = new Question();
		question.setTitle("What is java");
		question.setExam(exam);
		questionDao.create(question);
		
	
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
		
		List<MCQAnswer> answers = new ArrayList<MCQAnswer>();
		MCQAnswer answer1 = new MCQAnswer(user, choice2, exam);
		answers.add(answer1);
		
		//When
		examDs.answersExam(answers);
		
		
		//Then
		List<MCQAnswer> fetchedAnswer = mcqanswerDao.search(answer1);
		
		
		Assert.assertEquals(answers.size(), fetchedAnswer.size()); 
		
		
	}

	/**
	 * Method that test the search of answers in a exam
	 * @throws Exception
	 */
	@Test
	public void testGetAnswers() throws Exception {
	
		//given
		Exam exam = new Exam();
		exam.setTitle("Test");
		examDao.create(exam);
		
		User user = new User();
		user.setId("1234");
		user.setName("Juan");
		userDao.create(user);
		
		Question question = new Question();
		question.setTitle("What is java");
		question.setExam(exam);
		questionDao.create(question);
		
	
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
		
		MCQAnswer answer1 = new MCQAnswer(user, choice2, exam);
		mcqanswerDao.create(answer1);
		
		//When
		List<MCQAnswer> getAnswers = examDs.getAnswers(exam.getId(), user.getId());
		
		
		//Then
		List<MCQAnswer> fetchedAnswer = mcqanswerDao.search(answer1);

		Assert.assertEquals(fetchedAnswer.size(), getAnswers.size()); 
		
		
	}
	
	@Test
	public void TestCreateUser() throws Exception {
		
		//given 
		User user = new User();
		user.setId("123");
		user.setName("Juan");
		
		//when 
		examDs.createUser(user);
		
		//then
		User getUser = userDao.getById("123");
		Assert.assertEquals(user.getId(), getUser.getId()); 
		
	}
	


}
