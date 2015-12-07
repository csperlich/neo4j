package nicolecade.recipe.domain;

import java.util.Date;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

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
		return comment;
	}
	
	public void setLikedIt(boolean likedIt) {
		this.likedIt = likedIt;
	}
	
	public boolean getLikedIt() {
		return likedIt;
	}
	
	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}
	
	public User getReviewer() {
		return reviewer;
	}
}
