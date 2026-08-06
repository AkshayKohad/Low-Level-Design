package command;

import player.MusicPlayer;

public class NextCommand implements Command {

    private MusicPlayer player;

    public NextCommand(MusicPlayer player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.next();
    }
}