package org.example.recomusic;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Graph implements Serializable {
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

    public void saveGraph(String path, Graph graph){
        try{
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(graph);
            object.flush();
            System.out.println("Grafo salvo com sucesso!");
        }catch (Exception erro){ System.out.println("Não foi possivel salvar o grafo. ERRO: " + erro.getMessage() );}
    }
    public Graph loadGraph(String path){
        Graph graph = new Graph();
        try{
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream object = new ObjectInputStream(file);
            graph = (Graph) object.readObject();
            System.out.println("Grapho carregado com sucesso");

        }catch (Exception erro){ System.out.println("Não foi possivel carregar o Grafo. ERRO: " +erro.getMessage());}
        return graph;
    }


    public Graph createGraph(String path, int numVertex, int limitEdges){
        Graph graph = new Graph();
        try {
            List<Vertex> vertices = new CsvToBeanBuilder(new FileReader(path)).withType(Vertex.class).build().parse();
            for (int i = 0; i < numVertex; i++) {
                vertices.get(i).setEdges(new EdgeList(limitEdges, new EdgeCreator(new ArrayList<>(Arrays.asList(vertices.get(i))))));
                graph.add(vertices.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }
}
