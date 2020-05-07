package fr.epita.quiz.dto;

import fr.epita.quiz.datamodel.Exam;

/**
 * Exam data transfer object
 * Class that represent the exam that is going to be send to the front end
 * @author juanc
 *
 */
public class ExamDTO {
	
	private Long id;
	
	private String title;
	
	public ExamDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ExamDTO(Exam entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
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
	
	

}
