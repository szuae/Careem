package search.com.ctest.landing;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import search.com.ctest.BaseFragment;
import search.com.ctest.MovieSearch;
import search.com.ctest.R;


public class LandingScreen extends BaseFragment {

    public static LandingScreen newInstance() {
        return new LandingScreen();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.start_search_frag_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.search_button)
    public void onSearchButtonClick() {
        if (!isNetworkAvailable()) {
            showMessage(getResources().getString(R.string.no_internet_msg));
            return;
        }
        ((MovieSearch) getActivity()).goToMovieSearchList();
    }
}
