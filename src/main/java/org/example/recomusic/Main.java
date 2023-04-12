package org.example.recomusic;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Data
public class Main {

    public static Vertex createMusic(String[] music, int limit){
        float[] data = new float[6];
        int i, aux = 4;
        for(i =0; i<6; i++, aux++){
            if(music[aux].equals("True"))
                data[i] = 1;
            else if (music[aux].equals("False"))
                data[i] = 0;
            else {
                try {
                    data[i] = Float.parseFloat(music[aux]);
                } catch (NumberFormatException e) {
                    data[i] = 0;
                }
            }
        }
        Vertex newMusic = new Vertex(limit, music[0],music[1],music[2], music[3],data[0],data[1],data[2],
                data[3], data[4], data[5]);
        return  newMusic;
    }
    public static void main(String[] args) {
        Graph graph = new Graph();
        String path = "/home/lucas/Documents/BCC/3-periodo/aed2/Recomusic/dataset.csv";
        try {
            List<Vertex> vertices = new CsvToBeanBuilder(new FileReader(path)).withType(Vertex.class).build().parse();
            for (int i = 0; i < 1000; i++) {
                vertices.get(i).setEdges(new EdgeList(10, new EdgeCreator(new ArrayList<>(Arrays.asList(vertices.get(i))))));
                graph.add(vertices.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}