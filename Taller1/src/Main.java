import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        /**
         * Dar valor a las variables min y max que son el lienzo.
         */
        double min = -1.0;
        double max = 1.0;
        /**
         * Dar valor al X e Y de las lineas.
         */
        double x = (min+0.01) + (max - min) * Math.random();

        double y = (min+0.01) + (max - min) * Math.random();

        double x1 = (min+0.01) + (max - min) * Math.random();

        double y1 = (min+0.01) + (max - min) * Math.random();

        /**
         * Dar valor al azar de las velocidades de cada punto.
         */
        double velocidadX = 0.003 * Math.random();
        double x2 = x + velocidadX;
        double velocidadY = 0.005 * Math.random();
        double y2 = y + velocidadY;
        double velocidadX1 = velocidadX;
        double x3 = x1 + velocidadX1;
        double velocidadY1 = velocidadY;
        double y3 = y1 + velocidadY1;
        double velocidadX2 = velocidadX;
        double velocidadY2 = velocidadY;
        double velocidadX3 = velocidadX;
        double velocidadY3 = velocidadY;
        /**
         * Seleccionar el radio de las pelotas que irán al extremo de las lineas.
         */
        double radio = 0.01;
        //Color rojo = new Color(177, 0, 0);

        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);


        while (true) {
            StdDraw.enableDoubleBuffering();

            velocidadX = detectarColision(x,velocidadX,radio);
            velocidadY = detectarColision(y, velocidadY, radio);
            velocidadX1 = detectarColision(x1, velocidadX1, radio);
            velocidadY1 = detectarColision(y1, velocidadY1, radio);
            velocidadX2 = detectarColision(x2,velocidadX2,radio);
            velocidadY2 = detectarColision(y2, velocidadY2, radio);
            velocidadX3 = detectarColision(x3, velocidadX3, radio);
            velocidadY3 = detectarColision(y3, velocidadY3, radio);

            x += velocidadX;
            y += velocidadY;
            x1 += velocidadX1;
            y1 += velocidadY1;
            x2 += velocidadX2;
            y2 += velocidadY2;
            x3 += velocidadX3;
            y3 += velocidadY3;



            StdDraw.clear();
            dibujarLineas(x, y, x1, y1, velocidadX, velocidadY, velocidadX1, velocidadY1, radio, min, max);

            dibujarLineas(x2,y2,x3,y3,velocidadX2,velocidadY2,velocidadX3,velocidadY3,radio,min,max);
            StdDraw.show();


        }




    }
    public static void dibujarLineas(double x, double y, double x1, double y1, double velocidadX, double velocidadY, double velocidadX1, double velocidadY1, double radio, double min, double max) {
        //StdDraw.filledCircle(x,y,radio);
        StdDraw.line(x,y,x1,y1);
        //StdDraw.filledCircle(x1,y1,radio);
        }


    public static double detectarColision(double x, double velocidadX, double radio) {
        if (Math.abs(x + velocidadX) > 1.0) {
            StdDraw.setPenColor();
            return -velocidadX;
        }
        else {
            return velocidadX;
        }

    }
}