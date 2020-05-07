package fr.epita.quiz.services.business;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.datamodel.MCQAnswer;
import fr.epita.quiz.datamodel.MCQChoice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.dao.AnswerDAO;
import fr.epita.quiz.services.dao.ExamDAO;
import fr.epita.quiz.services.dao.MCQAnswerDAO;
import fr.epita.quiz.services.dao.MCQChoiceDAO;
import fr.epita.quiz.services.dao.QuestionDAO;
import fr.epita.quiz.services.dao.UserDAO;

/**
 * Exam Data service class
 * Class in charge of the business logic of the exam  
 * @author juanc
 *
 */
public class ExamDataService {

	@Inject
	AnswerDAO answerDao;
	
	@Inject
	UserDAO userDao;
	
	@Inject
	ExamDAO examDao;
	
	@Inject
	MCQAnswerDAO mcqAnswerDao;
	
	@Inject
	QuestionDAO questionDao;
	
	@Inject
	MCQChoiceDAO mcqChoiceDao;
	
	/**
	 * Method that handle the business logic of inserting a multiple choice answer
	 * @param answers
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public void answersExam(List<MCQAnswer> answers) throws Exception {
	
		if(answers == null)
			throw new Exception("The answers is null");
		
		for (MCQAnswer mcqAnswer : answers) {
			
			if(mcqAnswer.getExam() == null)
				throw new Exception("The exam is null");
			
			if(mcqAnswer.getUser() == null)
				throw new Exception("The user is null");
			
			mcqAnswerDao.create(mcqAnswer);
		}
		
	}
	
	/**
	 * Method that get an user answers of an exam
	 * @param examId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public List<MCQAnswer> getAnswers(Long examId, String userId) throws Exception {
	
		if(examId == null)
			throw new Exception("The exam is null");
		
		Exam exam = examDao.getById(examId);
		User user = userDao.getById(userId);
		
		MCQAnswer criteria = new MCQAnswer();
		criteria.setExam(exam);
		criteria.setUser(user);
		
		List<MCQAnswer> answers = mcqAnswerDao.search(criteria);
		
		return answers;
	}
	
	/**
	 * Method that handles the creation or update of a user
	 * @param user
	 * @throws Exception
	 */
	@Transactional(value = TxType.REQUIRED)
	public void createUser(User user) throws Exception {
		
		if(user == null)
			throw new Exception("The user is null");
			
		
		User oldUser = userDao.getById(user.getId());
		
		if(oldUser == null) {
			userDao.create(user);
		}
		else {
			userDao.update(user);
		}
		
	}
	
}
