package search.com.ctest;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({FragmentLoadStrategy.add, FragmentLoadStrategy.replace})
public @interface FragmentLoadStrategy {

    int add = 0;
    int replace = 1;
}
