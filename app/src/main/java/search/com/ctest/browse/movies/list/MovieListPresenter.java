package search.com.ctest.browse.movies.list;

import android.support.annotation.VisibleForTesting;
import android.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import search.com.ctest.model.ErrorObject;
import search.com.ctest.model.MovieListUIObject;
import search.com.ctest.repository.MovieRepository;


public class MovieListPresenter implements MovieListContract.Presenter {

    @NonNull
    private final MovieRepository repo;

    @android.support.annotation.NonNull
    private io.reactivex.Scheduler mainThread;

    @NonNull
    private MovieListContract.View view;

    CompositeDisposable disposable = new CompositeDisposable();

    public MovieListPresenter(MovieRepository repo, Scheduler scheduler) {
        this.repo = repo;
        mainThread = scheduler;
    }

    @VisibleForTesting
    public void requestData(int pageId) {
        disposable.add(repo.loadMovies(pageId)
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.onLoadingFinished();
                    }
                })
                .subscribe(new Consumer<MovieListUIObject>() {
                               @Override
                               public void accept(MovieListUIObject object) throws Exception {
                                   if (view != null)
                                       view.onSuccess(object);
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable errorObject) throws Exception {
                                if (errorObject instanceof ErrorObject) {
                                    view.onFailure(((ErrorObject) errorObject).getErrorMessage());
                                }
                            }
                        }));
    }


    @Override
    public void clear() {
        disposable.clear();
    }

    @Override
    public void setView(MovieListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadMovies() {
        requestData(1);
    }

    @Override
    public void loadMoreMovies() {
        Pair<Integer, Integer> pair = repo.canLoadMore();
        if (pair.first < pair.second)
            requestData(pair.first + 1);
        else
            view.showMessageForNoMoreData();
    }

    @Override
    public boolean validateDate(String from, String to) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fromDate = sdf.parse(from);
            Date toDate = sdf.parse(to);

            return fromDate.before(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void showFilteredData(String from, String to) {
        disposable.add(getFilteredData(from, to, repo.getCachedData())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.onLoadingFinished();
                    }
                })
                .subscribe(new Consumer<List<MovieListUIObject>>() {
                               @Override
                               public void accept(List<MovieListUIObject> object) throws Exception {
                                   if (view != null)
                                       view.onSuccess(object);
                               }
                           }
                        , new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable errorObject) throws Exception {
                                if (errorObject instanceof ErrorObject) {
                                    view.onFailure(((ErrorObject) errorObject).getErrorMessage());
                                }
                            }
                        }));
    }

    @VisibleForTesting
    public Observable<List<MovieListUIObject>> getFilteredData(final String from, final String to, final List<MovieListUIObject> data) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return Observable.create(new ObservableOnSubscribe<List<MovieListUIObject>>() {
            @Override
            public void subscribe(ObservableEmitter<List<MovieListUIObject>> emitter) throws Exception {
                List<MovieListUIObject> result = new ArrayList<>();
                try {
                    final Date afterDate = sdf.parse(from);
                    final Date beforeDate = sdf.parse(to);

                    for (MovieListUIObject obj : data) {
                        if (sdf.parse(obj.getRelease_date()).after(afterDate)
                                && sdf.parse(obj.getRelease_date()).before(beforeDate)) {
                            result.add(obj);
                        }
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                emitter.onNext(result);
            }
        }).observeOn(mainThread);
    }
}
