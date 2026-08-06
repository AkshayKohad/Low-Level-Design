package command;
import model.Song;
import player.MusicPlayer;

public class PlayCommand implements Command{
    private Song song;
    private MusicPlayer player;

    public PlayCommand(MusicPlayer player,Song song){
        this.song = song;
        this.player = player;
    }

    @Override
    public void execute(){
        player.play(song);
    }
}