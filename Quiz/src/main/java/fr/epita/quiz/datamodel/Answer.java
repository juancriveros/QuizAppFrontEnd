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
 * Entity Answer class
 * This class represents the Answer Table in the database
 * @author juanc
 *
 */

@Entity
@Table(name = "ANSWERS")
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "A_CONTENT")
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "A_USER", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "A_EXAM", nullable = false)
	private Exam exam;
		
	@ManyToOne
	@JoinColumn(name = "A_QUESTION", nullable = false)
	private Question question;
	
	public Answer() {
	}
	
	

	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public void setuser(User user) {
		this.user = user;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getuser() {
		return user;
	}

	public Exam getExam() {
		return exam;
	}

	public Question getQuestion() {
		return question;
	}

}
