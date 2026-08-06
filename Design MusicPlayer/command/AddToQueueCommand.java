package command;

import model.Song;
import player.MusicPlayer;

public class AddToQueueCommand implements Command {

    private MusicPlayer player;

    private Song song;

    public AddToQueueCommand(MusicPlayer player, Song song) {

        this.player = player;
        this.song = song;
    }

    @Override
    public void execute() {
        player.addToQueue(song);
    }
}