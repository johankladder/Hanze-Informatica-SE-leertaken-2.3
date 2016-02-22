package madeexercises.opdracht17;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;

import madeexercises.classifier.classifier.ClassifierModel;
import madeexercises.classifier.classifier.Node;

public class TreeView extends JPanel implements ActionListener{

    private static final long serialVersionUID = 1L;

    private static final int Y_OFFSET = 50;

    private ClassifierModel model;

    public TreeView(ClassifierModel model){
        super();

        this.model = model;
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(5000, 300));
        this.validate();
        this.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(this);
        JFrame frame = new JFrame();
        frame.add(scrollPane);
        frame.setPreferredSize(new Dimension(1024, 400));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setFont(new Font("Arial", 0, 10));


        Node root = model.getRoot();

        drawNode(root, this.getWidth()/2, TreeView.Y_OFFSET, this.getWidth()/4, 20, g, "1");

    }

    public void drawNode(Node n, int x, int y, int xOffset, int yOffset, Graphics g, String direction){
        g.setColor(Color.YELLOW);
        g.drawString(n.getLabel(),x,y);
        Map<String,Node> children = n.getChild();

        int xPos = 0;
        int yPos = 0;

        for (Iterator<String> child = children.keySet().iterator(); child.hasNext();) {
            String newDirection = child.next();

            Node childNode = children.get(direction);

            if(newDirection.equals("1")){
                xPos = x - xOffset;
                yPos = y + yOffset;
            }else if(newDirection.equals("0")){
                xPos = x + xOffset;
                yPos = y + yOffset;
            }else{
                throw new IllegalArgumentException("are you insane?");
            }
            g.setColor(Color.WHITE);
            g.drawLine(x-1, y-1, xPos-1, yPos-1);

            drawNode(childNode, xPos, yPos, xOffset/2, yOffset, g,newDirection);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(model.getRoot().toString());
        repaint();
    }


}