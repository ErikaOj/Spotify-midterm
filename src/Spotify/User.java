package Spotify;

import java.util.*;

public class User {
    private String username;
    private String password;
    private ArrayList<User> followerList;
    private ArrayList<User> followingList;
    private UserBehavior behavior;
    private ArrayList<Playlist> playlists;
    private static ArrayList<User> allUsers = new ArrayList<>();

    private boolean isUsernameUnique(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }

    public User(String username, String password) throws InvalidOperationException {
        if (!isUsernameUnique(username)) {
            throw new InvalidOperationException("Username already exists.");
        }
        if(username == null || username.isEmpty()){
            throw new InvalidOperationException("Username cannot be empty.");
        }
        if (password.length() < 8) {
            throw new InvalidOperationException("Password must be at least 8 characters long.");
        }

        this.username = username;
        this.password = password;
        this.followerList = new ArrayList<>();
        this.followingList = new ArrayList<>();
        this.playlists = new ArrayList<>();
        this.behavior = new RegularBehavior();

        allUsers.add(this);
    }

    public void follow(User user) {
        if (user == null || this == user)
            throw new InvalidOperationException("Invalid user");
        if (!followingList.contains(user)) {
            followingList.add(user);
            user.followerList.add(this);
        }
    }

    public void createPlaylist(String title) {
        this.behavior.createPlaylist(title, this);
    }

    public void playMusic(Music music) {
        this.behavior.playMusic(music);
    }

    public void buyPremium(int month) {
        this.behavior.buyPremium(this, month);
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<User> getFollowerList() {
        return followerList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getPassword() {
        return password;
    }
}
