package madeexercises.opdracht17;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Created by johankladder on 19-2-16.
 */
public class CanvasDrawer {

    private Canvas canvas;
    private static double orW;
    private static double orH;

    public CanvasDrawer(Canvas canvas) {
        this.canvas = canvas;
        init();
    }

    private void init() {
        canvas.setHeight(500);
        canvas.setWidth(500);
        orW = canvas.getWidth();
        orH = canvas.getHeight();

        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int y = 10; // Space between circels:
        double lastCenter  = canvas.getWidth()/2;
        boolean first = true;
        double middell = 0;
        double middelr = 0;
        for(int i= 0; i < 7; i++) {
            boolean left = true;
            for(int j = 0; j < i; j++) {
                if(first) {
                    gc.strokeOval(lastCenter, y, 5, 5);
                    middell = lastCenter - (lastCenter * 0.75);
                    middelr = lastCenter + (lastCenter * 0.75);
                    first = false;
                } else {
                    if(left) {
                        gc.strokeOval(middell, y, 5, 5);
                        left = false;
                        middell = middell - (middell * 0.75);
                    } else {
                        gc.strokeOval(middelr, y, 5, 5);
                        left = true;
                        middelr = middelr + (middelr * 0.75);
                    }
                }
            }
            //lastCenter =
            y = y + 50;
            //lastCenter = lastCenter * 2;

        }



    }

}
