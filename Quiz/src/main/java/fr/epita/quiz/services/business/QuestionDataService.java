package fr.epita.quiz.services.business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dao.AnswerDAO;
import fr.epita.quiz.services.dao.ExamDAO;
import fr.epita.quiz.services.dao.MCQChoiceDAO;
import fr.epita.quiz.services.dao.QuestionDAO;
import fr.epita.quiz.services.dao.UserDAO;

/**
 * Question data service class
 * Class in charge of the question business logic
 * @author juanc
 *
 */
public class QuestionDataService {
	
	@Inject
	AnswerDAO answerDao;
	
	@Inject
	UserDAO userDao;
	
	@Inject
	ExamDAO examDao;
	
	@Inject
	QuestionDAO questionDao;
	
	@Inject
	MCQChoiceDAO mcqChoiceDao;
	
	/**
	 * Method that handles the business logic of creating a question 
	 * @param question
	 * @param mcqChoices
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public void createQuestion(Question question, List<MCQChoice> mcqChoices) throws Exception {
		
		
		if(question == null || mcqChoices == null)
			throw new Exception("The question or choices are null");
		
		
		Exam exam = examDao.getById(question.getExam().getId());
		if(exam == null)
			throw new Exception("The exam does not exists");
		
		question.setExam(exam);
		//Case the question is new with the options
		questionDao.create(question);

		if(question.getId() > 0) {
			for (MCQChoice mcqChoice : mcqChoices) {
				mcqChoice.setQuestion(question);
				mcqChoiceDao.create(mcqChoice);
			}
		}
		
		}
		
	/**
	 * Method that handles the business logic of updating a question
	 * @param question
	 * @param mcqChoices
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public void updateQuestion(Question question, List<MCQChoice> mcqChoices) throws Exception {
		
		if(question == null || mcqChoices == null)
			throw new Exception("The question or choices are null");
		
		Exam exam = examDao.getById(question.getExam().getId());
		if(exam == null)
			throw new Exception("The exam does not exists");
		
		Question getQuestion = questionDao.getById(question.getId());
		if(getQuestion == null)
			throw new Exception("The question does not exists");
		getQuestion.setTitle(question.getTitle());
		questionDao.update(getQuestion);
		
		MCQChoice criteria = new MCQChoice();
		criteria.setQuestion(getQuestion);
		List<MCQChoice> originalChoices = mcqChoiceDao.search(criteria);
		
		for (MCQChoice mcqChoice : mcqChoices) {
			originalChoices.removeIf(x -> x.getId() == mcqChoice.getId());
			if(mcqChoice.getId() == null) { //Case is a new choice
				mcqChoice.setQuestion(getQuestion);
				mcqChoiceDao.create(mcqChoice);
			}
			else { //Case is an update of the choices
				MCQChoice getMcqChoice = mcqChoiceDao.getById(mcqChoice.getId());
				getMcqChoice.setChoice(mcqChoice.getChoice());
				getMcqChoice.setValid(mcqChoice.getValid());
				mcqChoiceDao.update(getMcqChoice);
			
			}
		}
		
		if(originalChoices.size() > 0) {
			for (MCQChoice mcqChoice : originalChoices) {
				mcqChoiceDao.delete(mcqChoice);
			}
		}
	}
	
	/**
	 * Method that handles the business logic of getting the questions from an exam
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public List<Question> searchQuestion(Long examId) throws Exception {
		
		if(examId == null)
			throw new Exception("The exam id is null");
		
		Exam exam = examDao.getById(examId);
		if(exam == null)
			throw new Exception("The exam does not exists");
		
		Question question = new Question();
		question.setExam(exam);
		List<Question> questions = questionDao.search(question);
		/*
		for (Question question2 : questions) {
			MCQChoice mcqCriteria = new MCQChoice();
			mcqCriteria.setQuestion(question);
			List<MCQChoice> mcqChoices = mcqChoiceDao.search(mcqCriteria);
			question2.setMcqChoices(mcqChoices);
		}
		*/
		return questions;
	}
	
	/**
	 * Method that handles the business logic of getting a question by it's id
	 * @param questionId
	 * @return
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public Question searchQuestionById(Long questionId) throws Exception {
		
		if(questionId == null)
			throw new Exception("The question id is null");
		
		Question question = questionDao.getById(questionId);
		/*MCQChoice mcqCriteria = new MCQChoice();
		mcqCriteria.setQuestion(question);
		
		List<MCQChoice> mcqChoices = mcqChoiceDao.search(mcqCriteria);
		question.setMcqChoices(mcqChoices);
		*/
		return question;
	}
	
	/**
	 * Method that handles the business logic of deleting a question
	 * @param questionId
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public void deleteQuestion(Long questionId) throws Exception {
		
		if(questionId == null)
			throw new Exception("The question id is null");
		
		Question question = questionDao.getById(questionId);
		MCQChoice criteria = new MCQChoice();
		criteria.setQuestion(question);
		List<MCQChoice> choices = mcqChoiceDao.search(criteria);
		
		for (MCQChoice mcqChoice : choices) {
			mcqChoiceDao.delete(mcqChoice);
		}
		
		questionDao.delete(question);
	
	}
	 

}
