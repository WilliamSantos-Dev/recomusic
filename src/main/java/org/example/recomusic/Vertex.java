package org.example.recomusic;

import lombok.Data;

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
    private float tempo;
    private EdgeList<Vertex> edges;

    public Vertex(int limit, String trackId, String artists, String albumName, String trackName, float danceability, float energy, float speechiness, float acousticness, float instrumentalness, float valence, float tempo) {
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
        this.tempo = tempo;
        this.edges = new EdgeList<>(limit, new EdgeCreator(this));
    }

    public int calculateWeight(Vertex vertex) {
        return 0;
    }

    public void connectVertex(Vertex vertex) {
        edges.add(vertex);
    }
}
