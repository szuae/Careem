// Generated by Dagger (https://google.github.io/dagger).
package search.com.ctest.browse.movies.list;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class MovieList_MembersInjector implements MembersInjector<MovieList> {
  private final Provider<MovieListContract.Presenter> presenterProvider;

  public MovieList_MembersInjector(Provider<MovieListContract.Presenter> presenterProvider) {
    this.presenterProvider = presenterProvider;
  }

  public static MembersInjector<MovieList> create(
      Provider<MovieListContract.Presenter> presenterProvider) {
    return new MovieList_MembersInjector(presenterProvider);
  }

  @Override
  public void injectMembers(MovieList instance) {
    injectPresenter(instance, presenterProvider.get());
  }

  public static void injectPresenter(MovieList instance, MovieListContract.Presenter presenter) {
    instance.presenter = presenter;
  }
}
