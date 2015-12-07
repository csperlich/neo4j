package nicolecade.controller;

import java.util.Arrays;

import nicolecade.recipe.domain.Category;
import nicolecade.recipe.domain.Ingredient;
import nicolecade.recipe.domain.Recipe;
import nicolecade.recipe.domain.Review;
import nicolecade.recipe.domain.User;
import nicolecade.recipe.service.UserService;
import nicolecade.util.io.UserInput;
import nicolecade.view.MenuSession;

public enum ActionEnum implements Action {

	EXIT_ACTION {
		@Override
		public void execute() {
			System.out.println("Good bye!");
			System.exit(0);
		}
	},
	LOGOUT {
		@Override
		public void execute() {
			MenuSession.singleton().setUser(null);
		}

	},
	POPULATE_DB {
		@Override
		public void execute() {
			final User nicole = new User();
			nicole.setUsername("narruda");

			final User cade = new User();
			cade.setUsername("csperlich");

			final User krish = new User();
			krish.setUsername("knarayanan");

			final UserService userService = new UserService();
			userService.createOrUpdate(nicole);
			userService.createOrUpdate(cade);
			userService.createOrUpdate(krish);

			final Category starches = new Category();
			starches.setDescription("Starches");

			final Category dairy = new Category();
			dairy.setDescription("Dairy");

			final Category seasonings = new Category();
			seasonings.setDescription("Herbs, spices, and seasonings");

			final Ingredient potatoes = new Ingredient();
			potatoes.setName("Potatoes");
			potatoes.addToCategory(starches);

			final Ingredient butter = new Ingredient();
			butter.setName("Butter");
			butter.addToCategory(dairy);

			final Ingredient salt = new Ingredient();
			salt.setName("Salt");
			salt.addToCategory(seasonings);

			final Ingredient pepper = new Ingredient();
			pepper.setName("Pepper");
			pepper.addToCategory(seasonings);

			final Ingredient cream = new Ingredient();
			cream.setName("Cream");
			cream.addToCategory(dairy);

			final Review review1 = new Review();
			review1.setComment("So light and fluffy! This is the best way to make mashed potatoes.");

			final Review review2 = new Review();
			review2.setComment("Ugh, I can feel my arteries clogging.");

			final Recipe mashedPotatoes = new Recipe();
			mashedPotatoes.setContributor(nicole);
			mashedPotatoes.setIngredients(Arrays.asList(potatoes, butter, salt, pepper, cream));
			mashedPotatoes.setReviews(Arrays.asList(review1, review2));
		}
	},
	USER_LOGIN {

		@Override
		public void execute() {
			final UserInput input = UserInput.singleton();

			System.out.println("Enter a username");
			final String username = input.getNextLine();

			System.out.println("Enter password");
			final String password = input.getNextLine();

			final UserService service = new UserService();
			final User user = service.login(username, password);
			if (user == null) {
				System.out.println("invalid username and/or password");
				MenuSession.singleton().setFailFlag();
			} else {
				MenuSession.singleton().setUser(user);
				System.out.println("Welcome " + user.getUsername());
			}
		}
	};

}
