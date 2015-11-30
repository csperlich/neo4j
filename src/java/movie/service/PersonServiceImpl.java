package java.movie.service;

import java.movie.domain.Person;

public class PersonServiceImpl extends GenericService<Person>implements PersonService {

	@Override
	public Class<Person> getEntityType() {
		return Person.class;
	}
}
