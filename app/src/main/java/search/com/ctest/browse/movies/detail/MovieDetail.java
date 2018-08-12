package search.com.ctest.browse.movies.detail;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import search.com.ctest.BaseFragment;
import search.com.ctest.BuildConfig;
import search.com.ctest.R;
import search.com.ctest.model.MovieListUIObject;

public class MovieDetail extends BaseFragment {

    MovieListUIObject uiObject;
    View view;

    @BindView(R.id.detail_toolbar_android)
    Toolbar toolbar;
    @BindView(R.id.movie_banner)
    ImageView movieImage;
    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.move_overview)
    TextView movieOverview;
    @BindView(R.id.movie_revenue_value)
    TextView movieRevenue;
    @BindView(R.id.movie_released_value)
    TextView movieReleaseDate;
    @BindView(R.id.movie_type_value)
    TextView movieType;
    @BindView(R.id.movie_trailer)
    Button trailerButton;


    public static MovieDetail newInstance() {
        MovieDetail detail = new MovieDetail();
        return detail;
    }

    public void setData(MovieListUIObject object) {
        uiObject = object;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.moviedetails, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        setupBackNavigation();
        populateData();
    }

    private void populateData() {
        loadMovieImage(movieImage, BuildConfig.BaseURLForImage + uiObject.getPoster_path());
        movieTitle.setText(uiObject.getTitle());
        movieOverview.setText(uiObject.getOverview());
        movieRevenue.setText("NA");
        movieReleaseDate.setText(uiObject.getRelease_date());
        movieType.setText("NA");
    }


    private void loadMovieImage(ImageView movieImage, String url) {
        Glide.with(getContext())
                .load(Uri.parse(url))
                .into(movieImage);
    }

    private void setupBackNavigation() {
        toolbar.setTitle(R.string.movie_detail);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}
