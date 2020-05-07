package fr.epita.quiz.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * Question data transfer object
 * Class that represent the question that is going to be send to the front end
 * @author juanc
 *
 */
public class QuestionDTO {
	
	private Long id;
	
	private String title;
	
	private Long examId;
	
	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<MCQChoicesDTO> choices;
	
	public QuestionDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public List<MCQChoicesDTO> getChoices() {
		return choices;
	}

	public void setChoices(List<MCQChoicesDTO> choices) {
		this.choices = choices;
	}
	
	

}
