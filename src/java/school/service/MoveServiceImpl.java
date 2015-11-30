package java.school.service;

import java.school.domain.Movie;

public class MoveServiceImpl extends GenericService<Movie>implements MovieService {

	@Override
	public Class<Movie> getEntityType() {
		return Movie.class;
	}

}
