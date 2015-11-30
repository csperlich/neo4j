package nicolecade.util.io;

import java.util.Scanner;

public class UserInput {
	private static UserInput INSTANCE;

	public static UserInput singleton() {
		if (INSTANCE == null) {
			INSTANCE = new UserInput();
		}
		return INSTANCE;

	}

	private final Scanner keyboard;

	private UserInput() {
		this.keyboard = new Scanner(System.in);
	}

	public String getNextLine() {
		return this.keyboard.nextLine();
	}
}