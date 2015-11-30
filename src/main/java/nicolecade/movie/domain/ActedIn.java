package nicolecade.movie.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;
import java.util.List;

@RelationshipEntity(type = "ACTED_IN")
public class ActedIn extends DomainObject {
	@StartNode
	private Person actor;

	@DateString("yy-MM-dd")
	@Property(name = "birthday")
	private Date birthday;

	@EndNode
	private Movie movie;

	@Property(name = "roles")
	private List<String> roles;

	public ActedIn() {
	}

	public Person getActor() {
		return this.actor;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public List<String> getRoles() {
		return this.roles;
	}

	public void setActor(Person actor) {
		this.actor = actor;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
