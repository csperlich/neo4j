package nicolecade.movie.service;

import org.neo4j.ogm.session.Session;

import nicolecade.movie.domain.DomainObject;
import nicolecade.util.db.Neo4jSessionFactory;

/*
 * Copyright (c)  [2011-2015] "Neo Technology" / "Graph Aware Ltd."
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with separate copyright notices and license terms. Your use of the source code for these subcomponents is subject to the terms and conditions of the subcomponent's license, as noted in the LICENSE file.
 *
 *
 */
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
