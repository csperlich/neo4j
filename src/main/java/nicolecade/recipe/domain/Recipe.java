package nicolecade.recipe.domain;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Recipe extends DomainObject {
	
	@Property(name = "title")
	private String title;
	
	@Relationship(type = "COMPRISED_OF", direction = Relationship.OUTGOING)
	private List<Ingredient> ingredients;
	
	@Relationship(type = "HAS_REVIEW", direction = Relationship.OUTGOING)
	private List<Review> reviews;
	
	@Relationship(type = "CONTRIBUTED_BY", direction = Relationship.OUTGOING)
	private User contributor;
	
	public Recipe() {
		
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void addIngredient(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
	
	public void removeIngredient(Ingredient ingredient) {
		this.ingredients.remove(this.ingredients.indexOf(ingredient));
	}
	
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	
	public void addReview(Review review) {
		this.reviews.add(review);
	}
	
	public void removeReview(Review review) {
		this.reviews.remove(this.reviews.indexOf(review));
	}
	
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public void setContributor(User contributor) {
		this.contributor = contributor;
	}
	
	public User getContributor() {
		return contributor;
	}
	
}
