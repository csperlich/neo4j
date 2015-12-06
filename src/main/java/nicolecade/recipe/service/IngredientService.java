package nicolecade.recipe.service;

import nicolecade.recipe.domain.Ingredient;

public class IngredientService extends GenericService<Ingredient> {
	
	@Override
	public Class<Ingredient> getEntityType() {
		return Ingredient.class;
	}

}
