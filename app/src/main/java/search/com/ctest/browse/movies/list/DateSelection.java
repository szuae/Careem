package search.com.ctest.browse.movies.list;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({DateSelection.from, DateSelection.to})
public @interface DateSelection {
    int from = 0;
    int to = 1;
}
