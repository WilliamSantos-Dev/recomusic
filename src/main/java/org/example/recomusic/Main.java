package org.example.recomusic;

import lombok.Data;
@Data
public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph = graph.loadGraph("graph.recomusic");
        System.out.println(graph.getVertices().size());
        Screen s = new Screen(graph);
    }
}