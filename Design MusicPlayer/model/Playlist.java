package model;

import java.util.*;

public class Playlist{
    private String name;
    private List<Song>songs = new ArrayList<>();

    public Playlist(String name){
        this.name = name;
    }

    public void addSong(Song song){
        songs.add(song);
    }
    public void removeSong(Song song){
        songs.remove(song);
    }
    public List<Song> getSongs(){
        return songs;
    }
    public String getName(){
        return name;
    }
}