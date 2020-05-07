package fr.epita.quiz.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

/***
 * Entity User class
 * This class represents the User table in the database
 * @author juanc
 *
 */

@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	private String id;
	
	@Column(name="U_NAME")
	private String name;

	@Column(name="U_AGE")
	private Integer age;
	
	@Column(name="U_EMAIL")
	private String email;

	public User(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
