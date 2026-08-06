package player;
import model.Song;
import java.util.*;


public class QueueManager{
    private Queue<Song>queue = new LinkedList<>();

    public void addSong(Song song){
        queue.offer(song);
    }
    public Song nextSong(){
        return queue.poll();
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }
}