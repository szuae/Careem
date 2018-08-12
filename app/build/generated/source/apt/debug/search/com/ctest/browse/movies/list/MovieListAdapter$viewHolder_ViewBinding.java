// Generated code from Butter Knife. Do not modify!
package search.com.ctest.browse.movies.list;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import search.com.ctest.R;

public class MovieListAdapter$viewHolder_ViewBinding implements Unbinder {
  private MovieListAdapter.viewHolder target;

  @UiThread
  public MovieListAdapter$viewHolder_ViewBinding(MovieListAdapter.viewHolder target, View source) {
    this.target = target;

    target.movieTitle = Utils.findRequiredViewAsType(source, R.id.movie_title, "field 'movieTitle'", TextView.class);
    target.movie_rating = Utils.findRequiredViewAsType(source, R.id.movie_rating, "field 'movie_rating'", RatingBar.class);
    target.view = Utils.findRequiredView(source, R.id.movie_list_node, "field 'view'");
  }

  @Override
  @CallSuper
  public void unbind() {
    MovieListAdapter.viewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.movieTitle = null;
    target.movie_rating = null;
    target.view = null;
  }
}
