package search.com.ctest.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import search.com.ctest.browse.movies.list.MovieListContract;
import search.com.ctest.browse.movies.list.MovieListPresenter;
import search.com.ctest.network.NetworkLayer;
import search.com.ctest.repository.MovieRepository;
import search.com.ctest.repository.MovieRepositoryImpl;
import search.com.ctest.service.CareemServices;

@Module
public class MovieSearchApplicationModule {

    @Provides
    @Singleton
    public MovieListContract.Presenter providePresenter(MovieRepository repo) {
        return new MovieListPresenter(repo, AndroidSchedulers.mainThread());
    }


    @Singleton
    @Provides
    public MovieRepository provideDataRepository(CareemServices service) {
        return new MovieRepositoryImpl(service, Schedulers.io(), AndroidSchedulers.mainThread());
    }

    @Provides
    public CareemServices provideService(NetworkLayer networkLayer) {
        return networkLayer.createApiService(CareemServices.class);
    }

    @Provides
    public NetworkLayer provideNetworkLayer() {
        return new NetworkLayer();
    }

}
