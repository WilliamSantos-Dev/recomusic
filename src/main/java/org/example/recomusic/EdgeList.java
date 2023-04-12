package org.example.recomusic;

import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
public class EdgeList implements Serializable {
    private int limit;
    private ArrayList<Edge> edges;
    private ArrayList<Vertex> vertices;

    public EdgeList(int limit, ArrayList<Vertex> vertices) {
        this.limit = limit;
        this.edges = new ArrayList<>();
        this.vertices = vertices;
    }

    public double calculateAverageWeight(Vertex vertex, long noOfArtists, long noOfAlbums) {
        return vertices.stream().mapToDouble((e) -> vertex.calculateWeight(e, noOfArtists, noOfAlbums)).average().getAsDouble();
    }

    public void add(Vertex vertex, long noOfArtists, long noOfAlbums) {
        int i = 0;
        Edge edge = new Edge(vertex, calculateAverageWeight(vertex, noOfArtists, noOfAlbums));
        for (; i < edges.size() && edges.get(i).getWeight() > edge.getWeight(); i++);
        edges.add(i, edge);
        if (edges.size() > limit)
            edges.remove(limit);
    }
}