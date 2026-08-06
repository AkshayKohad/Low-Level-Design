package player;
import java.util.*;
import model.Song;

public interface ShuffleStrategy{
    Song nextSong(List<Song>songs,int currentIndex);
}