package org.example.recomusic;

import lombok.Data;

@Data
public class Edge {
    private Vertex vertex;
    private double weight;

    public Edge(Vertex vertex, double weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
