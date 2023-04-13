package org.example.recomusic;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.Data;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.Duration;

@Data
public class Graph implements Serializable {
    private ArrayList<Vertex> vertices;
    private static final long serialVersionUID = 57246925417L;
    //Esse ID garante que o grafo salvo e carregado seja sempre o mesmo, evita erros.
    public Graph() {
        vertices = new ArrayList<>();
    }

    public void add(Vertex vertex) {
        if (vertices.size() != 0)
            for (Vertex v : vertices)
                v.connectVertex(vertex);
        vertices.add(vertex);
    }

    public List<Vertex> recommend(ArrayList<Vertex> sample, int size) {
        EdgeList result = new EdgeList(size, sample);
        long noOfArtists = sample.stream()
                .mapToLong(
                        (e) -> sample.stream().filter(
                                (ee) -> ee.getArtists().equals(e.getArtists())
                        ).count()
                ).max()
                .getAsLong();
        long noOfAlbums = sample.stream()
                .mapToLong(
                        (e) -> sample.stream().filter(
                                (ee) -> ee.getAlbumName().equals(e.getAlbumName())
                        ).count()
                ).max()
                .getAsLong();
        sample.stream().forEach((music) -> music.getEdges().getEdges().stream().forEach((edge) -> result.add(edge.getVertex(), noOfArtists, noOfAlbums)));
        return result.getEdges().stream().map((e) -> e.getVertex()).toList();
    }

    public void saveGraph(String path){
        try{
            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream object = new ObjectOutputStream(file);
            object.writeObject(this);
            object.flush();
            System.out.println("Grafo salvo com sucesso!");
        }catch (Exception erro){ System.out.println("Não foi possivel salvar o grafo. ERRO: " + erro.getMessage() );}
    }
    public static Graph loadGraph(String path) throws IOException {
        Graph graph = new Graph();
        try{
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream object = new ObjectInputStream(file);
            graph = (Graph) object.readObject();
            System.out.println("Grafo carregado com sucesso");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

     public static Graph createGraph(String path, int numVertices, int limitEdges){

        Graph graph = new Graph();
        //int contador = 0;
         // Instant inicioGeral = Instant.now();
         //Instant inicioParcial = Instant.now();
        try {
            List<Vertex> vertices = new CsvToBeanBuilder(new FileReader(path)).withType(Vertex.class).build().parse();
            for (int i = 0; i < numVertices; i++) {
                vertices.get(i).setEdges(new EdgeList(limitEdges, new ArrayList<>(Arrays.asList(vertices.get(i)))));
                graph.add(vertices.get(i));
                /*
                if (contador % 1000 == 0) {
                    Instant fimParcial = Instant.now();
                    Duration duracaoParcial = Duration.between(inicioParcial, fimParcial); // Calcula a duração parcial
                    long duracaoParcialMillis = duracaoParcial.toMillis();
                    System.out.println((contador - 1000) + " - " + contador + " Tempo: " + duracaoParcialMillis + " ms");
                    inicioParcial = Instant.now();
                }
                contador++;
                 */
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /*
        Instant fimGeral = Instant.now();
         Duration duracaoGeral = Duration.between(inicioGeral, fimGeral);
         long duracaoGeralMillis = duracaoGeral.toMillis();
         System.out.println("Tempo total: " + duracaoGeralMillis + " ms");*/
         return graph;
    }

    //Função auxiliar para carregar um intervalo de músicas específico, caso já tenha adicionado uma parte e deseje continuar de onde parou.
    public Graph addGraphInterval(Graph graph, String path, int limitEdges, int initInterval, int endInterval){
        int contador = 0;
        try {
            List<Vertex> vertices = new CsvToBeanBuilder(new FileReader(path)).withType(Vertex.class).build().parse();
            for (int i = initInterval; i < endInterval; i++) {
                vertices.get(i).setEdges(new EdgeList(limitEdges, new ArrayList<>(Arrays.asList(vertices.get(i)))));
                graph.add(vertices.get(i));
                if(contador%1000 == 0)
                    System.out.println(contador);
                contador++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return graph;
    }

    public  Vertex searchMusic(String id){
        for(Vertex m : this.vertices){
            if(id.equals(m.getTrackId())) {
                return m;
            }
        }
        return null;
    }
}
