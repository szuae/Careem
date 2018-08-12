package search.com.ctest.browse.movies.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.Schedulers;
import search.com.ctest.model.MovieListUIObject;
import search.com.ctest.repository.MovieRepository;
import search.com.ctest.repository.MovieRepositoryImpl;
import search.com.ctest.service.CareemServices;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieListPresenterTest {

    MovieListPresenter presenter;

    @Mock
    MovieRepository repo;


    @Mock
    CareemServices services;

    @Mock
    MovieList view;

    @Before
    public void setup() {
        repo = new MovieRepositoryImpl(services, Schedulers.trampoline(), Schedulers.trampoline());
        presenter = Mockito.spy(new MovieListPresenter(repo, Schedulers.trampoline()));
        presenter.setView(view);
    }

    @Test
    public void testValidate_NagativeTest() {
        String from = "2018-02-01";
        String to = "2018-01-01";
        Assert.assertFalse(presenter.validateDate(from, to));

        from = "";
        to = "";
        Assert.assertFalse(presenter.validateDate(from, to));

        from = "";
        to = "2018-01-01";
        Assert.assertFalse(presenter.validateDate(from, to));

        to = "";
        from = "2018-01-01";
        Assert.assertFalse(presenter.validateDate(from, to));
    }

    @Test
    public void loadDataTest() {
        presenter.loadMovies();
        verify(presenter, Mockito.times(1)).requestData(any(Integer.class));
    }

    @Test
    public void testValidate_PositiveTest() {
        String to = "2018-02-01";
        String from = "2018-01-01";
        Assert.assertTrue(presenter.validateDate(from, to));

        to = "2018-02-01";
        from = "2015-12-01";
        Assert.assertTrue(presenter.validateDate(from, to));
    }

    @Test
    public void getFilteredDataTest() {
        String to = "2018-01-05";
        String from = "2017-06-01";

        TestObserver<List<MovieListUIObject>> testObserver = presenter.getFilteredData(from, to, prepareDataWithReleaseDate())
                .test()
                .assertSubscribed();
        Assert.assertTrue(testObserver.values().size() > 0);
    }

    @Test
    public void showFilteredDataPositiveTest() {
        String to = "2018-01-05";
        String from = "2017-06-01";
        presenter.showFilteredData(from, to);
        verify(view, Mockito.times(1)).onSuccess(any(ArrayList.class));
    }

    private List<MovieListUIObject> prepareDataWithReleaseDate() {
        List<MovieListUIObject> data = new ArrayList<>();
        data.add(new MovieListUIObject.Builder()
                .release_date("2018-02-01").build());
        data.add(new MovieListUIObject.Builder()
                .release_date("2018-01-01").build());
        data.add(new MovieListUIObject.Builder()
                .release_date("2017-11-01").build());
        data.add(new MovieListUIObject.Builder()
                .release_date("2017-07-01").build());
        data.add(new MovieListUIObject.Builder()
                .release_date("2017-01-01").build());
        data.add(new MovieListUIObject.Builder()
                .release_date("2017-02-01").build());
        return data;
    }
}