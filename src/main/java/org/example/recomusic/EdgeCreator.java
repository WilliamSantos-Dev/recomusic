package org.example.recomusic;

import lombok.Data;

import java.util.Comparator;

@Data
public class EdgeCreator implements Comparator<Vertex> {
    private Vertex vertex;

    public EdgeCreator(Vertex vertex) {
        this.vertex = vertex;
    }

    @Override
    public int compare(Vertex vertex1, Vertex vertex2) {
        return vertex1.calculateWeight(vertex) - vertex2.calculateWeight(vertex);
    }
}
