package nicolecade.movie.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class Person extends DomainObject {

	@Property(name = "born")
	int birthyear;

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

	public int getBirthday() {
		return this.birthyear;
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

	public void setBirthday(int birthday) {
		this.birthyear = birthday;
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

	@Override
	public String toString() {
		return "Person [birthday=" + this.birthyear + ", id=" + this.id + ", moviesActedIn=" + this.moviesActedIn
				+ ", moviesDirected=" + this.moviesDirected + ", moviesWritten=" + this.moviesWritten + ", name="
				+ this.name + "]";
	}
}
