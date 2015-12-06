package nicolecade.recipe.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class Category extends DomainObject {

	@Property(name = "description")
	private String description;
	
	public void setName(String name) {
		this.description = name;
	}
	
	public String getName() {
		return description;
	}
	
}
