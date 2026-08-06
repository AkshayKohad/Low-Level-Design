package command;

import player.MusicPlayer;

public class ToggleShuffleCommand implements Command {

    private MusicPlayer player;

    public ToggleShuffleCommand(MusicPlayer player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.toggleShuffle();
    }
}