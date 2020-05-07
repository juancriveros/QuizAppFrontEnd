package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.Answer;

/***
 * Answer data access object class
 * Class in charge of handling the access and operations in the database for the answer table 
 * @author juanc
 *
 */
public class AnswerDAO extends GenericDAO<Answer, Long>{

	/**
	 * Method to set the where query 
	 */
	@Override
	public String getQuery() {
		return "from Answer";
	}

	/**
	 * Method to set the parameters from the criteria 
	 */
	@Override
	public void setParameters(Map<String, Object> parameters, Answer criteria) {
	}

	/**
	 * Method that returns the entity class
	 */
	@Override
	public Class<Answer> getEntityClass() {
		return Answer.class;
	}

}
