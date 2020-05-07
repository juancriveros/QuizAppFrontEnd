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

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dao.QuestionDAO;

/***
 * Test question class
 * Class in charge of testing the question dao methods
 * @author juanc
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestQuestionDAO {

	private static final Logger LOGGER = LogManager.getLogger(TestQuestionDAO.class);

	@Inject
	QuestionDAO questionDao;

	@Inject
	DataSource ds;

	@Test
	public void TestCreate() {
		// Given
		Question question = new Question();
		question.setTitle("What is a dependency?");

		// when
		questionDao.create(question);

		// then
		try (Connection connection = ds.getConnection();
				PreparedStatement prepareStatement = connection.prepareStatement("Select * from Questions");
				ResultSet rs = prepareStatement.executeQuery();) {
			int count = rs.getInt(1);
			Assert.assertEquals(1, count);
		} catch (Exception e) {
			LOGGER.error("Some exception occured whule performing count verification", e);
		}
	}

	@Test
	public void TestUpdate() throws SQLException {

		// Given
		Question question = new Question();
		question.setTitle("What is a depend");
		question.setId(4l);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Questions values (?, ?)");
		prepareStatement.setLong(1, question.getId());
		prepareStatement.setString(2, question.getTitle());
		prepareStatement.executeUpdate();

		// when
		question.setTitle("What is a dependency?");
		questionDao.update(question);

		// Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Questions");
		ResultSet rs = prepareStatement1.executeQuery();
		Question questionUpdated = new Question();
		while (rs.next()) {
			questionUpdated.setId(rs.getLong("ID"));
			questionUpdated.setTitle(rs.getString("Q_TITLE"));
		}

		Assert.assertEquals(question.getTitle(), questionUpdated.getTitle());
	}

	@Test
	public void TestDelete() throws SQLException {
		// Given
		Question question = new Question();
		question.setTitle("What is a dependency?");
		question.setId(2l);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Questions values (?, ?)");
		prepareStatement.setLong(1, question.getId());
		prepareStatement.setString(2, question.getTitle());
		prepareStatement.executeUpdate();

		// When
		questionDao.delete(question);

		// Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Questions where ID = ?");
		prepareStatement1.setLong(1, question.getId());
		ResultSet rs = prepareStatement1.executeQuery();
		Question questionDeleted = new Question();
		if (rs.next() == false) {
			questionDeleted = null;
		}

		Assert.assertNull(questionDeleted);
	}

	@Test
	public void TestSearch() throws SQLException {
		// Given
		Question question = new Question();
		question.setTitle("What is a dependency?");
		question.setId(3l);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Questions values (?, ?)");
		prepareStatement.setLong(1, question.getId());
		prepareStatement.setString(2, question.getTitle());
		prepareStatement.executeUpdate();

		// When
		List<Question> getQuestion = questionDao.search(question);

		// Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Questions");
		ResultSet rs = prepareStatement1.executeQuery();
		int questionDeleted = 0;
		while (rs.next()) {
			questionDeleted++;
		}

		Assert.assertEquals(getQuestion.size(), questionDeleted);
	}

	@Test
	public void TestGetById() throws SQLException {
		// Given
		Question question = new Question();
		question.setTitle("What is a dependency?");
		question.setId(5l);
		Connection connection = ds.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("Insert into Questions values (?, ?)");
		prepareStatement.setLong(1, question.getId());
		prepareStatement.setString(2, question.getTitle());
		prepareStatement.executeUpdate();

		// When
		Question getQuestion = questionDao.getById(question.getId().longValue());

		// Then
		PreparedStatement prepareStatement1 = connection.prepareStatement("Select * from Questions where ID = ?");
		prepareStatement1.setLong(1, question.getId());
		ResultSet rs = prepareStatement1.executeQuery();
		Question questionGet = new Question();
		while (rs.next()) {
			questionGet.setId(rs.getLong("ID"));
			questionGet.setTitle(rs.getString("Q_TITLE"));
		}

		Assert.assertEquals(getQuestion.getId(), questionGet.getId());
	}

}
