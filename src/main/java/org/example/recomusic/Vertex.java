package org.example.recomusic;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Vertex {
    @CsvBindByName(column = "track_id")
    private String trackId;
    @CsvBindByName
    private String artists;
    @CsvBindByName(column = "album_name")
    private String albumName;
    @CsvBindByName(column = "track_name")
    private String trackName;
    @CsvBindByName
    private float danceability;
    @CsvBindByName
    private float energy;
    @CsvBindByName
    private float speechiness;
    @CsvBindByName
    private float acousticness;
    @CsvBindByName
    private float instrumentalness;
    @CsvBindByName
    private float valence;
    private EdgeList edges;

    public Vertex() {

    };

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
//        int weights[]
        return 0;
    }

    public void connectVertex(Vertex vertex) {
        edges.add(vertex, 1, 1);
    }
}
