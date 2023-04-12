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
    @CsvBindByName
    private double tempo;
    @CsvBindByName(column = "track_genre")
    private String trackGenre;
    private EdgeList edges;

    public Vertex() {

    };

    public double calculateWeight(Vertex vertex, long noOfArtists, long noOfAlbums) {
        int sameArtists = this.artists.equals(vertex.artists) ? 1 : 0;
        int sameAlbums = this.albumName.equals(vertex.albumName) ? 1 : 0;
        int sameGenre = this.trackGenre.equals(vertex.trackGenre) ? 1 : 0;
        return ((float) noOfArtists / (1 + sameArtists) +
                (float) noOfAlbums / (1 + sameAlbums) +
                (float) 3 / (1 + sameGenre) +
                (float) 3 / (1 + Math.pow(danceability - vertex.danceability, 2)) +
                (float) 4 / (1 + Math.pow(energy - vertex.energy, 2)) +
                (float) 2 / (1 + Math.pow(speechiness - vertex.speechiness, 2)) +
                (float) 1 / (1 + Math.pow(acousticness - vertex.acousticness, 2)) +
                (float) 2 / (1 + Math.pow(instrumentalness - vertex.instrumentalness, 2)) +
                (float) 2 / (1 + Math.pow(tempo - vertex.tempo, 2)) +
                (float) 4 / (1 + Math.pow(valence - vertex.valence, 2))) / (noOfArtists + noOfAlbums + 21);
    }

    public void connectVertex(Vertex vertex) {
        edges.add(vertex, 1, 1);
    }
}
