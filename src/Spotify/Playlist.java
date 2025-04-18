package Spotify;

import java.util.*;

public class Playlist {
    private String title;
    private ArrayList<Music> playlist = new ArrayList<>();
    private User owner;

    public Playlist(String title, User owner) {
        if(title.isEmpty() || owner == null){
            throw new InvalidOperationException("Title or owner cannot be null or empty.");
        }
        this.title = title;
        this.owner = owner;
    }

    private void validatePassword(String password) throws InvalidOperationException {
        if (!owner.getPassword().equals(password)) {
            throw new InvalidOperationException("Invalid password");
        }
    }

    public void editTitle(String newTitle, String password) throws InvalidOperationException {
        if (newTitle == null || newTitle.isEmpty()) {
            throw new InvalidOperationException("New title cannot be empty");
        }
        validatePassword(password);
        this.title = newTitle;
    }

    public void addMusic(Music music, String password) throws InvalidOperationException {
        validatePassword(password);
        if (music == null) {
            throw new InvalidOperationException("Music cannot be null");
        }
        if (playlist.contains(music)) {
            throw new InvalidOperationException("This music already exists in playlist");
        }
        playlist.add(music);
    }

    public void removeMusic(Music music, String password) throws InvalidOperationException {
        if (music == null) {
            throw new InvalidOperationException("Music cannot be null");
        }
        validatePassword(password);
        if (!playlist.contains(music)) {
            throw new InvalidOperationException("This music doesnt exist in playlist");
        }
        playlist.remove(music);
    }

    public ArrayList<Music> searchInPlaylist(String title) {
        ArrayList<Music> results = new ArrayList<>();
        if (title == null) {
            throw new InvalidOperationException("Title cannot be null");
        }
        for (Music music : playlist) {
            if (music.getTitle().equalsIgnoreCase(title)) {
                results.add(music);
            }
        }
        return results;
    }

    public void playPlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty");
            return;
        }
        System.out.println("Playing: " + title + " from playlist");
        for (Music music : playlist) {
            music.play();
        }
    }

    public void shuffle() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty");
            return;
        }
        System.out.println("Playing: " + title + " in shuffle mode");
        ArrayList<Music> shuffled = new ArrayList<>(playlist);
        Collections.shuffle(shuffled);
        for (Music music : shuffled) {
            music.play();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Music> getPlaylist() {
        return playlist;
    }

    public User getOwner() {
        return owner;
    }
}
