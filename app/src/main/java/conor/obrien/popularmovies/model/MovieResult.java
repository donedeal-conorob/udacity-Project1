package conor.obrien.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Conor O'Brien on 12/11/15.
 */
public class MovieResult implements Parcelable{

    private boolean adult;
    private boolean video;
    private String backdrop_path;
    private String original_language;
    private String original_title;
    private String overview;
    private String release_date;
    private String poster_path;
    private String title;
    private int vote_count;
    private int id;
    private int[] genre_ids;
    private float popularity;
    private float vote_average;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(adult ? 1 : 0);
        out.writeInt(video ? 1 : 0);
        out.writeString(backdrop_path);
        out.writeString(original_language);
        out.writeString(original_title);
        out.writeString(overview);
        out.writeString(release_date);
        out.writeString(poster_path);
        out.writeString(title);
        out.writeInt(vote_count);
        out.writeInt(id);
        out.writeIntArray(genre_ids);
        out.writeFloat(popularity);
        out.writeFloat(vote_average);
    }

    private MovieResult(Parcel in) {
        adult = (in.readInt() != 0);
        video = (in.readInt() != 0);
        backdrop_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date= in.readString();
        poster_path= in.readString();
        title= in.readString();
        vote_count = in.readInt();
        id = in.readInt();
        genre_ids = in.createIntArray();
        popularity = in.readFloat();
        vote_average = in.readFloat();
    }

    public static final Parcelable.Creator<MovieResult> CREATOR = new Parcelable.Creator<MovieResult>() {
        public MovieResult createFromParcel(Parcel in) {
            return new MovieResult(in);
        }

        public MovieResult[] newArray(int size) {
            return new MovieResult[size];
        }
    };

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String release_date) {
        this.release_date = release_date;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getGenreIds() {
        return genre_ids;
    }

    public void setGenreIds(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(float vote_average) {
        this.vote_average = vote_average;
    }
}
