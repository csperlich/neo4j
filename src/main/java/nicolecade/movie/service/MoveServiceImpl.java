package nicolecade.movie.service;

import nicolecade.movie.domain.Movie;

public class MoveServiceImpl extends GenericService<Movie>implements MovieService {

	@Override
	public Class<Movie> getEntityType() {
		return Movie.class;
	}

}
