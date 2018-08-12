package search.com.ctest.model;


import java.io.Serializable;

public class MovieListUIObject implements Serializable {
    String title;
    String release_date;
    int id;
    String poster_path;
    String backdrop_path;
    String overview;
    boolean video;
    double average;

    private MovieListUIObject(Builder builder) {
        title = builder.title;
        id = builder.id;
        poster_path = builder.poster_path;
        backdrop_path = builder.backdrop_path;
        overview = builder.overview;
        video = builder.video;
        average = builder.average;
        release_date = builder.release_date;
    }

    public String getTitle() {
        return title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getId() {
        return id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public boolean isVideo() {
        return video;
    }

    public double getAverage() {
        return average;
    }


    public static final class Builder {
        private String title;
        private int id;
        private String poster_path;
        private String backdrop_path;
        private String overview;
        private boolean video;
        private double average;
        private String release_date;

        public Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder release_date(String val) {
            release_date = val;
            return this;
        }


        public Builder poster_path(String val) {
            poster_path = val;
            return this;
        }

        public Builder backdrop_path(String val) {
            backdrop_path = val;
            return this;
        }

        public Builder overview(String val) {
            overview = val;
            return this;
        }

        public Builder video(boolean val) {
            video = val;
            return this;
        }

        public Builder average(double val) {
            average = val;
            return this;
        }

        public MovieListUIObject build() {
            return new MovieListUIObject(this);
        }
    }
}
