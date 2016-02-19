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

        int y = 10;
        for(int i= 0; i < 7; i++) {
            gc.strokeOval(canvas.getWidth()/2, y , 60, 20);
            y = y + 50;
        }



    }

}
