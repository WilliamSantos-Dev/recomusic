package org.example.recomusic;

import lombok.Data;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

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
        String path = "/home/william/Documentos/recomusic/database.csv";
        int limit = 10;
        int contador = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            String line = br.readLine();
            line = br.readLine();
            while (line != null){
                String [] music = line.split(",");
                if(music.length == 10) //evita que linhas com virgulas a mais causem erro
                    graph.add(createMusic(music, limit));
                line = br.readLine();
                if(contador%1000 == 0)
                    System.out.println(contador);
                contador++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(contador + "Músicas inseridas");
        System.out.println(graph.getVertices().size());
    }
}