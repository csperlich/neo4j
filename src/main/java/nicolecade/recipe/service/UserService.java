package nicolecade.recipe.service;

import java.util.Collections;

import nicolecade.recipe.domain.User;

public class UserService extends GenericService<User> {

	@Override
	public Class<User> getEntityType() {
		return User.class;
	}

	/**
	 * Attempts to find the user with the supplied username and password. As we
	 * add relatinships to the User type, we may have to change the way this
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
}
