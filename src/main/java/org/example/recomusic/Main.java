package org.example.recomusic;

import lombok.Data;
@Data
public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        Screen s = new Screen();

        s.showWindow(graph);

    }
}