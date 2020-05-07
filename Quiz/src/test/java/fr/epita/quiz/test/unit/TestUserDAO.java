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

import fr.epita.quiz.datamodel.User;
import fr.epita.quiz.services.dao.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestUserDAO {

	private static final Logger LOGGER = LogManager.getLogger(TestUserDAO.class);
	
	@Inject
	UserDAO studentdao;
	
	@Inject
	DataSource ds;
	
	@Test
	public void TestCreate() {
		//Given 
		User student = new User();
		student.setName("Juan Riveros");
		student.setAge(28);
		student.setEmail("mail@mail.com");
		student.setId("120");
		//student.setIsStudent(true);
		//student.setPassword("pass");
		
		//When
		studentdao.create(student);
		
		//Then
		try(Connection connection = ds.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement("Select * from Users");
				ResultSet rs = prepareStatement.executeQuery();) {
					int count = rs.getInt(1);
					Assert.assertEquals(1, count);
			}
			catch(Exception e){
				LOGGER.error("Some exception occured whule performing count verification", e);
			}			
	}
	
	@Test
	public void TestUpdate() throws SQLException {
		//Given 
		User student = new User();
		student.setName("Juan Riv");
		student.setAge(28);
		student.setEmail("mail@mail.com");
		student.setId("119");
		//student.setIsStudent(true);
		//student.setPassword("pass");
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Users values (?, ?, ?, ?, ?, ?)");
		prepareStatement.setString(1, student.getId());
		prepareStatement.setInt(2, student.getAge());
		prepareStatement.setString(3, student.getName());
		//prepareStatement.setBoolean(4, student.getIsStudent());
		prepareStatement.setString(5, student.getEmail());
		//prepareStatement.setString(6, student.getPassword());
		prepareStatement.executeUpdate();

		
		
		//When
		student.setName("Juan Riveros");
		studentdao.update(student);
		
		//Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Users where Id = ?");
		prepareStatement1.setString(1, student.getId());
		ResultSet rs = prepareStatement1.executeQuery();
		User studentUpdated = new User();
		while(rs.next()) {
			studentUpdated.setId(rs.getString("ID"));
			studentUpdated.setName(rs.getString("U_NAME"));
			studentUpdated.setAge(rs.getInt("U_AGE"));
			studentUpdated.setEmail(rs.getString("U_EMAIL"));
		}
		
		Assert.assertEquals(student.getName(), studentUpdated.getName());
	}
	
	@Test
	public void TestDelete() throws SQLException {
		//Given 
		User student = new User();
		student.setName("Juan Riveros");
		student.setAge(28);
		student.setEmail("mail@mail.com");
		student.setId("121");
		//student.setPassword("pass");
		//student.setIsStudent(true);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Users values (?, ?, ?, ?, ?, ?)");
		prepareStatement.setString(1, student.getId());
		prepareStatement.setInt(2, student.getAge());
		prepareStatement.setString(3, student.getName());
		//prepareStatement.setBoolean(4, student.getIsStudent());
		prepareStatement.setString(5, student.getEmail());
		//prepareStatement.setString(6, student.getPassword());
		prepareStatement.executeUpdate();
		
		//When
		studentdao.delete(student);
		
		//Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Users where Id = ?");
		prepareStatement1.setString(1, student.getId());
		ResultSet rs = prepareStatement1.executeQuery();
		User studentDeleted = new User();
		if(rs.next() == false) {
			studentDeleted = null;
		}
		
		Assert.assertNull(studentDeleted);
	}
	
	@Test
	public void TestSearch() throws SQLException {
		//Given 
		User student = new User();
		student.setName("Juan Riveros");
		student.setAge(28);
		student.setEmail("mail@mail.com");
		student.setId("122");
		//student.setIsStudent(true);
		//student.setPassword("pass");
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Users values (?, ?, ?, ?, ?, ?)");
		prepareStatement.setString(1, student.getId());
		prepareStatement.setInt(2, student.getAge());
		prepareStatement.setString(3, student.getName());
		//prepareStatement.setBoolean(4, student.getIsStudent());
		prepareStatement.setString(5, student.getEmail());
		//prepareStatement.setString(6, student.getPassword());
		prepareStatement.executeUpdate();
		
		//When
		List<User> getStudent = studentdao.search(student);

		//Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Users");
		ResultSet rs = prepareStatement1.executeQuery();
		int count = 0;
		while(rs.next()) {
			count++;
		}
		
		Assert.assertEquals(getStudent.size(), count);
	}
	
	@Test
	public void TestGetById() throws SQLException {
		//Given
		User student = new User();
		student.setName("Juan Riveros");
		student.setAge(28);
		student.setEmail("mail@mail.com");
		student.setId("123");
		//student.setIsStudent(true);
		//student.setPassword("pass");
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Users values (?, ?, ?, ?, ?, ?)");
		prepareStatement.setString(1, student.getId());
		prepareStatement.setInt(2, student.getAge());
		prepareStatement.setString(3, student.getName());
		//prepareStatement.setBoolean(4, student.getIsStudent());
		prepareStatement.setString(5, student.getEmail());
		//prepareStatement.setString(6, student.getPassword());
		prepareStatement.executeUpdate();
		
		//When
		User getStudent = studentdao.getById(student.getId());
		
		//Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Users where Id = ?");
		prepareStatement1.setString(1, student.getId());
		ResultSet rs = prepareStatement1.executeQuery();
		User studentGet = new User();
		while(rs.next()) {
			studentGet.setId(rs.getString("ID"));
			studentGet.setName(rs.getString("U_NAME"));
			studentGet.setAge(rs.getInt("U_AGE"));
			studentGet.setEmail(rs.getString("U_EMAIL"));
		}
		Assert.assertEquals(getStudent.getId(), studentGet.getId());
	}
	
}
