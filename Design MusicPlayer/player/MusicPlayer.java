package player;

import model.Song;

import java.util.*;

public class MusicPlayer {

    private Song currentSong;

    private PlaybackState state = PlaybackState.STOPPED;

    private LoopMode loopMode = LoopMode.OFF;

    private boolean shuffle = false;

    private QueueManager queueManager = new QueueManager();

    public void play(Song song) {

        currentSong = song;
        state = PlaybackState.PLAYING;

        System.out.println("Playing : " + song);
    }

    public void pause() {

        if (state == PlaybackState.PLAYING) {

            state = PlaybackState.PAUSED;

            System.out.println("Paused");
        }
    }

    public void resume() {

        if (state == PlaybackState.PAUSED) {

            state = PlaybackState.PLAYING;

            System.out.println("Resumed");
        }
    }

    public void next() {

        Song next = queueManager.nextSong();

        if (next != null) {
            play(next);
        }
        else {
            System.out.println("Queue Empty");
        }
    }

    public void addToQueue(Song song) {
        queueManager.addSong(song);
    }

    public void toggleShuffle() {

        shuffle = !shuffle;

        System.out.println("Shuffle : " + shuffle);
    }

    public void setLoopMode(LoopMode mode) {

        loopMode = mode;

        System.out.println("Loop Mode : " + mode);
    }
}