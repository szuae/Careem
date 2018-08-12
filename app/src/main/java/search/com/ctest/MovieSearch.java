package search.com.ctest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import search.com.ctest.browse.movies.detail.MovieDetail;
import search.com.ctest.browse.movies.list.MovieList;
import search.com.ctest.landing.LandingScreen;
import search.com.ctest.model.MovieListUIObject;

public class MovieSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        goToLandingScreen();
    }

    public void goToLandingScreen() {
        this.loadFragment(LandingScreen.newInstance(), FragmentLoadStrategy.add, FragmentAnimationStrategy.slideFromLeft);
    }

    public void goToMovieSearchList() {
        this.loadFragment(MovieList.newInstance(), FragmentLoadStrategy.replace, FragmentAnimationStrategy.fading);
    }

    public void goToMovieDetailScreen(MovieListUIObject obj) {
        MovieDetail detail = MovieDetail.newInstance();
        detail.setData(obj);
        this.loadFragment(detail, FragmentLoadStrategy.replace, FragmentAnimationStrategy.fading);
    }

    public void loadFragment(Fragment fragmentClass, @FragmentLoadStrategy int overwriteStrategy, @FragmentAnimationStrategy int animationStrategy) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        setFragmentAnimation(fragmentTransaction, animationStrategy);
        setFragmentTransactionType(fragmentTransaction, fragmentClass, overwriteStrategy);
        fragmentTransaction.commit();
    }

    private void setFragmentAnimation(FragmentTransaction fragmentTransaction, @FragmentAnimationStrategy int animationStrategy) {
        switch (animationStrategy) {
            case FragmentAnimationStrategy.fading:
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                break;
            case FragmentAnimationStrategy.slideFromLeft:
                fragmentTransaction.setCustomAnimations(R.anim.in_from_left, R.anim.out_to_right);
                break;
        }
    }

    private void setFragmentTransactionType(FragmentTransaction fragmentTransaction, Fragment fragmentClass, @FragmentLoadStrategy int overwriteStrategy) {
        switch (overwriteStrategy) {
            case FragmentLoadStrategy.add:
                fragmentTransaction.add(R.id.container, fragmentClass, fragmentClass.getClass()
                        .getName());
                break;
            case FragmentLoadStrategy.replace:
                fragmentTransaction.addToBackStack(fragmentClass.getClass().getName());
                fragmentTransaction.replace(R.id.container, fragmentClass, fragmentClass.getClass()
                        .getName());
                break;
        }
    }
}
