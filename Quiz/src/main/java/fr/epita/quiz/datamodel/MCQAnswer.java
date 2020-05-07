package fr.epita.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/***
 * Entity MCQAnswer class
 * This class represents the MCQAnswer table in the database
 * @author juanc
 *
 */

@Entity
@Table(name = "MCQANSWERS")
public class MCQAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "MCA_CHOICE", nullable = false)
	private MCQChoice mcqChoice;
	
	@ManyToOne
	@JoinColumn(name = "MCA_USER", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "MCA_EXAM", nullable = false)
	private Exam exam;

	public MCQAnswer(User user, MCQChoice mcqChoice, Exam exam) {
		
		this.user = user;
		this.mcqChoice = mcqChoice;
		this.exam = exam;
		
	}
	
	public MCQAnswer() {
		
	}
	
	public void setMcqChoice(MCQChoice mcqChoice) {
		this.mcqChoice = mcqChoice;
	}

	public void setuser(User user) {
		//this.user = user;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public MCQChoice getMcqChoice() {
		return mcqChoice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Exam getExam() {
		return exam;
	}
	
}
