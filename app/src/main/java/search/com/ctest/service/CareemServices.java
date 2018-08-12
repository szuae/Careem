package search.com.ctest.service;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import search.com.ctest.repository.dto.MovieListResponseDTO;

public interface CareemServices {

    @GET("discover/movie")
    Single<MovieListResponseDTO> fetchMovies(@Query("api_key") String apiKey, @Query("page") int pageId, @Query("language") String language);
}
