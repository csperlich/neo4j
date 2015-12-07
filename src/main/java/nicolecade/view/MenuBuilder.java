package nicolecade.view;

import java.util.ArrayList;
import java.util.List;

import nicolecade.controller.ActionEnum;

public class MenuBuilder {

	public class Menu {
		private final Message message;
		private final List<MenuOption> options;
		private final String title;

		private Menu(final String title, final Message message) {
			this.options = new ArrayList<MenuOption>();
			this.title = title;
			this.message = message;
		}

		private void addOption(final MenuOption option) {
			this.options.add(option);
		}

		private void addOptionsToMenu(final ArrayList<MenuOption> menuOptions) {
			int optionNumber = 1;
			for (final MenuOption option : menuOptions) {
				option.setOptionNumber(optionNumber++);
				this.addOption(option);
			}
		}

		public void displayMenu() {
			System.out.println(this.title);

			for (final MenuOption option : this.options) {
				System.out.println(" " + option.getOptionNumber() + " " + option.getOptionText());
			}
			System.out.println();
			System.out.print(this.message);
		}

		private MenuOption findOptionByNumber(final int optionNumber) {
			MenuOption pickedOption = null;
			for (final MenuOption option : this.options) {
				if (option.getOptionNumber() == optionNumber) {
					pickedOption = option;
					break;
				}
			}
			return pickedOption;
		}

		public Menu performActionByOptionNumberReturnNextMenu(final int optionNumber) {
			final MenuOption pickedOption = this.findOptionByNumber(optionNumber);

			pickedOption.performAction();
			if (MenuSession.singleton().actionFailed()) {
				MenuSession.singleton().resetFailFlag();
				return this;
			}
			return pickedOption.menuToShowNext();
		}
	}

	public static MenuBuilder singleton() {
		if (INSTANCE == null) {
			INSTANCE = new MenuBuilder();
		}
		return INSTANCE;
	}

	private static MenuBuilder INSTANCE;
	private static Menu MAIN_MENU;
	private static Menu MANAGER_MENU;
	private static Menu USER_MENU;
	private static Menu RECIPE_MENU;

	public Menu mainMenu() {
		if (MAIN_MENU == null) {
			System.out.println("built");
			this.buildMainMenu();
		}
		return MAIN_MENU;
	}

	public Menu managementMenu() {
		if (MANAGER_MENU == null) {
			this.buildManagerMenu();
		}
		return MANAGER_MENU;
	}

	public Menu userMenu() {
		if (USER_MENU == null) {
			this.buildUserMenu();
		}
		return USER_MENU;
	}

	public Menu recipeMenu() {
		if (RECIPE_MENU == null) {
			this.buildRecipeMenu();
		}
		return RECIPE_MENU;
	}

	private MenuBuilder() {

	}

	private void buildMainMenu() {
		MAIN_MENU = MenuBuilder.singleton().new Menu("MAIN MENU", Message.MENU_DEFAULT_MESSAGE);
		final ArrayList<MenuOption> mainMenuOptions = new ArrayList<>();

		mainMenuOptions.add(new MenuOption("Log in", ActionEnum.USER_LOGIN, this.userMenu()));
		mainMenuOptions
				.add(new MenuOption("Register", ActionEnum.USER_REGISTRATION, this.userMenu()));
		mainMenuOptions.add(new MenuOption("EXIT", ActionEnum.EXIT_ACTION, this.mainMenu()));

		MAIN_MENU.addOptionsToMenu(mainMenuOptions);
	}

	private void buildManagerMenu() {
		MANAGER_MENU = MenuBuilder.singleton().new Menu("MANAGE DATABASE",
				Message.MENU_DEFAULT_MESSAGE);
		final ArrayList<MenuOption> mgmtMenuOptions = new ArrayList<>();

		mgmtMenuOptions.add(
				new MenuOption("Populate database", ActionEnum.POPULATE_DB, this.managementMenu()));
		mgmtMenuOptions.add(
				new MenuOption("Empty database", ActionEnum.DROP_ALL_DB, this.managementMenu()));
		mgmtMenuOptions.add(new MenuOption("EXIT", ActionEnum.EXIT_ACTION, this.managementMenu()));

		MANAGER_MENU.addOptionsToMenu(mgmtMenuOptions);
	}

	private void buildUserMenu() {
		USER_MENU = MenuBuilder.singleton().new Menu("USER MENU", Message.MENU_DEFAULT_MESSAGE);
		final ArrayList<MenuOption> userMenuOptions = new ArrayList<>();

		userMenuOptions.add(new MenuOption("See recommendations from your Food Buddies",
				ActionEnum.FOOD_BUDDY_RECOMMENDATIONS, this.userMenu()));
		userMenuOptions.add(new MenuOption("Recipe menu", ActionEnum.NO_ACTION, this.recipeMenu()));
		userMenuOptions.add(new MenuOption("Log out", ActionEnum.LOGOUT, this.mainMenu()));
		userMenuOptions.add(new MenuOption("EXIT", ActionEnum.EXIT_ACTION, this.mainMenu()));

		USER_MENU.addOptionsToMenu(userMenuOptions);

	}

	private void buildRecipeMenu() {
		RECIPE_MENU = MenuBuilder.singleton().new Menu("RECIPES", Message.MENU_DEFAULT_MESSAGE);
		ArrayList<MenuOption> recipeMenuOptions = new ArrayList<>();

		recipeMenuOptions.add(
				new MenuOption("Show all recipes", ActionEnum.SHOW_ALL_RECIPES, this.recipeMenu()));
		recipeMenuOptions.add(new MenuOption("See reviews for a recipe",
				ActionEnum.SHOW_REVIEWS_FOR_RECIPE, this.recipeMenu()));
		recipeMenuOptions.add(new MenuOption("Go back", ActionEnum.NO_ACTION, this.userMenu()));

		RECIPE_MENU.addOptionsToMenu(recipeMenuOptions);
	}

}
