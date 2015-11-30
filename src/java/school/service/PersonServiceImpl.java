package java.school.service;

import java.school.domain.Person;

public class PersonServiceImpl extends GenericService<Person>implements PersonService {

	@Override
	public Class<Person> getEntityType() {
		return Person.class;
	}
}
