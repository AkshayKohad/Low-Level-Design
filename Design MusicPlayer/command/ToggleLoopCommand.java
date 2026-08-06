package command;

import player.*;

public class ToggleLoopCommand implements Command {

    private MusicPlayer player;

    private LoopMode mode;

    public ToggleLoopCommand(MusicPlayer player, LoopMode mode) {

        this.player = player;
        this.mode = mode;
    }

    @Override
    public void execute() {
        player.setLoopMode(mode);
    }
}