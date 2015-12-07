package nicolecade.view;

import nicolecade.recipe.domain.User;

public class MenuSession {
	private static MenuSession INSTANCE;
	private User user;
	private boolean failFlag = false;

	private MenuSession() {

	}

	public static MenuSession singleton() {
		if (INSTANCE == null) {
			INSTANCE = new MenuSession();
		}
		return INSTANCE;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean actionFailed() {
		return this.failFlag;
	}

	public void resetFailFlag() {
		this.failFlag = false;
	}

	public void setFailFlag() {
		this.failFlag = true;
	}

}
