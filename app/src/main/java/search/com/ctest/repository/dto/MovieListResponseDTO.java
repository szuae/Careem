package search.com.ctest.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieListResponseDTO implements Serializable {
    public int page;
    public int total_pages;
    public Results[] results;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Results {
        public String title;
        public String release_date;
        public int id;
        public String poster_path;
        public String backdrop_path;
        public String overview;
        public boolean video;
        public double vote_average;
    }
}
