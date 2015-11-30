package java.school.domain;

import org.neo4j.ogm.annotation.Property;

public class Category extends DomainObject {
	@Property(name = "name")
	private String name;

	public Category() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
