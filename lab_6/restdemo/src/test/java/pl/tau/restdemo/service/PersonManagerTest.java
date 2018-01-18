package pl.tau.restdemo.service;
// przyklad na podstawie przykladow J. Neumanna
import org.junit.After;
import org.junit.Test;
import pl.tau.restdemo.domain.Person;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PersonManagerTest {

	PersonManager personManager = new PersonManagerImpl();
	
	private final static String NAME_1 = "Janek";
	private final static int YOB_1 = 1939;

	public PersonManagerTest() throws SQLException {
	}

	@After
    public void cleanup() throws SQLException {
        personManager.clearPersons();
    }

	@Test
	public void checkConnection() {
	    assertNotNull(personManager.getConnection());
	}
	
	@Test
	public void checkAdding() throws SQLException{
		Person person = new Person();
		person.setName(NAME_1);
		person.setYob(YOB_1);
		
		personManager.clearPersons();
		assertEquals(1,personManager.addPerson(person));
		
		List<Person> persons = personManager.getAllPersons();
		Person personRetrieved = persons.get(0);
		
		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(YOB_1, personRetrieved.getYob());
	}

	@Test
    public void addMultiple() throws SQLException {
        personManager.clearPersons();

        assertEquals(1,personManager.addPerson(new Person("Zenek", 1234)));
        assertEquals(1,personManager.addPerson(new Person("Karol", 1971)));
        assertEquals(1,personManager.addPerson(new Person("Karolina", 1999)));

        List<Person> persons = personManager.getAllPersons();

        assertEquals(3, persons.size());
    }

}
