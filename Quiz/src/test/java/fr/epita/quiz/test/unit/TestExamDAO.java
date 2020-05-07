package fr.epita.quiz.test.unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Exam;
import fr.epita.quiz.services.dao.ExamDAO;

/***
 * Test exam class
 * Class in charge of testing the exam dao methods
 * @author juanc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestExamDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(TestExamDAO.class);
	
	@Inject
	ExamDAO examDao;
	
	@Inject
	DataSource ds;
	
	/**
	 * Method that handles the creation of a new exam test
	 */
	@Test
	public void TestCreate() {
		//Given
		Exam exam = new Exam();
		exam.setTitle("Java Test");
		
		//When
		examDao.create(exam);

		//Then 
		try(Connection connection = ds.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("Select * from Exams");
			ResultSet rs = prepareStatement.executeQuery();) {
				int count = rs.getInt(1);
				Assert.assertEquals(1, count);
		}
		catch(Exception e){
			LOGGER.error("Some exception occured whule performing count verification", e);
		}
	}
	
	/**
	 * Method that handles the update of a exam test
	 * @throws SQLException
	 */
	@Test
	public void TestUpdate() throws SQLException {
		//Given
		Exam exam = new Exam();
		exam.setTitle("Java Te");
		exam.setId(4l);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Exams values (?, ?)");
		prepareStatement.setLong(1, exam.getId());
		prepareStatement.setString(2, exam.getTitle());
		prepareStatement.executeUpdate();
		
		//when
		exam.setTitle("Java Test");
		examDao.update(exam);
		
		//Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Exams where ID = ?");
		prepareStatement1.setLong(1, exam.getId());
		ResultSet rs = prepareStatement1.executeQuery();
		Exam examUpdated = new Exam();
		while(rs.next()) {
			examUpdated.setId(rs.getLong("ID"));
			examUpdated.setTitle(rs.getString("E_TITLE"));
		}
		
		Assert.assertEquals(exam.getTitle(), examUpdated.getTitle());
	}
	
	/**
	 * Method that handles the deleting of a exam test
	 * @throws SQLException
	 */
	@Test
	public void TestDelete() throws SQLException {
		//Given
		Exam exam = new Exam();
		exam.setTitle("Java Test");
		exam.setId(2l);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Exams values (?, ?)");
		prepareStatement.setLong(1, exam.getId());
		prepareStatement.setString(2, exam.getTitle());
		prepareStatement.executeUpdate();
		
		//When 
		examDao.delete(exam);
		
		//Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Exams where ID = ?");
		prepareStatement1.setLong(1, exam.getId());
		ResultSet rs = prepareStatement1.executeQuery();
		Exam examDeleted = new Exam();
		if (rs.next() == false) {
			examDeleted= null;
		}
		
		Assert.assertNull(examDeleted);
	}
	
	/**
	 * Method that handles the search of exams test
	 * @throws SQLException
	 */
	@Test
	public void TestSearch() throws SQLException {
		//Given
		Exam exam = new Exam();
		exam.setTitle("Java Test");
		exam.setId(3l);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Exams values (?, ?)");
		prepareStatement.setLong(1, exam.getId());
		prepareStatement.setString(2, exam.getTitle());
		prepareStatement.executeUpdate();
		
		//When
		List<Exam> getExam = examDao.search(exam);
		
		//Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Exams");
		ResultSet rs = prepareStatement1.executeQuery();
		int examDeleted = 0;
		while(rs.next()) {
			examDeleted++;
		}
		
		Assert.assertEquals(getExam.size(), examDeleted);
	}
	
	/**
	 * Method that handles the get by id of new exam test
	 * @throws SQLException
	 */
	@Test
	public void TestGetById() throws SQLException {
		//Given
		Exam exam = new Exam();
		exam.setTitle("Java Test");
		exam.setId(5l);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Exams values (?, ?)");
		prepareStatement.setLong(1, exam.getId());
		prepareStatement.setString(2, exam.getTitle());
		prepareStatement.executeUpdate();
		
		//When
		Exam getExam = examDao.getById(exam.getId().longValue());
		
		//Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Exams where ID = ?");
		prepareStatement1.setLong(1, exam.getId());
		ResultSet rs = prepareStatement1.executeQuery();
		Exam examUpdated = new Exam();
		while(rs.next()) {
			examUpdated.setId(rs.getLong("ID"));
			examUpdated.setTitle(rs.getString("E_TITLE"));
		}
		
		Assert.assertEquals(getExam.getId(), examUpdated.getId());
	}
	
	


}
