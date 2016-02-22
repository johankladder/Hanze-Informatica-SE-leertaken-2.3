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

    private static final int Y_OFFSET = 200;

    private ClassifierModel model;
    private BufferedImage image;

    public TreeView(ClassifierModel model){
        super();

//		try{
//			this.image = ImageIO.read(new URL("http://www.nasa.gov/images/content/178493main_sig07-009-hires.jpg"));
//		}catch(IOException io){
//			Logger.getLogger(TreeView.class.getName()).log(Level.SEVERE, null, io);
//		}

        this.model = model;
        //model.addActionListener(this);
        this.setBackground(Color.DARK_GRAY);
        this.setPreferredSize(new Dimension(5000, 500));
        this.validate();
        this.setVisible(true);

        JScrollPane scrollPane = new JScrollPane(this);
        JFrame frame = new JFrame();
        frame.add(scrollPane);
        frame.setPreferredSize(new Dimension(1024, 768));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image, 0, 0, null);
        g.setFont(new Font("Arial", 0, 10));


        Node root = model.getRoot();

        drawNode(root, this.getWidth()/2, TreeView.Y_OFFSET, this.getWidth()/4, 20, g,"1");

    }

    public void drawNode(Node n, int x, int y, int xOffset, int yOffset, Graphics g,String direction){
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

//	public void drawNode
//		printNode
//		for(Children c : Node.children)
//			if(c.label.equals ( RIGHT ) )
//				drawNode(RIGHT Version)
//			else if( c.label.equals( LEFT ) )
//			else
//				NO BINARY TREE DESU!!!
//	

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(model.getRoot().toString());
        repaint();
    }


}