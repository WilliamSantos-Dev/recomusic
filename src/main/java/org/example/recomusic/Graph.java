package org.example.recomusic;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Graph {
    private ArrayList<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public void add(Vertex vertex) {
        if (vertices.size() != 0)
            for (Vertex v : vertices)
                v.connectVertex(vertex);
        vertices.add(vertex);
    }

    public EdgeList recommend(ArrayList<Vertex> sample, int size) {
        EdgeList result = new EdgeList(size, new EdgeCreator(sample));
        long noOfArtists = vertices.stream()
                .mapToLong(
                        (e) -> vertices.stream().filter(
                                (ee) -> ee.getArtists().equals(e.getArtists())
                        ).count()
                ).max()
                .getAsLong();
        long noOfAlbums = vertices.stream()
                .mapToLong(
                        (e) -> vertices.stream().filter(
                                (ee) -> ee.getAlbumName().equals(e.getAlbumName())
                        ).count()
                ).max()
                .getAsLong();
        sample.stream().forEach((music) -> music.getEdges().getItems().stream().forEach((edge) -> result.add(edge, noOfArtists, noOfAlbums)));
        return result;
    }
}
