package org.example.recomusic;

import lombok.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

@Data
public class Screen extends JFrame {

    private  ArrayList<Vertex> sample;
    private Vertex music;
    private final JTextField field;


    private Graph graph;

    public Screen (Graph graph) {
        super();
        this.graph = graph;
        sample = new ArrayList<Vertex>();
        music = new Vertex();
        field = new JTextField();
        DefaultListModel listSample = new DefaultListModel();
        DefaultListModel listRecommendation = new DefaultListModel();
        JList lst = new JList(listSample);
        JList lst2 = new JList(listRecommendation);
        JScrollPane jpListSample = new JScrollPane(lst);
        JScrollPane jpListRecommendation = new JScrollPane(lst2);

        jpListSample.setBounds(100, 600, 400, 400);
        jpListRecommendation.setBounds(600, 600, 400, 400);
        JFrame frame1 = new JFrame();
        frame1.add(jpListSample);
        frame1.add(jpListRecommendation);

        frame1.setSize(1500, 1000);

        frame1.setLayout(null);

        frame1.setVisible(true);

        JButton button1 = new JButton("Escolher Música");
        button1.setBounds(100, 100, 300, 40);

        JButton button2 = new JButton("Gerar Recomendações");
        button2.setBounds(100, 150, 300, 40);

        JButton button3 = new JButton("Limpar Sample");
        button3.setBounds(100, 200, 300, 40);

        JButton button4 = new JButton("Limpar Recomendações");
        button4.setBounds(100, 250, 300, 40);

        JButton button5 = new JButton("Fechar");
        button5.setBounds(100, 300, 300, 40);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                JButton search = new JButton("Procurar");
                JButton button = new JButton("Adicionar");
                frame.setSize(500, 500);
                frame.setLayout(null);
                frame.setVisible(true);

                search.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String trackID = field.getText();

                        music = graph.searchMusic(trackID);
                        if(music != null) {
                            JOptionPane.showMessageDialog(null,
                                  "Nome do Artista: " + music.getArtists() +
                                  "\nNome da Música: " + music.getTrackName());
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Impossível encontrar música, tente " +
                                    "com outro ID");
                        }
                    }
                });

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String trackID = field.getText();
                        music = graph.searchMusic(trackID);

                        if(music != null) {
                           sample.add(music);
                           JOptionPane.showMessageDialog(null, "Música adicionada à playlist");
                        }
                        else{JOptionPane.showMessageDialog(null, "Impossível encontrar música, tente " + "com outro ID");}

                        listSample.add(listSample.size()," Nome " +sample.get(listSample.size()).getTrackName() + " Artirta: " + sample.get(listSample.size()).getArtists());
                        frame1.add(jpListSample);
                        frame.setVisible(false);
                    }
                });
                search.setBounds(310, 100, 100, 20);
                button.setBounds(150, 400, 200, 40);
                field.setBounds(100, 100, 200, 20);
                frame.add(search);
                frame.add(field);
                frame.add(button);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listSample.size() > 0 ){
                    JOptionPane.showMessageDialog(null, "Carregando recomendação, aguarde!");
                    try{
                        List<Vertex> listR = graph.recommend(sample, 3);
                        listRecommendation.add(listRecommendation.size(), listR.get(0).getTrackName() + " - " + listR.get(0).getArtists());
                        listRecommendation.add(listRecommendation.size(), listR.get(1).getTrackName() + " - " + listR.get(1).getArtists());
                        listRecommendation.add(listRecommendation.size(), listR.get(2).getTrackName() + " - " + listR.get(2).getArtists());
                    }catch (Exception erro){
                        JOptionPane.showMessageDialog(null, "Não Foi possivel gerar uma recomendação! \nERRO:" + erro.getMessage());
                    }
                }else{ JOptionPane.showMessageDialog(null, "Primeiro crie uma lista de musicas!");}

            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               listSample.removeAllElements();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listRecommendation.removeAllElements();
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame1.add(button1);
        frame1.add(button2);
        frame1.add(button3);
        frame1.add(button4);
        frame1.add(button5);
    }
}
