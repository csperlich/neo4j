package nicolecade.recipe.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

@NodeEntity
public abstract class DomainObject {
	public static final String DATE_FORMAT = "yyyy-MM-DD";
	public final static DateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);

	@GraphId
	private Long id;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final DomainObject other = (DomainObject) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public Long getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
