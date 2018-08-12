package search.com.ctest.browse.movies.list;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import search.com.ctest.BaseFragment;
import search.com.ctest.MovieSearch;
import search.com.ctest.MovieSearchApplication;
import search.com.ctest.R;
import search.com.ctest.model.MovieListUIObject;


public class MovieList extends BaseFragment implements MovieListContract.View {

    @BindView(R.id.movie_list)
    RecyclerView listView;

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.list_toolbar_android)
    Toolbar toolbar;

    @BindView(R.id.from_date)
    TextView fromDate;

    @BindView(R.id.to_date)
    TextView toDate;

    @BindView(R.id.filter_button)
    Button filterButton;

    MovieListAdapter listAdapter;

    View view;

    @Inject
    MovieListContract.Presenter presenter;

    DatePickerDialog datePickerDialog;


    public static MovieList newInstance() {
        MovieList list = new MovieList();
        return list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MovieSearchApplication) getActivity().getApplication()).getComponent().inject(this);
        presenter.loadMovies();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.browse_movie_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        presenter.setView(this);
        return view;
    }

    @Override
    public void onDestroy() {
        presenter.clear();
        super.onDestroy();
    }

    private void setupBackNavigation() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.movie_list);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    private void initView() {
        setupBackNavigation();
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        listView.addItemDecoration(new DividerItemDecoration(listView.getContext(), DividerItemDecoration.VERTICAL));

        if (listAdapter == null)
            listAdapter = new MovieListAdapter(getContext(), this);
        listView.setAdapter(listAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadMoreMovies();
            }
        });

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender(DateSelection.from);
            }
        });

        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender(DateSelection.to);
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = fromDate.getText().toString();
                String to = toDate.getText().toString();
                if (presenter.validateDate(from, to))
                    presenter.showFilteredData(from, to);
                else {
                    showMessage(getString(R.string.wrong_date_message));
                }
            }
        });
    }

    private void showCalender(@DateSelection final int selectionFor) {
        Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext());

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int date, int month, int year) {
                String dateStr = datePicker.getYear() + "-" + (datePicker.getMonth() + 1) + "-" + datePicker.getDayOfMonth();
                if (selectionFor == DateSelection.from)
                    fromDate.setText(dateStr);
                else
                    toDate.setText(dateStr);
            }
        }, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSuccess(MovieListUIObject object) {
        listAdapter.setData(object);
        listAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSuccess(List<MovieListUIObject> object) {
        listAdapter.setData(object);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.setView(null);
    }

    @Override
    public void onFailure(String errorMessage) {
        showMessage(errorMessage);
    }

    @Override
    public void onLoadingFinished() {
        Log.e("saify", "onLoadingFinished called...");
    }

    @Override
    public void onItemClicked(MovieListUIObject obj) {
        ((MovieSearch) getActivity()).goToMovieDetailScreen(obj);
    }

    @Override
    public void showMessageForNoMoreData() {
        showMessage(getString(R.string.no_more_data));
    }
}
