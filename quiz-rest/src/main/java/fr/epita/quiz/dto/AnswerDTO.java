package fr.epita.quiz.dto;

import java.util.List;

/**
 * Answer data transfer object
 * Class that represent the answer that is going to be send to the front end
 * @author juanc
 *
 */
public class AnswerDTO {
	
	private List<MCQChoicesDTO> choices;
	
	private ExamDTO exam;
	
	private UserDTO student;
	
	public AnswerDTO() {
		// TODO Auto-generated constructor stub
	}

	public List<MCQChoicesDTO> getChoices() {
		return choices;
	}

	public void setChoices(List<MCQChoicesDTO> choices) {
		this.choices = choices;
	}

	public ExamDTO getExam() {
		return exam;
	}

	public void setExam(ExamDTO exam) {
		this.exam = exam;
	}

	public UserDTO getStudent() {
		return student;
	}

	public void setStudent(UserDTO student) {
		this.student = student;
	}
	
	

}
