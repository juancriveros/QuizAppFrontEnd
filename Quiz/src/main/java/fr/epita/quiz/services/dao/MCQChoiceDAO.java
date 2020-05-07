package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.MCQChoice;

/***
 * MCQChoice data access object class
 * Class in charge of handling the access and operations in the database for the MCQChoice table 
 * @author juanc
 *
 */
public class MCQChoiceDAO extends GenericDAO<MCQChoice, Long> {
	
	/**
	 * Method to set the where query 
	 */
	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "from MCQChoice where question = :pQuestion";
	}

	/**
	 * Method to set the parameters from the criteria 
	 */
	@Override
	public void setParameters(Map<String, Object> parameters, MCQChoice criteria) {
		parameters.put("pQuestion", criteria.getQuestion());
	}

	/**
	 * Method that returns the entity class
	 */
	@Override
	public Class<MCQChoice> getEntityClass() {
		// TODO Auto-generated method stub
		return MCQChoice.class;
	}
	
}
