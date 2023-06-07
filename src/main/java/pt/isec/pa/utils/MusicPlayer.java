package pt.isec.pa.utils;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;

public class MusicPlayer {
    public static final String pacman_beginning = "pacman_beginning";
    public static final String pacman_chomp = "pacman_chomp";
    public static final String pacman_death = "pacman_death";
    public static final String pacman_eatfruit = "pacman_eatfruit";
    public static final String pacman_eatghost = "pacman_eatghost";
    private static boolean running = false;
    static MediaPlayer mp;

    public static void playMusic(String name) {
        if (running) {
            return;
        }
        running = true;
        URL path = MusicPlayer.class.getResource("/sounds/" + name + ".wav");
        Media music = new Media(path.toString());
        mp = new MediaPlayer(music);
        mp.setStartTime(Duration.ZERO);
        mp.setStopTime(music.getDuration());
        mp.setAutoPlay(true);
        mp.setOnEndOfMedia(() -> {
            running = false;
        });
    }
}