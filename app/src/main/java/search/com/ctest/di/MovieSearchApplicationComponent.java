package search.com.ctest.di;


import javax.inject.Singleton;

import dagger.Component;
import search.com.ctest.browse.movies.list.MovieList;

@Singleton
@Component(modules = {MovieSearchApplicationModule.class})
public interface MovieSearchApplicationComponent {

    void inject(MovieList movieList);

}
