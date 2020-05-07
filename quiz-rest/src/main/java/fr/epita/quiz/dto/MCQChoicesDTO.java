package fr.epita.quiz.dto;

/**
 * MCQChoice data transfer object
 * Class that represent the mcqchoice that is going to be send to the front end
 * @author juanc
 *
 */
public class MCQChoicesDTO {
	
	private Long id;
	
	private String choice;
	
	private Boolean valid;
	
	private Long questionId;
	
	private Long examId;
	
	public MCQChoicesDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}
	
	

}
