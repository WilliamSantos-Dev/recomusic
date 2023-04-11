package org.example.recomusic;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Vertex {
    private String trackId;
    private String artists;
    private String albumName;
    private String trackName;
    private float danceability;
    private float energy;
    private float speechiness;
    private float acousticness;
    private float instrumentalness;
    private float valence;
    private EdgeList edges;

    public Vertex(int limit, String trackId, String artists, String albumName, String trackName, float danceability, float energy, float speechiness, float acousticness, float instrumentalness, float valence) {
        this.trackId = trackId;
        this.artists = artists;
        this.albumName = albumName;
        this.trackName = trackName;
        this.danceability = danceability;
        this.energy = energy;
        this.speechiness = speechiness;
        this.acousticness = acousticness;
        this.instrumentalness = instrumentalness;
        this.valence = valence;
        this.edges = new EdgeList(limit, new EdgeCreator(new ArrayList<>(Arrays.asList(this))));
    }

    public double calculateWeight(Vertex vertex, long noOfArtists, long noOfAlbums) {
        int sameArtists = artists.equals(vertex.artists) ? 1 : 0;
        int sameAlbum = albumName.equals(vertex.albumName) ? 1 : 0;
        return noOfArtists * sameArtists + noOfAlbums * sameAlbum + danceability + energy + speechiness + acousticness + instrumentalness + valence;
    }

    public void connectVertex(Vertex vertex) {
        edges.add(vertex, 1, 1);
    }
}
