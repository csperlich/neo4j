package nicolecade.recipe.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Ingredient extends DomainObject {

	@Property(name = "ingredient-name")
	private String name;
	
	@Relationship(type = "BELONGS_TO", direction = Relationship.OUTGOING)
	private List<Category> categories;
	
	public Ingredient() {
		categories = new ArrayList<>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addToCategory(Category category) {
		this.categories.add(category);
	}
	
	public void removeFromCategory(Category category) {
		this.categories.remove(this.categories.indexOf(category));
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public List<Category> getCategories() {
		return categories;
	}
}
