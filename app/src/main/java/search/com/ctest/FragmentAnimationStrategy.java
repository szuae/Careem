package search.com.ctest;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({FragmentAnimationStrategy.slideFromLeft, FragmentAnimationStrategy.fading})
public @interface FragmentAnimationStrategy {

    int slideFromLeft = 0;
    int fading = 1;
}
