package nicolecade.view;

public enum Message {
	MENU_DEFAULT_MESSAGE("What would you like to do? "), WELCOME_MESSAGE(
			"\n" + " ++++++++++++++++\n" + " + NEO4J +\n" + " ++++++++++++++++");

	private String message;

	Message(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return this.message;
	}
}
