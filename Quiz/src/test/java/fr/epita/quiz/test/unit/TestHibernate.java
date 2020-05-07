package fr.epita.quiz.test.unit;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestHibernate {
	
private static final Logger LOGGER = LogManager.getLogger(TestHibernate.class);
	
	@Inject
	SessionFactory sf;

	@BeforeClass
	public static void beforeAll() {
		LOGGER.info("Test Hibernate just started");
	}
	
	@Before
	public void beforeTestFirst() {
		LOGGER.info("Testing Question table");
	}
	
	@Test
	public void testFirstSessionFactory() {
		Session openSession = sf.openSession();
		LOGGER.info("Session opened");
		Question question = new Question();
		LOGGER.info("Question created");
		question.setTitle("What is a dependency?");
		openSession.save(question);
		LOGGER.info("Question saved");
		
		Assert.assertNotEquals(0l, question.getId().longValue());
	}
	
	@After
	public void afterTestFirst() {
		LOGGER.info("Testing question finished");
	}
	
	@Before
	public void beforeTestSecond() {
		LOGGER.info("Testing Students teable");
	}
	
	@Test
	public void testStudentsTable() {
		Session openSession = sf.openSession();
		LOGGER.info("Session opened");
		User student = new User();
		LOGGER.info("Student created");
		student.setName("Juan Riveros");
		student.setAge(28);
		openSession.save(student);
		LOGGER.info("Student saved");
		
		Assert.assertNotEquals(0l, student.getId());
	}
	
	@After
	public void afterTestStudent() {
		LOGGER.info("Testing students finished");
	}
	

}
