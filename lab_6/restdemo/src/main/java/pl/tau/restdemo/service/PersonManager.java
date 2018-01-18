package pl.tau.restdemo.service;

// w oparciu o przyklad J Neumanna, przerobiony przez T Puzniakowskiego

import pl.tau.restdemo.domain.Person;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PersonManager {
	Connection getConnection();
	List<Person> getAllPersons();
	void deletePerson(int id);
	void clearPersons() throws SQLException;
	int addPerson(Person person);
	Person getPerson(int id);
}
