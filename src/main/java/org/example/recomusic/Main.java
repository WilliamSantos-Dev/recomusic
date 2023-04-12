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

        //Alguns testes
        System.out.println(graph.getVertices().size());
        graph = graph.createGraph("dataset.csv", 1000, 10);
        System.out.println(graph.getVertices().size());
        graph.saveGraph("graph.recomusic", graph);
        graph = graph.loadGraph("graph.recomusic");
        for(int i =0; i < 1000;i++) {
            System.out.println(graph.getVertices().get(i).getTrackName());
        }
        System.out.println(graph.getVertices().size());
    }
}