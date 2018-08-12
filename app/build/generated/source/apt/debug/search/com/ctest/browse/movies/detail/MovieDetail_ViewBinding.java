// Generated code from Butter Knife. Do not modify!
package search.com.ctest.browse.movies.detail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import search.com.ctest.R;

public class MovieDetail_ViewBinding implements Unbinder {
  private MovieDetail target;

  @UiThread
  public MovieDetail_ViewBinding(MovieDetail target, View source) {
    this.target = target;

    target.toolbar = Utils.findRequiredViewAsType(source, R.id.detail_toolbar_android, "field 'toolbar'", Toolbar.class);
    target.movieImage = Utils.findRequiredViewAsType(source, R.id.movie_banner, "field 'movieImage'", ImageView.class);
    target.movieTitle = Utils.findRequiredViewAsType(source, R.id.movie_title, "field 'movieTitle'", TextView.class);
    target.movieOverview = Utils.findRequiredViewAsType(source, R.id.move_overview, "field 'movieOverview'", TextView.class);
    target.movieRevenue = Utils.findRequiredViewAsType(source, R.id.movie_revenue_value, "field 'movieRevenue'", TextView.class);
    target.movieReleaseDate = Utils.findRequiredViewAsType(source, R.id.movie_released_value, "field 'movieReleaseDate'", TextView.class);
    target.movieType = Utils.findRequiredViewAsType(source, R.id.movie_type_value, "field 'movieType'", TextView.class);
    target.trailerButton = Utils.findRequiredViewAsType(source, R.id.movie_trailer, "field 'trailerButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MovieDetail target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.movieImage = null;
    target.movieTitle = null;
    target.movieOverview = null;
    target.movieRevenue = null;
    target.movieReleaseDate = null;
    target.movieType = null;
    target.trailerButton = null;
  }
}
