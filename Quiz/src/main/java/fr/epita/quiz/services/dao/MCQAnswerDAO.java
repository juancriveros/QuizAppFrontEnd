package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.MCQAnswer;

/***
 * MCQAnswer data access object class
 * Class in charge of handling the access and operations in the database for the MCQAnswer table 
 * @author juanc
 *
 */
public class MCQAnswerDAO extends GenericDAO<MCQAnswer, Long> {

	/**
	 * Method to set the where query 
	 */
	@Override
	public String getQuery() {
		return "from MCQAnswer where exam = :pExam and user = :pUser";
	}

	/**
	 * Method to set the parameters from the criteria 
	 */
	@Override
	public void setParameters(Map<String, Object> parameters, MCQAnswer criteria) {
		parameters.put("pExam", criteria.getExam());
		parameters.put("pUser", criteria.getUser());
	}

	/**
	 * Method that returns the entity class
	 */
	@Override
	public Class<MCQAnswer> getEntityClass() {
		return MCQAnswer.class;
	}
	
}
