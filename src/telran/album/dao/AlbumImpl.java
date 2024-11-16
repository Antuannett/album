package telran.album.dao;

import telran.album.model.Photo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Predicate;

public class AlbumImpl implements Album{
    private Photo[] photos;
    private int size;

    public AlbumImpl(int capacity){
        photos = new Photo[capacity];
    }

    @Override
    public boolean addPhoto(Photo photo) {
        if (photo == null || photos.length == size || getPhotoFromAlbum(photo.getPhotoid(), photo.getAlbumid()) != null) {
            return false;
        }
        photos[size++] = photo;
        return true;
    }

    @Override
    public boolean removePhoto(int photoId, int albumId) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumid() == albumId && photos[i].getPhotoid() == photoId){
                System.arraycopy(photos, i + 1, photos, i, size - 1 - i);
                photos[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePhoto(int phtoId, int albumId, String url) {
        Photo photo = getPhotoFromAlbum(phtoId, albumId);
        if (photo == null) {
            return false;
        }
        photo.setUrl(url);
        return true;
    }

    @Override
    public Photo getPhotoFromAlbum(int photoId, int albumId) {
        for (int i = 0; i < size; i++) {
            if (photos[i].getAlbumid() == albumId && photos[i].getPhotoid() == photoId) {
                return photos[i];
            }
        }
        return null;
    }

    @Override
    public Photo[] getPhotoBetweenDate(LocalDate dateFrom, LocalDate dateTo) {
        return findPhotoByPredicate(p -> dateFrom.compareTo(p.getDate().toLocalDate()) <= 0
        && dateTo.compareTo(p.getDate().toLocalDate()) > 0);
    }

    @Override
    public Photo[] getAllPhotoFromAlbum(int albumId) {
        return findPhotoByPredicate(p -> p.getAlbumid() == albumId);
    }

    @Override
    public int size() {
        return size;
    }

    private Photo[] findPhotoByPredicate(Predicate<Photo> predicate) {
        Photo[] res = new Photo[size];
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (predicate.test(photos[i])){
                res[j++] = photos[i];
            }
        }
        return Arrays.copyOf(res, j);
    }
}
