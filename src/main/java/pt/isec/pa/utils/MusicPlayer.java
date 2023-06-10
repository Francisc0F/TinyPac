package pt.isec.pa.utils;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    public static final String pacman_beginning = "pacman_beginning";
    public static final String pacman_chomp = "pacman_chomp";
    public static final String pacman_death = "pacman_death";
    public static final String pacman_eatfruit = "pacman_eatfruit";
    public static final String pacman_eatghost = "pacman_eatghost";
    private static boolean running = false;


    static List<MediaPlayer> musicPlayers = new ArrayList<>();

    public static void playMusic(String name) {
        if (isMusicPlaying(name)) {
            return;
        }
        URL path = MusicPlayer.class.getResource("/sounds/" + name + ".wav");
        Media music = new Media(path.toString());
        MediaPlayer mp = new MediaPlayer(music);
        mp.setStartTime(Duration.ZERO);

        mp.setStopTime(music.getDuration());
        mp.setAutoPlay(true);
        mp.setOnEndOfMedia(() -> {
            musicPlayers.remove(mp);
        });
        musicPlayers.add(mp);
    }
    private static boolean isMusicPlaying(String name) {
        for (MediaPlayer player : musicPlayers) {
            Media media = player.getMedia();
            if (media != null && media.getSource().endsWith(name + ".wav")) {
                return true; // Music with the same name is already playing
            }
        }
        return false;
    }
}