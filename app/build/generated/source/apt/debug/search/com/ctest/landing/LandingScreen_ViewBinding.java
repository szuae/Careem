// Generated code from Butter Knife. Do not modify!
package search.com.ctest.landing;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import search.com.ctest.R;

public class LandingScreen_ViewBinding implements Unbinder {
  private LandingScreen target;

  private View view2131230864;

  @UiThread
  public LandingScreen_ViewBinding(final LandingScreen target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.search_button, "method 'onSearchButtonClick'");
    view2131230864 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearchButtonClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230864.setOnClickListener(null);
    view2131230864 = null;
  }
}
