package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.Question;

/***
 * Question data access object class
 * Class in charge of handling the access and operations in the database for the Question table 
 * @author juanc
 *
 */
public class QuestionDAO extends GenericDAO<Question, Long> {
	
	/**
	 * Method to set the where query 
	 */
	@Override
	public String getQuery() {
		return "from Question where exam = :pExam";
	}

	/**
	 * Method to set the parameters from the criteria 
	 */
	@Override
	public void setParameters(Map<String, Object> parameters, Question criteria) {
		parameters.put("pExam", criteria.getExam());
	}

	/**
	 * Method that returns the entity class
	 */
	@Override
	public Class<Question> getEntityClass() {
		return Question.class;
	}
	
}
