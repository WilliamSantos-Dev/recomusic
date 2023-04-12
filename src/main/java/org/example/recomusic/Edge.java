package org.example.recomusic;

import lombok.Data;

import java.io.Serializable;

@Data
public class Edge implements Serializable {
    private Vertex vertex;
    private double weight;

    public Edge(Vertex vertex, double weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
