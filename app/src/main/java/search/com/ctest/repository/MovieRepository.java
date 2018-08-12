package search.com.ctest.repository;


import android.util.Pair;

import java.util.List;

import io.reactivex.Observable;
import search.com.ctest.model.MovieListUIObject;


public interface MovieRepository {
    Observable<MovieListUIObject> loadMovies(int pageId);

    Pair<Integer, Integer> canLoadMore();

    List<MovieListUIObject> getCachedData();
}
