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

			return pickedOption.menuToShowNext();
		}
	}

	private static MenuBuilder INSTANCE;

	private static Menu MAIN_MENU;

	public static MenuBuilder singleton() {
		if (INSTANCE == null) {
			INSTANCE = new MenuBuilder();
		}
		return INSTANCE;
	}

	private MenuBuilder() {

	}

	private void buildMainMenu() {
		MAIN_MENU = MenuBuilder.singleton().new Menu("MAIN MENU", Message.MENU_DEFAULT_MESSAGE);
		final ArrayList<MenuOption> mainMenuOptions = new ArrayList<>();
		mainMenuOptions.add(new MenuOption("EXIT", ActionEnum.EXIT_ACTION, MAIN_MENU));

		MAIN_MENU.addOptionsToMenu(mainMenuOptions);
	}

	public Menu mainMenu() {
		if (MAIN_MENU == null) {
			this.buildMainMenu();
		}
		return MAIN_MENU;
	}

}
