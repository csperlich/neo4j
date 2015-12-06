package nicolecade.recipe.service;

import nicolecade.recipe.domain.User;

public class UserService extends GenericService<User> {

	@Override
	public Class<User> getEntityType() {
		return User.class;
	}

}
