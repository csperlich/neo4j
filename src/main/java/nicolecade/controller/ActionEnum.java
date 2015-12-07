package nicolecade.controller;

import java.util.Arrays;

import nicolecade.recipe.domain.Category;
import nicolecade.recipe.domain.Ingredient;
import nicolecade.recipe.domain.Recipe;
import nicolecade.recipe.domain.Review;
import nicolecade.recipe.domain.User;
import nicolecade.recipe.service.UserService;

public enum ActionEnum implements Action {
	
	POPULATE_DB {
		@Override
		public void execute() {
			User nicole = new User();
			nicole.setUsername("narruda");
			
			User cade = new User();
			cade.setUsername("csperlich");
			
			User krish = new User();
			krish.setUsername("knarayanan");
			
			UserService userService = new UserService();
			userService.createOrUpdate(nicole);
			userService.createOrUpdate(cade);
			userService.createOrUpdate(krish);

			Category starches = new Category();
			starches.setDescription("Starches");
			
			Category dairy = new Category();
			dairy.setDescription("Dairy");
			
			Category seasonings = new Category();
			seasonings.setDescription("Herbs, spices, and seasonings");
			
			Ingredient potatoes = new Ingredient();
			potatoes.setName("Potatoes");
			potatoes.addToCategory(starches);
			
			Ingredient butter = new Ingredient();
			butter.setName("Butter");
			butter.addToCategory(dairy);
			
			Ingredient salt = new Ingredient();
			salt.setName("Salt");
			salt.addToCategory(seasonings);

			Ingredient pepper = new Ingredient();
			pepper.setName("Pepper");
			pepper.addToCategory(seasonings);
			
			Ingredient cream = new Ingredient();
			cream.setName("Cream");
			cream.addToCategory(dairy);
			
			Review review1 = new Review();
			review1.setComment("So light and fluffy! This is the best way to make mashed potatoes.");
			
			Review review2 = new Review();
			review2.setComment("Ugh, I can feel my arteries clogging.");

			Recipe mashedPotatoes = new Recipe();
			mashedPotatoes.setContributor(nicole);
			mashedPotatoes.setIngredients(Arrays.asList(potatoes, butter, salt, pepper, cream));
			mashedPotatoes.setReviews(Arrays.asList(review1, review2));
		}
	},
	EXIT_ACTION {
		@Override
		public void execute() {
			System.out.println("Good bye!");
			System.exit(0);
		}

	};

}
