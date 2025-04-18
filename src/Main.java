import Spotify.*;;

public class Main {
    public static void main(String[] args) {
        try {
            User user1 = new User("erika", "password123");
            User user2 = new User("hana", "pass1234");
            User artist1 = new User("nirvana", "nirvana1");
            User artist2 = new User("silent planet", "silentplanet");

            try {
                User duplicateUser = new User("erika", "anotherpass");
            } catch (InvalidOperationException e) {
                System.out.println("Duplicate username: " + e.getMessage());
            }

            try {
                User shortPasswordUser = new User("short", "123");
            } catch (InvalidOperationException e) {
                System.out.println("Short pass: " + e.getMessage());
            }

            Music song1 = new Music("Smells Like Teen Spirit", artist1);
            Music song2 = new Music("Terminal", artist2);

            song1.play();
            song2.play();

            user1.buyPremium(3);
            user1.createPlaylist("Metal");
            try {
                user2.createPlaylist("Pop");
            } catch (InvalidOperationException e) {
                System.out.println("Error: " + e.getMessage());
            }


            Playlist playlist = user1.getPlaylists().get(0);

            try {
                playlist.addMusic(song1, "wrongpass");
            } catch (InvalidOperationException e) {
                System.out.println("Can't add, wrong password: " + e.getMessage());
            }

            playlist.addMusic(song1, "password123");
            playlist.addMusic(song2, "password123");

            try {
                playlist.addMusic(song1, "password123");
            } catch (InvalidOperationException e) {
                System.out.println("Add duplicate music: " + e.getMessage());
            }

            playlist.playPlaylist();

            playlist.shuffle();

        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

