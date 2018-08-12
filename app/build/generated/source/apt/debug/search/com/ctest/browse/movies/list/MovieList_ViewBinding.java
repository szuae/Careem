// Generated code from Butter Knife. Do not modify!
package search.com.ctest.browse.movies.list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import search.com.ctest.R;

public class MovieList_ViewBinding implements Unbinder {
  private MovieList target;

  @UiThread
  public MovieList_ViewBinding(MovieList target, View source) {
    this.target = target;

    target.listView = Utils.findRequiredViewAsType(source, R.id.movie_list, "field 'listView'", RecyclerView.class);
    target.swipeRefreshLayout = Utils.findRequiredViewAsType(source, R.id.swipeContainer, "field 'swipeRefreshLayout'", SwipeRefreshLayout.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.list_toolbar_android, "field 'toolbar'", Toolbar.class);
    target.fromDate = Utils.findRequiredViewAsType(source, R.id.from_date, "field 'fromDate'", TextView.class);
    target.toDate = Utils.findRequiredViewAsType(source, R.id.to_date, "field 'toDate'", TextView.class);
    target.filterButton = Utils.findRequiredViewAsType(source, R.id.filter_button, "field 'filterButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MovieList target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.listView = null;
    target.swipeRefreshLayout = null;
    target.toolbar = null;
    target.fromDate = null;
    target.toDate = null;
    target.filterButton = null;
  }
}
