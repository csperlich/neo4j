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
	
	@Property(name = "date")
	@DateString(DATE_FORMAT)
	private Date date;
	
	@Relationship(type = "LEFT_BY", direction = Relationship.OUTGOING)
	private User user;
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
}
