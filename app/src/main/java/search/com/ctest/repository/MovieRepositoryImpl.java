package search.com.ctest.repository;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import search.com.ctest.BuildConfig;
import search.com.ctest.model.ErrorObject;
import search.com.ctest.model.MovieListUIObject;
import search.com.ctest.repository.dto.MovieListResponseDTO;
import search.com.ctest.service.CareemServices;

public class MovieRepositoryImpl implements MovieRepository {

    @NonNull
    private final CareemServices services;
    @NonNull
    private final Scheduler backgroundThread;
    @NonNull
    private final io.reactivex.Scheduler mainThread;

    private int currentPage;
    private int totalAvailPages;

    private List<MovieListUIObject> data = new ArrayList<>();

    public MovieRepositoryImpl(CareemServices services, Scheduler backgroundThread, Scheduler mainThread) {
        this.services = services;
        this.backgroundThread = backgroundThread;
        this.mainThread = mainThread;
    }

    public void request(int pageId, final ObservableEmitter<MovieListUIObject> emitter) {

        services.fetchMovies(BuildConfig.apiKey, pageId, "en-US")
                .subscribeOn(backgroundThread)
                .subscribe(new Consumer<MovieListResponseDTO>() {
                    @Override
                    public void accept(MovieListResponseDTO movieListResponseDTO) throws Exception {
                        updateDataConsumed(movieListResponseDTO);
                        parseMovieListToUIEntity(movieListResponseDTO, emitter);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        emitter.onError(new ErrorObject.Builder()
                                .errorMessage("Invalid Session ! Renew your session again")
                                .build());
                    }
                });
    }

    private void updateDataConsumed(MovieListResponseDTO dto) {
        currentPage = dto.page;
        totalAvailPages = dto.total_pages;
    }

    private void parseMovieListToUIEntity(MovieListResponseDTO movieListResponseDTO, Emitter<MovieListUIObject> emitter) {
        for (MovieListResponseDTO.Results movieObj : movieListResponseDTO.results) {
            MovieListUIObject object = new MovieListUIObject.Builder()
                    .backdrop_path(movieObj.backdrop_path)
                    .id(movieObj.id)
                    .overview(movieObj.overview)
                    .poster_path(movieObj.poster_path)
                    .release_date(movieObj.release_date)
                    .title(movieObj.title)
                    .video(movieObj.video)
                    .average(movieObj.vote_average)
                    .build();
            data.add(object);
            emitter.onNext(object);
        }
    }

    @Override
    public Observable<MovieListUIObject> loadMovies(final int pageId) {
        return Observable.create(new ObservableOnSubscribe<MovieListUIObject>() {
            @Override
            public void subscribe(ObservableEmitter<MovieListUIObject> emitter) throws Exception {
                request(pageId, emitter);
            }
        }).observeOn(mainThread);
    }

    @Override
    public Pair<Integer, Integer> canLoadMore() {
        return Pair.create(currentPage, totalAvailPages);
    }

    @Override
    public List<MovieListUIObject> getCachedData() {
        return data;
    }
}

