package fr.epita.quiz.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/***
 * Entity MCQChoice class
 * This class represents the MCQChoice table in the database
 * @author juanc
 *
 */

@Entity
@Table(name = "MCQCHOICES")
public class MCQChoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="MC_CHOICE")
	private String choice;
	
	@Column(name="MC_VALID")
	private Boolean valid;	
	
	@ManyToOne
	@JoinColumn(name = "MC_QUESTION_FK")
	private Question question;
	
	public MCQChoice() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

}
