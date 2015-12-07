package nicolecade.view;

import nicolecade.util.io.UserInput;
import nicolecade.view.MenuBuilder.Menu;

public class MenuNavigator {

	private static MenuNavigator INSTANCE;

	public static MenuNavigator singleton() {
		if (INSTANCE == null) {
			INSTANCE = new MenuNavigator();
		}

		return INSTANCE;
	}

	private MenuNavigator() {
	}

	private void displayWelcomeMessage() {
		System.out.println(Message.WELCOME_MESSAGE);
		System.out.println();
	}

	public void runTheApp() {

		Menu menu = MenuBuilder.singleton().mainMenu();
		
		kickOffDatabaseManagement(menu);

	}

	private int showMenuAndGetInput(final Menu menu) {
		menu.displayMenu();

		final String menuInput = UserInput.singleton().getNextLine();
		final int optionNumber = Integer.parseInt(menuInput);
		return optionNumber;
	}

	public void runDatabaseManager() {
		Menu menu = MenuBuilder.singleton().managementMenu();
		
		kickOffDatabaseManagement(menu);		
	}

	private void kickOffDatabaseManagement(Menu menu) {
		this.displayWelcomeMessage();
		int optionNumberFromUser;

		while (true) {
			optionNumberFromUser = this.showMenuAndGetInput(menu);
			menu = menu.performActionByOptionNumberReturnNextMenu(optionNumberFromUser);
			System.out.println();
		}
	}
}
