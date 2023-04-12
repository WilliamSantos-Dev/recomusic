package org.example.recomusic;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class Vertex implements Serializable {
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
        int sameArtists = this.artists == vertex.artists ? 1 : 0;
        int sameAlbums = this.albumName == vertex.albumName ? 1 : 0;
        return ((float) noOfArtists / (1 + sameArtists) +
                (float) noOfAlbums / (1 + sameAlbums) +
                (float) 1 / (1 + Math.pow(danceability - vertex.danceability, 2)) +
                (float) 1 / (1 + Math.pow(energy - vertex.energy, 2)) +
                (float) 1 / (1 + Math.pow(speechiness - vertex.speechiness, 2)) +
                (float) 1 / (1 + Math.pow(acousticness - vertex.acousticness, 2)) +
                (float) 1 / (1 + Math.pow(instrumentalness - vertex.instrumentalness, 2)) +
                (float) 1 / (1 + Math.pow(valence - vertex.valence, 2))) / (noOfArtists + noOfAlbums + 6) ;
    }

    public void connectVertex(Vertex vertex) {
        edges.add(vertex, 1, 1);
    }
}
