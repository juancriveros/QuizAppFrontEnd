package fr.epita.quiz.services.dao;

import java.util.Map;

import fr.epita.quiz.datamodel.User;

/***
 * User data access object class
 * Class in charge of handling the access and operations in the database for the User table 
 * @author juanc
 *
 */
public class UserDAO extends GenericDAO<User, String>{
	
	/**
	 * Method to set the where query 
	 */
	@Override
	public String getQuery() {
		// TODO Auto-generated method stub
		return "from User";
	}

	/**
	 * Method to set the parameters from the criteria 
	 */
	@Override
	public void setParameters(Map<String, Object> parameters, User criteria) {
	}

	/**
	 * Method that returns the entity class
	 */
	@Override
	public Class<User> getEntityClass() {
		// TODO Auto-generated method stub
		return User.class;
	}
	
}
