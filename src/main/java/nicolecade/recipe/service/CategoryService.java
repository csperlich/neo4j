package nicolecade.recipe.service;

import nicolecade.recipe.domain.Category;

public class CategoryService extends GenericService<Category> {
	@Override
	public Class<Category> getEntityType() {
		return Category.class;
	}
}
