package org.example.recomusic;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;

@Data
public class EdgeCreator implements Serializable {
    private ArrayList<Vertex> vertices;

    public EdgeCreator(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public double compare(Vertex vertex1, Vertex vertex2, long noOfArtists, long noOfAlbums) {
        double average1 = vertices.stream().mapToDouble((vertex) -> vertex1.calculateWeight(vertex, noOfArtists, noOfAlbums)).average().getAsDouble();
        double average2 = vertices.stream().mapToDouble((vertex) -> vertex2.calculateWeight(vertex, noOfArtists, noOfAlbums)).average().getAsDouble();
        return average1 - average2;
    }
}
