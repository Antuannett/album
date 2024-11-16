package telran.album.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Photo {
    private int albumid;
    private int photoid;
    private String title;
    private String url;
    private LocalDateTime date;

    public int getAlbumid() {
        return albumid;
    }

    public int getPhotoid() {
        return photoid;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Photo(int albumid, int photoid, String title, String url, LocalDateTime date) {
        this.albumid = albumid;
        this.photoid = photoid;
        this.title = title;
        this.url = url;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "albumid=" + albumid +
                ", photoid=" + photoid +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return albumid == photo.albumid && photoid == photo.photoid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumid, photoid);
    }
}
