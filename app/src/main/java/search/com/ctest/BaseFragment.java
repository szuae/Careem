package search.com.ctest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;
import search.com.ctest.utilitycomponent.ConnectivityUtil;
import search.com.ctest.utilitycomponent.ConnectivityUtilImpl;


public class BaseFragment extends Fragment {

    @Nullable
    private ConnectivityUtil connectivityUtil;
    public Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectivityUtil = new ConnectivityUtilImpl(getContext());
    }

    public void showMessage(String msg) {
        Snackbar.make(getActivity().findViewById(android.R.id.content),
                msg, Snackbar.LENGTH_LONG).show();
    }

    public boolean isNetworkAvailable() {
        return connectivityUtil != null && connectivityUtil.isNetworkAvailable();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (unbinder != null)
            unbinder.unbind();
    }
}
