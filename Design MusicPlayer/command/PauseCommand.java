package command;
import player.MusicPlayer;

public class PauseCommand implements Command{
    private MusicPlayer player;

    public PauseCommand(MusicPlayer player){
        this.player = player;
    }

    @Override
    public void execute(){
        player.pause();
    }
}