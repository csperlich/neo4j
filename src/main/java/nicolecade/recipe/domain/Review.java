package nicolecade.recipe.domain;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Review extends DomainObject {

	@Property(name = "comment")
	private String comment;

	@Property(name = "likedIt")
	private boolean likedIt;

	@Relationship(type = "LEFT_BY", direction = Relationship.OUTGOING)
	private User reviewer;

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment() {
		return this.comment;
	}

	public void setLikedIt(boolean likedIt) {
		this.likedIt = likedIt;
	}

	public boolean getLikedIt() {
		return this.likedIt;
	}

	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}

	public User getReviewer() {
		return this.reviewer;
	}
}
