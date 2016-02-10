package madeexercises.opdracht8;

import javax.swing.*;
import java.awt.*;

public class DobbelsteenMVC extends JApplet {
    DobbelsteenModel model;             //het model
    TekstView tekstView;              // view
    DobbelsteenView dobbelsteenView;  // view
    StatsView statsView;
    DobbelsteenController controller;             // Controller

    public void init() {
        // hoi
        // iets
        resize(250, 200);

        // Maak het model
        model = new DobbelsteenModel();

        // Maak de controller en geef hem het model
        controller = new DobbelsteenController(model);
        controller.setBackground(Color.cyan);
        getContentPane().add(controller, BorderLayout.NORTH);

        // Maak de views
        dobbelsteenView = new DobbelsteenView(Color.red);
        dobbelsteenView.setBackground(Color.black);
        statsView = new StatsView(model);
        getContentPane().add(dobbelsteenView, BorderLayout.CENTER);
        tekstView = new TekstView();
        tekstView.setBackground(Color.green);
        getContentPane().add(tekstView, BorderLayout.SOUTH);
        getContentPane().add(statsView, BorderLayout.EAST);

        // Registreer de views bij het model
        model.addActionListener(tekstView);
        model.addActionListener(dobbelsteenView);
        model.addActionListener(statsView);

        // Eerste worp
        model.gooi();
    }
}
