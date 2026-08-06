package player;
import model.Song;
import java.util.*;

public class RandomShuffleStrategy implements ShuffleStrategy{
    private Random random = new Random();

    @Override
    public Song nextSong(List<Song>songs,int currentIndex){
        if(songs.isEmpty())return null;

        return songs.get(random.nextInt(songs.size()));
    }
}