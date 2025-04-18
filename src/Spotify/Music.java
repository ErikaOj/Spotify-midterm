package Spotify;

import java.util.*;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream = 0;
    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidOperationException("Title cannot be empty");
        }
        if (singer == null) {
            throw new InvalidOperationException("Singer cannot be empty");
        }

        this.title = title;
        this.singer = singer;
        allMusics.add(this);
    }

    public void play() {
        numberOfStream++;
        System.out.println("Playing: " + this.title + " by " + this.singer.getUsername() + " number of streams: " + this.numberOfStream );
    }

    public static ArrayList<Music> search(String title) {
        if (title == null) {
            throw new InvalidOperationException("Title cannot be null");
        }
        ArrayList<Music> results = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.getTitle().equalsIgnoreCase(title)) {
                results.add(music);
            }
        }
        if(results.isEmpty()){
            return null;
        }else{
            return results;
        }
    }

    public static Music search(String title, String singer) {
        if (title == null || singer == null) {
            throw new InvalidOperationException("Title or singer cannot be null");
        }
        for (Music music : allMusics) {
            if (music.getTitle().equalsIgnoreCase(title) &&
                    music.getSinger().getUsername().equalsIgnoreCase(singer)) {
                return music;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getSinger() {
        return singer;
    }

    public void setSinger(User singer) {
        this.singer = singer;
    }

    public int getNumberOfStream() {
        return numberOfStream;
    }

    public static ArrayList<Music> getAllMusics() {
        return allMusics;
    }

}
