package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.Exam;

/***
 * Exam data access object class
 * Class in charge of handling the access and operations in the database for the exam table 
 * @author juanc
 *
 */
public class ExamDAO extends GenericDAO<Exam, Long> {
	
	/**
	 * Method to set the where query 
	 */
	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "from Exam";
	}

	/**
	 * Method to set the parameters from the criteria 
	 */
	@Override
	public void setParameters(Map<String, Object> parameters, Exam criteria) {
		
	}

	/**
	 * Method that returns the entity class
	 */
	@Override
	public Class<Exam> getEntityClass() {
		// TODO Auto-generated method stub
		return Exam.class;
	}
	
}
