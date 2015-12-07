package nicolecade.recipe.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

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

	@Relationship(type = "FOOD_BUDDIES", direction = Relationship.UNDIRECTED)
	private List<User> foodBuddies;

	public User() {
		this.foodBuddies = new ArrayList<>();
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void addFoodBuddy(User foodBuddy) {
		this.foodBuddies.add(foodBuddy);
	}

	public void removeFoodBuddy(User foodBuddy) {
		this.foodBuddies.remove(this.foodBuddies.indexOf(foodBuddy));
	}

	public void setFoodBuddies(List<User> foodBuddies) {
		this.foodBuddies = foodBuddies;
	}

	public List<User> getFoodBuddies() {
		return this.foodBuddies;
	}

	@Override
	public String toString() {
		return "User [password=" + this.password + ", username=" + this.username
				+ ", gID=" + this.getId() + "]";
	}
}
