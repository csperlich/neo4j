package nicolecade.view;

import nicolecade.controller.Action;
import nicolecade.view.MenuBuilder.Menu;

public class MenuOption {
	private final Action action;
	private final Menu menuToShowNext;
	private final String option;
	private int optionNumber;

	public MenuOption(final String option, final Action action, final Menu menuToShowNext) {
		this.option = option;
		this.action = action;
		this.menuToShowNext = menuToShowNext;
	}

	public int getOptionNumber() {
		return this.optionNumber;
	}

	public String getOptionText() {
		return this.option;
	}

	public Menu menuToShowNext() {
		return this.menuToShowNext;
	}

	public void performAction() {
		this.action.execute();
	}

	public void setOptionNumber(final int optionNumber) {
		this.optionNumber = optionNumber;
	}
}
