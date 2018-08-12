package search.com.ctest.browse.movies.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import search.com.ctest.R;
import search.com.ctest.model.MovieListUIObject;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.viewHolder> {

    List<MovieListUIObject> data = new ArrayList<>();
    Context context;
    MovieListContract.View fragmentView;
    private Unbinder unbinder;


    public MovieListAdapter(Context context, MovieListContract.View view) {
        this.context = context;
        this.fragmentView = view;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_node, parent, false);

        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(MovieListUIObject object) {
        this.data.add(object);
    }

    public void setData(List<MovieListUIObject> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, final int position, List<Object> payloads) {
        final MovieListUIObject uiObject = data.get(position);

        holder.movieTitle.setText(uiObject.getTitle());
        holder.movie_rating.setRating((float) uiObject.getAverage() / 2);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentView.onItemClicked(data.get(position));
            }
        });
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_title)
        TextView movieTitle;

        @BindView(R.id.movie_rating)
        RatingBar movie_rating;

        @BindView(R.id.movie_list_node)
        View view;

        public viewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onViewAttachedToWindow(viewHolder holder) {
        super.onViewAttachedToWindow(holder);
    }
}
