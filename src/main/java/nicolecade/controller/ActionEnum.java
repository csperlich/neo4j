package nicolecade.controller;

import java.text.ParseException;
import java.util.Date;

import nicolecade.movie.domain.DomainObject;
import nicolecade.movie.domain.Person;
import nicolecade.movie.service.CustomService;
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
					System.out.print("Enter Birthday (" + DomainObject.DATE_FORMAT + "): ");
					birthday = DomainObject.dateFormatter.parse(UserInput.singleton().getNextLine());
					badDate = false;
				} catch (final ParseException e) {
					System.out.println("Bad date, try again.");
				}
			}
			final Person person = new Person();
			person.setName(name);
			person.setBirthday(123);

			final PersonServiceImpl personService = new PersonServiceImpl();
			personService.createOrUpdate(person);
		}
	},
	CUSTOM_GET_PERSONS {

		@Override
		public void execute() {
			final CustomService service = new CustomService();
			service.customGetPersons();
		}

	},
	CUSTOM_SHORTEST_PATH {

		@Override
		public void execute() {
			final CustomService service = new CustomService();
			service.shortestPath();

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
