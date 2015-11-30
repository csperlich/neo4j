package nicolecade.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nicolecade.movie.domain.Person;
import nicolecade.movie.service.PersonServiceImpl;
import nicolecade.util.io.UserInput;

public enum ActionEnum implements Action {
	ADD_PERSON {
		@Override
		public void execute() {
			System.out.print("Enter Name: ");
			final String name = UserInput.singleton().getNextLine();

			Date birthday = null;
			boolean badDate = true;
			while (badDate) {
				try {
					System.out.print("Enter Birthday (\"yyyy-MM-dd\"): ");
					birthday = dateFormatter.parse(UserInput.singleton().getNextLine());
					badDate = false;
				} catch (final ParseException e) {
					System.out.println("Bad date, try again.");
				}
			}
			final Person person = new Person();
			person.setName(name);
			person.setBirthday(birthday);
			final PersonServiceImpl personService = new PersonServiceImpl();
			personService.createOrUpdate(person);
		}
	},
	EXIT_ACTION {
		@Override
		public void execute() {
			System.out.println("Good bye!");
			System.exit(0);
		}

	};

	private static DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

}
