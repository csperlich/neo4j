package nicolecade.recipe.service;

import org.neo4j.ogm.session.Session;

import nicolecade.recipe.domain.DomainObject;
import nicolecade.util.db.Neo4jSessionFactory;

public abstract class GenericService<T> implements Service<T> {

	private static final int DEPTH_ENTITY = 1;
	private static final int DEPTH_LIST = 0;
	private final Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

	@Override
	public T createOrUpdate(T domainObject) {
		this.session.save(domainObject, DEPTH_ENTITY);
		return this.find(((DomainObject) domainObject).getId());
	}

	@Override
	public void delete(Long id) {
		this.session.delete(this.session.load(this.getEntityType(), id));
	}

	@Override
	public T find(Long id) {
		return this.session.load(this.getEntityType(), id, DEPTH_ENTITY);
	}

	@Override
	public Iterable<T> findAll() {
		return this.session.loadAll(this.getEntityType(), DEPTH_LIST);
	}

	public abstract Class<T> getEntityType();
}
