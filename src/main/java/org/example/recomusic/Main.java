package org.example.recomusic;

import lombok.Data;

import java.io.IOException;

@Data
public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        System.out.println("Iniciando Grafo");

        try {
            graph = graph.loadGraph("graph.recomusic");
            System.out.println(graph.getVertices().size() + " Músicas carregadas");
        }
        catch (IOException e) {
            graph = Graph.createGraph("dataset.csv", 100000, 10);
            graph.saveGraph("graph.recomusic");
        }
        Screen s = new Screen(graph);
        s.main(args, graph);
    }
}