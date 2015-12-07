package nicolecade.recipe.service;

import org.neo4j.ogm.session.result.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import nicolecade.recipe.domain.User;

public class UserService extends GenericService<User> {

	@Override
	public Class<User> getEntityType() {
		return User.class;
	}

	/**
	 * Attempts to find the user with the supplied username and password. As we
	 * add relationships to the User type, we may have to change the way this
	 * method works as all relationships may not be retrieved with the query
	 * within.
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password) {
		final User user = this.session.queryForObject(User.class,
				"MATCH (u:User {username:'" + username + "', password:'" + password + "'}) return u",
				Collections.<String, Object> emptyMap());

		return user;
	}

	public User register(String username, String password) {
		User user = this.session.queryForObject(User.class, "MATCH (u:User {username:'" + username + "'}) return u",
				Collections.<String, Object> emptyMap());

		// if didn't find user, then create a new user
		if (user == null) {
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user = this.createOrUpdate(user);
			return user;
		} else {
			return null;
		}
	}

	public List<String> getShortestPath(String username1, String username2) {
		final Result result = this.session.query("MATCH p=shortestPath((u1:User {username:'" + username1
				+ "'})-[:FOOD_BUDDIES*]-(u2:User {username:'" + username2 + "'})) RETURN p",
				Collections.<String, LinkedHashMap> emptyMap());

		final List<String> baconList = new ArrayList<String>();

		final Iterator<Map<String, Object>> iterator = result.iterator();
		if (!iterator.hasNext()) {
			return baconList;
		}

		final ArrayList resultList = (ArrayList) (iterator.next().get("p"));
		for (int i = 0; i < resultList.size(); i++) {
			final LinkedHashMap resultItemAttributeMap = (LinkedHashMap) resultList.get(i);
			final String username = (String) resultItemAttributeMap.get("username");
			if (username != null) {
				baconList.add(username);
			}
		}

		return baconList;
	}

	public User mostLikesForRecipeWithIngredient(String ingredient) {
		final User biggestLiker = this.session.queryForObject(User.class,
				"match (user:User)<-[:LEFT_BY]-(review:Review {likedIt:true})<-[r:HAS_REVIEW]-(recipe:Recipe)-->(ingredient:Ingredient {name:'"
						+ ingredient + "'}) return user, count(r) as likes order by likes desc limit 1",
				Collections.<String, Object> emptyMap());
		return biggestLiker;
	}

}
