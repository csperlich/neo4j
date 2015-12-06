package nicolecade.recipe.service;

import nicolecade.recipe.domain.Review;

public class ReviewService extends GenericService<Review> {
	@Override
	public Class<Review> getEntityType() {
		return Review.class;
	}
}
