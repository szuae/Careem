package search.com.ctest.browse.movies.list;

import java.util.List;

import search.com.ctest.model.MovieListUIObject;


public interface MovieListContract {
    interface View {
        void onSuccess(MovieListUIObject object);

        void onSuccess(List<MovieListUIObject> object);

        void onFailure(String errorMessage);

        void onLoadingFinished();

        void onItemClicked(MovieListUIObject obj);

        void showMessageForNoMoreData();
    }

    interface Presenter {

        void clear();

        void setView(View view);

        void loadMovies();

        void loadMoreMovies();

        boolean validateDate(String from, String to);

        void showFilteredData(String from, String to);
    }

}
