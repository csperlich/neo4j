package nicolecade.recipe.service;

import nicolecade.recipe.domain.Recipe;

public class RecipeService extends GenericService<Recipe> {

	@Override
	public Class<Recipe> getEntityType() {
		return Recipe.class;
	}

}
