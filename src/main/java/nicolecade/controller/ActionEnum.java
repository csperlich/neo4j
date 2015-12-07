package nicolecade.controller;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.result.Result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nicolecade.recipe.domain.Category;
import nicolecade.recipe.domain.Ingredient;
import nicolecade.recipe.domain.Recipe;
import nicolecade.recipe.domain.Review;
import nicolecade.recipe.domain.User;
import nicolecade.recipe.service.CategoryService;
import nicolecade.recipe.service.IngredientService;
import nicolecade.recipe.service.RecipeService;
import nicolecade.recipe.service.ReviewService;
import nicolecade.recipe.service.UserService;
import nicolecade.util.db.Neo4jSessionFactory;
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

	FOOD_BUDDY_RECOMMENDATIONS {
		@Override
		public void execute() {
			final Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

			final String username = "narruda";
			final String countCondition = "count (r)";
			final String titleAttribute = "title";
			final String recipeLabel = "f";

			final Result result = session.query(
					"match (n:User {username:'" + username
							+ "'})-[FOOD_BUDDIES]-(m:User)<-[:LEFT_BY]-(r:Review {likedIt:true})<-[:HAS_REVIEW]-("
							+ recipeLabel + ":Recipe) return " + recipeLabel + ", " + countCondition,
					Collections.<String, LinkedHashMap> emptyMap());

			final LinkedHashMap<String, Integer> recipeToVotesMap = new LinkedHashMap<>();

			for (final Map<String, Object> row : result) {
				recipeToVotesMap.put((String) ((LinkedHashMap) row.get(recipeLabel)).get(titleAttribute),
						(Integer) row.get(countCondition));
			}

			System.out.println();
			System.out.println("Recommendations from your Food Buddies:");
			System.out.println();
			System.out.println("    RECIPE                     #LIKES");

			final Set<String> recipes = recipeToVotesMap.keySet();
			Integer currentMostVotes;
			String currentBestRecipe = "";
			while (!recipeToVotesMap.isEmpty()) {
				currentMostVotes = new Integer(-1);
				for (final String thisRecipe : recipes) {
					final Integer thisNumberOfVotes = recipeToVotesMap.get(thisRecipe);
					if (thisNumberOfVotes.compareTo(currentMostVotes) > 0) {
						currentMostVotes = thisNumberOfVotes;
						currentBestRecipe = thisRecipe;
					}
				}
				recipeToVotesMap.remove(currentBestRecipe);
				System.out.printf("%-35s%d\n", currentBestRecipe, currentMostVotes);
			}

		}
	},
	GET_BACON_PATH {

		@Override
		public void execute() {
			final UserService service = new UserService();

			// find the baconator
			final User baconator = service.mostLikesForRecipeWithIngredient("Bacon");

			// find the bacon path, remember, always follow the bacon
			final List<String> baconPath = service.getShortestPath(MenuSession.singleton().getUser().getUsername(),
					baconator.getUsername());

			final String sessionUsername = MenuSession.singleton().getUser().getUsername();
			boolean theBaconatorIsYou = false;
			System.out.print("The baconator is ");
			if (baconator.getUsername().equals(sessionUsername)) {
				theBaconatorIsYou = true;
				System.out.print(" YOU ");
			} else {
				System.out.print(baconator.getUsername());
			}

			if (!theBaconatorIsYou) {
				System.out.println();
				if (baconPath.size() > 0) {
					System.out.println("Distance to the baconator = " + baconPath.size());
					System.out.print("Path to the baconator: ");
					System.out.print(baconPath.get(0));
					for (int i = 1; i < baconPath.size(); i++) {
						System.out.print(" --> " + baconPath.get(i));
					}
				} else {
					System.out.print("You are not connected to the baconator");
				}
			}
			System.out.println();
		}

	},
	POPULATE_DB {
		@Override
		public void execute() {

			System.out.println("Populating...");
			System.out.println();

			final ArrayList<User> users = new ArrayList<User>();
			final ArrayList<Category> categories = new ArrayList<Category>();
			final ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
			final ArrayList<Review> reviews = new ArrayList<Review>();
			final ArrayList<Recipe> recipes = new ArrayList<Recipe>();

			final User nicole = this.addUser(users, "narruda");
			final User cade = this.addUser(users, "csperlich");
			final User krish = this.addUser(users, "knarayanan");
			final User celeste = this.addUser(users, "carruda");
			final User healthy = this.addUser(users, "health_nut", "4444");
			final User dad = this.addUser(users, "dad_arruda");
			final User kevin = this.addUser(users, "kevin_baconator");
			nicole.addFoodBuddy(celeste);
			nicole.addFoodBuddy(dad);
			nicole.addFoodBuddy(cade);
			nicole.addFoodBuddy(krish);
			cade.addFoodBuddy(krish);
			krish.addFoodBuddy(dad);
			dad.addFoodBuddy(kevin);

			final Category starches = this.addCategory(categories, "Grains and Starches");
			final Category dairy = this.addCategory(categories, "Dairy products");
			final Category seasonings = this.addCategory(categories, "Herbs, spices, and seasonings");
			final Category produce = this.addCategory(categories, "Fruits and vegetables");
			final Category protein = this.addCategory(categories, "Protein");
			final Category sauce = this.addCategory(categories, "Condiments, Oils, and Sauces");
			final Category pastries = this.addCategory(categories, "Pastries");

			final Ingredient potatoes = this.addIngredient(ingredients, starches, "Potatoes");
			potatoes.addToCategory(produce);
			final Ingredient butter = this.addIngredient(ingredients, dairy, "Butter");
			final Ingredient salt = this.addIngredient(ingredients, seasonings, "Salt");
			final Ingredient pepper = this.addIngredient(ingredients, seasonings, "Black pepper");
			final Ingredient milk = this.addIngredient(ingredients, dairy, "Whole milk");
			final Ingredient yogurt = this.addIngredient(ingredients, dairy, "Yogurt");
			yogurt.addToCategory(protein);
			final Ingredient tomatoes = this.addIngredient(ingredients, produce, "Tomatoes");
			final Ingredient peas = this.addIngredient(ingredients, produce, "Peas");
			final Ingredient onion = this.addIngredient(ingredients, produce, "Onion");
			final Ingredient cumin = this.addIngredient(ingredients, seasonings, "Cumin");
			final Ingredient lemon = this.addIngredient(ingredients, produce, "Lemon juice");
			final Ingredient rice = this.addIngredient(ingredients, starches, "Rice");
			final Ingredient mushrooms = this.addIngredient(ingredients, produce, "Mushrooms");
			final Ingredient egg = this.addIngredient(ingredients, protein, "Egg");
			egg.addToCategory(dairy);

			final Ingredient biBimBopSauce = this.addIngredient(ingredients, sauce, "Bi Bim Bop sauce");
			final Ingredient zucchini = this.addIngredient(ingredients, produce, "Zucchini");
			final Ingredient bacon = this.addIngredient(ingredients, protein, "Bacon");
			final Ingredient jalapeno = this.addIngredient(ingredients, produce, "Jalepeno");
			final Ingredient cheddar = this.addIngredient(ingredients, dairy, "Cheddar Cheese");
			final Ingredient lettuce = this.addIngredient(ingredients, produce, "Lettuce");
			final Ingredient tomato = this.addIngredient(ingredients, produce, "Tomato");
			final Ingredient bread = this.addIngredient(ingredients, starches, "Bread");
			final Ingredient mayo = this.addIngredient(ingredients, sauce, "Mayonnaise");
			mayo.addToCategory(dairy);
			final Ingredient pasta = this.addIngredient(ingredients, starches, "Pasta");
			final Ingredient oliveOil = this.addIngredient(ingredients, sauce, "Olive Oil");
			final Ingredient garlic = this.addIngredient(ingredients, produce, "Garlic");
			final Ingredient dooughnut = this.addIngredient(ingredients, pastries, "Doughnut");

			final boolean LIKE = true;
			final boolean DISLIKE = false;

			final Review potatoReview1 = this.addReview(reviews, celeste,
					"So light and fluffy! This is the best way to make mashed potatoes.", LIKE);
			final Review potatoReview2 = this.addReview(reviews, healthy, "Ugh, I can feel my arteries clogging.",
					DISLIKE);
			final Review potatoReview3 = this.addReview(reviews, cade, "I love potatoes.", LIKE);
			final Review yogurtReview1 = this.addReview(reviews, nicole,
					"Who knew you could make new yogurt from old yogurt?", LIKE);
			final Review mattarPaneerReview1 = this.addReview(reviews, krish, "Very authentic!", LIKE);
			final Review mattarPaneerReview2 = this.addReview(reviews, healthy, "I like that it's vegetarian.", LIKE);
			final Review mattarPaneerReview3 = this.addReview(reviews, celeste, "Too spicy for me.", DISLIKE);
			final Review biBimBopReview1 = this.addReview(reviews, healthy, "This is actually pretty good.", LIKE);
			final Review biBimBopReview2 = this.addReview(reviews, nicole, "Rice + egg = :)", LIKE);
			final Review biBimBopReview3 = this.addReview(reviews, celeste, "Yum!", LIKE);
			final Review baconJalapenosReview1 = this.addReview(reviews, healthy, "Not healthy at all!", DISLIKE);
			final Review baconJalapenosReview2 = this.addReview(reviews, krish, "Yummmm-eee", DISLIKE);
			final Review baconJalapenosReview3 = this.addReview(reviews, kevin, "yesyesyesyesyesyesyes", LIKE);
			final Review bltReview1 = this.addReview(reviews, cade, "Simple, yet effective", LIKE);
			final Review bltReview2 = this.addReview(reviews, celeste, "A classic.", LIKE);
			final Review baconCarbonaraReview1 = this.addReview(reviews, kevin, "You had me at \"Bacon\"", LIKE);
			final Review baconDoughnutReview1 = this.addReview(reviews, kevin, "Bacon Doughnuts for President!", LIKE);

			this.addRecipe(recipes, nicole, "Mashed Potatoes", Arrays.asList(potatoes, butter, salt, pepper, milk),
					Arrays.asList(potatoReview1, potatoReview2, potatoReview3));
			this.addRecipe(recipes, dad, "Homemade Yogurt", Arrays.asList(yogurt, milk), Arrays.asList(yogurtReview1));
			this.addRecipe(recipes, dad, "Mattar Paneer", Arrays.asList(milk, lemon, tomatoes, onion, peas, cumin),
					Arrays.asList(mattarPaneerReview1, mattarPaneerReview2, mattarPaneerReview3));
			this.addRecipe(recipes, cade, "Bi Bim Bop", Arrays.asList(rice, mushrooms, egg, zucchini, biBimBopSauce),
					Arrays.asList(biBimBopReview1, biBimBopReview2, biBimBopReview3));
			this.addRecipe(recipes, cade, "Bacon-Wrapped Cheese-Stuffed Jalepenos",
					Arrays.asList(bacon, cheddar, jalapeno),
					Arrays.asList(baconJalapenosReview1, baconJalapenosReview2, baconJalapenosReview3));
			this.addRecipe(recipes, kevin, "BLT", Arrays.asList(bacon, lettuce, tomato, bread, mayo),
					Arrays.asList(bltReview1, bltReview2));
			this.addRecipe(recipes, dad, "Bacon Carbonara", Arrays.asList(pasta, egg, bacon, oliveOil, garlic),
					Arrays.asList(baconCarbonaraReview1, baconJalapenosReview2));
			this.addRecipe(recipes, celeste, "Glazed Donut with Bacon", Arrays.asList(dooughnut, bacon),
					Arrays.asList(baconDoughnutReview1));

			final UserService userService = new UserService();
			for (final User user : users) {
				userService.createOrUpdate(user);
			}

			final CategoryService categoryService = new CategoryService();
			for (final Category category : categories) {
				categoryService.createOrUpdate(category);
			}

			final IngredientService ingredientService = new IngredientService();
			for (final Ingredient ingredient : ingredients) {
				ingredientService.createOrUpdate(ingredient);
			}

			final ReviewService reviewService = new ReviewService();
			for (final Review review : reviews) {
				reviewService.createOrUpdate(review);
			}

			final RecipeService recipeService = new RecipeService();
			for (final Recipe recipe : recipes) {
				recipeService.createOrUpdate(recipe);
			}

			System.out.println();
			System.out.println("Database populated!");
		}

		private Recipe addRecipe(ArrayList<Recipe> recipes, User contributor, String title,
				List<Ingredient> ingredients, List<Review> reviews) {
			final Recipe recipe = new Recipe();
			recipe.setTitle(title);
			recipe.setContributor(contributor);
			recipe.setIngredients(ingredients);
			recipe.setReviews(reviews);
			recipes.add(recipe);
			return recipe;
		}

		private Review addReview(ArrayList<Review> reviews, User user, String comment, boolean likedIt) {
			final Review review = new Review();
			review.setComment(comment);
			review.setReviewer(user);
			review.setLikedIt(likedIt);
			reviews.add(review);
			return review;

		}

		private Ingredient addIngredient(ArrayList<Ingredient> ingredients, Category category, String name) {
			final Ingredient ingredient = new Ingredient();
			ingredient.setName(name);
			ingredient.addToCategory(category);
			ingredients.add(ingredient);
			return ingredient;
		}

		private Category addCategory(ArrayList<Category> categories, String description) {
			final Category category = new Category();
			category.setDescription(description);
			categories.add(category);
			return category;
		}

		private User addUser(ArrayList<User> users, String... userInfo) {
			final User user = new User();
			user.setUsername(userInfo[0]);

			if (userInfo.length == 2) {
				user.setPassword(userInfo[1]);
			} else {
				user.setPassword("1234");
			}

			users.add(user);
			return user;
		}
	},

	DROP_ALL_DB {
		@Override
		public void execute() {
			System.out.println("Emptying database...");
			System.out.println();

			final Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
			session.query("MATCH (n) DETACH DELETE n", Collections.<String, Object> emptyMap());

			System.out.println();
			System.out.println("Database empty.");

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
	},
	USER_REGISTRATION {

		@Override
		public void execute() {
			final UserInput input = UserInput.singleton();

			System.out.println("Enter a username");
			final String username = input.getNextLine();

			System.out.println("Enter password");
			final String password = input.getNextLine();

			final UserService service = new UserService();
			final User user = service.register(username, password);

			if (user == null) {
				System.out.println("username " + username + " is taken");
				MenuSession.singleton().setFailFlag();
			} else {
				MenuSession.singleton().setUser(user);
				System.out.println("Welcome " + user.getUsername());
			}
		}

	};

}
