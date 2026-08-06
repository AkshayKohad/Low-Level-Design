package command;
import player.MusicPlayer;

public class ResumeCommand implements Command{
    private MusicPlayer player;

    public ResumeCommand(MusicPlayer player){
        this.player = player;
    }

    @Override
    public void execute(){
        player.pause();
    }
}