package nicolecade.movie.service;

import org.neo4j.ogm.session.Session;

import java.util.Collections;
import java.util.Map;

import nicolecade.movie.domain.Person;
import nicolecade.util.db.Neo4jSessionFactory;

public class CustomService {
	// Create the session
	Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

	public void customGetPersons() {
		final Iterable<Person> person = this.session.query(Person.class, "MATCH (a:Person) RETURN a",
				Collections.<String, Object> emptyMap());
		for (final Person p : person) {
			System.out.println(p);
		}
	}

	public void shortestPath() {
		final Iterable<Map<String, Object>> result = this.session.query(
				"MATCH p=shortestPath((bacon:Person {name:\"Kevin Bacon\"})-[*]-(meg:Person {name:\"Meg Ryan\"})) RETURN p",
				Collections.<String, Object> emptyMap());
		for (final Map<String, Object> row : result) {
			System.out.println(row.get(row.keySet().iterator().next()));
		}
	}
}
