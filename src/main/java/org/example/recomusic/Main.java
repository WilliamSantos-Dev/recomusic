package org.example.recomusic;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        ArrayList<Vertex> sample = new ArrayList<>();
        //Alguns testes
        //System.out.println(graph.getVertices().size());
        //graph = graph.createGraph("dataset.csv", 30000, 10);
        //System.out.println("Grafo criado!");
        //System.out.println(graph.getVertices().size());
        //graph.saveGraph("graph.recomusic", graph);
        graph = graph.loadGraph("graph.recomusic");
        System.out.println(graph.getVertices().size());
        //graph = graph.addGraphInterval(graph,"dataset.csv", 10,30000,50000);
        //graph.saveGraph("graph.recomusic", graph);
        //System.out.println(graph.getVertices().size());
    }
}