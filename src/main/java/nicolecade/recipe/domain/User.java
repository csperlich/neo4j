package nicolecade.recipe.domain;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class User extends DomainObject {

	@Property(name = "password")
	private String password;

	@Property(name = "username")
	private String username;

	@Index(unique = true)
	public String getUsername() {
		return this.username;
	}

	public void setPassword(String nextLine) {
		// TODO Auto-generated method stub

	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [password=" + this.password + ", username=" + this.username + ", gID=" + this.getId() + "]";
	}
}
