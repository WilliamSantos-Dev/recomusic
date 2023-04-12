package org.example.recomusic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Screen extends JFrame {

    private final ArrayList<Vertex> sample;
    private Vertex music;
    private final JTextField field;

    private Graph graph;

    public Screen (Graph graph) {
        super();
        this.graph = graph;
        sample = new ArrayList<Vertex>();
        music = new Vertex();
        field = new JTextField();
        JFrame frame1 = new JFrame();

        frame1.setSize(500, 500);

        frame1.setLayout(null);

        frame1.setVisible(true);

        JButton button1 = new JButton("Escolher Música");
        button1.setBounds(100, 100, 300, 40);

        JButton button2 = new JButton("Ver Sample");
        button2.setBounds(100, 150, 300, 40);

        JButton button3 = new JButton("Limpar Sample");
        button3.setBounds(100, 200, 300, 40);

        JButton button4 = new JButton("Mostrar Recomendações");
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
                            System.out.println("TrackName: " + music.getTrackName() + " Artista: " + music.getArtists());
                            sample.add(music);
                        }
                        else{ System.out.println("Musica não encontrada");}



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



        frame1.add(button1);
        frame1.add(button2);
        frame1.add(button3);
        frame1.add(button4);
        frame1.add(button5);
    }



}
