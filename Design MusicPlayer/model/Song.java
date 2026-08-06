package model;

public class Song{
    private final String id;
    private final String title;
    private final String artist;
    private final int duration;

    public Song(String id,String title,String artist,int duration){
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getId(){
        return this.id;
    }
    public String getTitle(){
        return this.title;
    }
    public String getArtist(){
        return this.artist;
    }
    public int getDuration(){
        return this.duration;
    }
    @Override
    public String toString() {
        return title + " - " + artist;
    }
}