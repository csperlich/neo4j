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

	private static MenuBuilder INSTANCE;
	private static Menu MAIN_MENU;
	private static Menu MANAGER_MENU;
	private static Menu USER_MENU;

	public static MenuBuilder singleton() {
		if (INSTANCE == null) {
			INSTANCE = new MenuBuilder();
		}
		return INSTANCE;
	}

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

	private MenuBuilder() {

	}

	private void buildMainMenu() {
		MAIN_MENU = MenuBuilder.singleton().new Menu("MAIN MENU", Message.MENU_DEFAULT_MESSAGE);
		final ArrayList<MenuOption> mainMenuOptions = new ArrayList<>();
		mainMenuOptions.add(new MenuOption("LOGIN", ActionEnum.USER_LOGIN, this.userMenu()));
		mainMenuOptions.add(new MenuOption("EXIT", ActionEnum.EXIT_ACTION, MAIN_MENU));

		MAIN_MENU.addOptionsToMenu(mainMenuOptions);
	}

	private void buildManagerMenu() {
		MANAGER_MENU = MenuBuilder.singleton().new Menu("MANAGE DATABASE", Message.MENU_DEFAULT_MESSAGE);
		final ArrayList<MenuOption> mgmtMenuOptions = new ArrayList<>();

		mgmtMenuOptions.add(new MenuOption("Populate database", ActionEnum.POPULATE_DB, MANAGER_MENU));
		mgmtMenuOptions.add(new MenuOption("Empty database", ActionEnum.DROP_ALL_DB, MANAGER_MENU));
		mgmtMenuOptions.add(new MenuOption("EXIT", ActionEnum.EXIT_ACTION, MANAGER_MENU));

		MANAGER_MENU.addOptionsToMenu(mgmtMenuOptions);
	}

	private void buildUserMenu() {
		USER_MENU = MenuBuilder.singleton().new Menu("MAIN MENU", Message.MENU_DEFAULT_MESSAGE);
		final ArrayList<MenuOption> userMenuOptions = new ArrayList<>();
		userMenuOptions.add(new MenuOption("EXIT", ActionEnum.EXIT_ACTION, MAIN_MENU));
		userMenuOptions.add(new MenuOption("LOGOUT", ActionEnum.LOGOUT, MAIN_MENU));

		USER_MENU.addOptionsToMenu(userMenuOptions);

	}

}
