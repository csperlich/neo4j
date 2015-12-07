package nicolecade.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

public enum ActionEnum implements Action {
	
	POPULATE_DB {
		@Override
		public void execute() {
			
			ArrayList<User> users = new ArrayList<User>();
			ArrayList<Category> categories = new ArrayList<Category>();
			ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
			ArrayList<Review> reviews = new ArrayList<Review>();
			ArrayList<Recipe> recipes = new ArrayList<Recipe>();
			
			User nicole = addUser(users, "narruda");
			User cade = addUser(users, "csperlich");
			User krish = addUser(users, "knarayanan");
			User celeste = addUser(users, "carruda");
			User healthy = addUser(users, "health_nut");
			User dad = addUser(users, "dad_arruda");

			Category starches = addCategory(categories, "Starches");
			Category dairy = addCategory(categories, "Dairy products");
			Category seasonings = addCategory(categories, "Herbs, spices, and seasonings");			
			Category produce = addCategory(categories, "Fruits and vegetables");
			Category protein = addCategory(categories, "Protein");

			Ingredient potatoes = addIngredient(ingredients, starches, "Potatoes");
			potatoes.addToCategory(produce);
			Ingredient butter = addIngredient(ingredients, dairy, "Butter");
			Ingredient salt = addIngredient(ingredients, seasonings, "Salt");
			Ingredient pepper = addIngredient(ingredients, seasonings, "Black pepper");
			Ingredient milk = addIngredient(ingredients, dairy, "Whole milk");
			Ingredient yogurt = addIngredient(ingredients, dairy, "Yogurt");
			yogurt.addToCategory(protein);
			Ingredient tomatoes = addIngredient(ingredients, produce, "Tomatoes");
			Ingredient peas = addIngredient(ingredients, produce, "Peas");
			Ingredient onion = addIngredient(ingredients, produce, "Onion");
			Ingredient cumin = addIngredient(ingredients, seasonings, "Cumin");
			Ingredient lemon = addIngredient(ingredients, produce, "Lemon juice");
			Ingredient rice = addIngredient(ingredients, starches, "Rice");
			Ingredient mushrooms = addIngredient(ingredients, produce, "Mushrooms");
			Ingredient egg = addIngredient(ingredients, protein, "Egg");
			egg.addToCategory(dairy);
			Ingredient biBimBopSauce = addIngredient(ingredients, seasonings, "Bi Bim Bop sauce");
			Ingredient zucchini = addIngredient(ingredients, produce, "Zucchini");
			
			Review potatoReview1 = addReview(reviews, celeste, "So light and fluffy! This is the best way to make mashed potatoes.");
			Review potatoReview2 = addReview(reviews, healthy, "Ugh, I can feel my arteries clogging.");
			Review yogurtReview1 = addReview(reviews, nicole, "Who knew you could make new yogurt from old yogurt?");
			Review mattarPaneerReview1 = addReview(reviews, krish, "Very authentic!");
			Review mattarPaneerReview2 = addReview(reviews, healthy, "I like that it's vegetarian.");
			Review mattarPaneerReview3 = addReview(reviews, celeste, "Too spicy for me.");
			Review biBimBopReview1 = addReview(reviews, healthy, "This is actually pretty good.");
			Review biBimBopReview2 = addReview(reviews, nicole, "Rice + egg = :)");
			
			addRecipe(recipes, nicole, "Mashed Potatoes",
					Arrays.asList(potatoes, butter, salt, pepper, milk),
					Arrays.asList(potatoReview1, potatoReview2));
			addRecipe(recipes, dad, "Homemade Yogurt",
					Arrays.asList(yogurt, milk),
					Arrays.asList(yogurtReview1));
			addRecipe(recipes, dad, "Mattar Paneer",
					Arrays.asList(milk, lemon, tomatoes, onion, peas, cumin),
					Arrays.asList(mattarPaneerReview1, mattarPaneerReview2, mattarPaneerReview3));
			addRecipe(recipes, cade, "Bi Bim Bop", 
					Arrays.asList(rice, mushrooms, egg, zucchini, biBimBopSauce),
					Arrays.asList(biBimBopReview1, biBimBopReview2));
			
			UserService userService = new UserService();
			for (User user : users) {
				userService.createOrUpdate(user);
			}
			
			CategoryService categoryService = new CategoryService();
			for (Category category : categories) {
				categoryService.createOrUpdate(category);
			}
			
			IngredientService ingredientService = new IngredientService();
			for (Ingredient ingredient : ingredients) {
				ingredientService.createOrUpdate(ingredient);
			}
			
			ReviewService reviewService = new ReviewService();
			for (Review review : reviews) {
				reviewService.createOrUpdate(review);
			}
			
			RecipeService recipeService = new RecipeService();
			for (Recipe recipe : recipes) {
				recipeService.createOrUpdate(recipe);
			}
		}

		private Recipe addRecipe(ArrayList<Recipe> recipes, User contributor, String title, List<Ingredient> ingredients,
				List<Review> reviews) {
			Recipe mashedPotatoes = new Recipe();
			mashedPotatoes.setTitle(title);
			mashedPotatoes.setContributor(contributor);
			mashedPotatoes.setIngredients(ingredients);
			mashedPotatoes.setReviews(reviews);
			recipes.add(mashedPotatoes);
			return mashedPotatoes;
		}

		private Review addReview(ArrayList<Review> reviews, User user, String comment) {
			Review potatoReview1 = new Review();
			potatoReview1.setComment(comment);
			potatoReview1.setReviewer(user);
			reviews.add(potatoReview1);
			return potatoReview1;
		}

		private Ingredient addIngredient(ArrayList<Ingredient> ingredients, Category category, String name) {
			Ingredient potatoes = new Ingredient();
			potatoes.setName(name);
			potatoes.addToCategory(category);
			ingredients.add(potatoes);
			return potatoes;
		}

		private Category addCategory(ArrayList<Category> categories, String description) {
			Category starches = new Category();
			starches.setDescription(description);
			categories.add(starches);
			return starches;
		}

		private User addUser(ArrayList<User> users, String username) {
			User nicole = new User();
			nicole.setUsername(username);
			users.add(nicole);
			return nicole;
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
