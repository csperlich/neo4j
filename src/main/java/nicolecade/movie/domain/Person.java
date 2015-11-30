package nicolecade.movie.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;
import java.util.List;

@NodeEntity
public class Person extends DomainObject {

	@Property(name = "birthday")
	@DateString("yyyy-MM-dd")
	Date birthday;

	@Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
	private List<ActedIn> moviesActedIn;

	@Relationship(type = "DIRECTED", direction = Relationship.OUTGOING)
	private List<Movie> moviesDirected;

	@Relationship(type = "WROTE", direction = Relationship.OUTGOING)
	private List<Movie> moviesWritten;

	@Property(name = "name")
	private String name;

	public Person() {
	}

	public void addMovieActedIn(ActedIn movieActedIn) {
		this.moviesActedIn.add(movieActedIn);
	}

	public void addMovieDirected(Movie movie) {
		this.moviesDirected.add(movie);
	}

	public void addMovieWritten(Movie movie) {
		this.moviesWritten.add(movie);
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public List<ActedIn> getMoviesActedIn() {
		return this.moviesActedIn;
	}

	public List<Movie> getMoviesDirected() {
		return this.moviesDirected;
	}

	public List<Movie> getMoviesWritten() {
		return this.moviesWritten;
	}

	public String getName() {
		return this.name;
	}

	public List<ActedIn> getRolesPlayed() {
		return this.moviesActedIn;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setMoviesActedIn(List<ActedIn> moviesActedIn) {
		this.moviesActedIn = moviesActedIn;
	}

	public void setMoviesDirected(List<Movie> moviesDirected) {
		this.moviesDirected = moviesDirected;
	}

	public void setMoviesWritten(List<Movie> moviesWritten) {
		this.moviesWritten = moviesWritten;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRolesPlayed(List<ActedIn> rolesPlayed) {
		this.moviesActedIn = rolesPlayed;
	}
}
