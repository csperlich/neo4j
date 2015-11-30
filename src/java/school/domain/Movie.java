package java.school.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;
import java.util.List;

@NodeEntity
public class Movie extends DomainObject {
	@Relationship(type = "HAS_CATEGORY", direction = Relationship.OUTGOING)
	private List<Category> categories;

	@DateString("yy-MM-dd")
	@Property(name = "release_date")
	private Date releaseDate;

	@Property(name = "tagline")
	private String tagline;

	@Property(name = "title")
	private String title;

	public Movie() {
	}

	public void addCategory(Category category) {
		this.categories.add(category);
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public String getTagline() {
		return this.tagline;
	}

	public String getTitle() {
		return this.title;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
