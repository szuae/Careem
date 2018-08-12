package search.com.ctest;

import android.app.Application;

import search.com.ctest.di.DaggerMovieSearchApplicationComponent;
import search.com.ctest.di.MovieSearchApplicationComponent;


public class MovieSearchApplication extends Application {

    private MovieSearchApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerMovieSearchApplicationComponent.builder().build();
    }

    public MovieSearchApplicationComponent getComponent() {
        return component;
    }

}
