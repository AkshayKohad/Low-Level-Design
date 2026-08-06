package service;

import model.Playlist;

import java.util.*;

public class PlaylistService {

    private Map<String, Playlist> playlists = new HashMap<>();

    public Playlist createPlaylist(String name) {

        Playlist playlist = new Playlist(name);

        playlists.put(name, playlist);

        return playlist;
    }

    public void deletePlaylist(String name) {
        playlists.remove(name);
    }

    public Playlist getPlaylist(String name) {
        return playlists.get(name);
    }
}