import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;

public class main2 {
    public static void main(String[] args) {
        double min = -1.0;
        double max = 1.0;
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);

        double x = min + (max - min) * Math.random();
        double y = min + (max - min) * Math.random();
        double x1 = min + (max - min) * Math.random();
        double y1 = min + (max - min) * Math.random();
        double radio = 0.005;
        double variacionVelocidad = Math.random();
        double velocidadX = 0.005 * variacionVelocidad;
        double velocidadY = 0.01 * variacionVelocidad;
        double velocidadX1 = velocidadX;
        double velocidadY1 = velocidadY;
        double velocidadX2 = velocidadX;
        double velocidadY2 = velocidadY;
        double velocidadX3 = velocidadX;
        double velocidadY3 = velocidadY;
        double velocidadX4 = velocidadX;
        double velocidadY4 = velocidadY;
        double velocidadX5 = velocidadX;
        double velocidadY5 = velocidadY;
        double x2 = x - 0.02;
        double y2 = y - 0.05;
        double x3 = x1 - 0.02;
        double y3 = y1 - 0.05;
        double x4 = x2 - 0.02;
        double y4 = y2 - 0.05;
        double x5 = x3 - 0.02;
        double y5 = y3 - 0.05;
        StdDraw.enableDoubleBuffering();

        while (true) {


            velocidadX = detectarColision(x, velocidadX, radio);
            velocidadY = detectarColision(y, velocidadY, radio);
            velocidadX1 = detectarColision(x1, velocidadX1, radio);
            velocidadY1 = detectarColision(y1, velocidadY1, radio);
            velocidadX2 = detectarColision(x2, velocidadX2, radio);
            velocidadY2 = detectarColision(y2, velocidadY2, radio);
            velocidadX3 = detectarColision(x3, velocidadX3, radio);
            velocidadY3 = detectarColision(y3, velocidadY3, radio);
            velocidadX4 = detectarColision(x4, velocidadX4, radio);
            velocidadY4 = detectarColision(y4, velocidadY4, radio);
            velocidadX5 = detectarColision(x5, velocidadX5, radio);
            velocidadY5 = detectarColision(y5, velocidadY5, radio);



            x += velocidadX;
            y += velocidadY;
            x1 += velocidadX1;
            y1 += velocidadY1;
            x2 += velocidadX2;
            x3 += velocidadX3;
            y2 += velocidadY2;
            y3 += velocidadY3;
            x4 += velocidadX4;
            x5 += velocidadX5;
            y4 += velocidadY4;
            y5 += velocidadY5;

            StdDraw.setPenColor(Color.black);
            StdDraw.filledSquare(min, max, 2.0);
            StdDraw.setPenColor(Color.YELLOW);
            StdDraw.filledCircle(x,y,radio);
            StdDraw.filledCircle(x1,y1,radio);
            StdDraw.line(x,y,x1,y1);
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.filledCircle(x2,y2,radio);
            StdDraw.filledCircle(x3,y3,radio);
            StdDraw.line(x2,y2,x3,y3);
            StdDraw.setPenColor(Color.white);
            StdDraw.filledCircle(x4,y4,radio);
            StdDraw.filledCircle(x5,y5,radio);
            StdDraw.line(x4,y4,x5,y5);



            StdDraw.show();
            StdDraw.clear();
        }
    }

    public static double detectarColision(double x, double velocidadX, double radio) {
        if (Math.abs(x + velocidadX) > 1.0 - (radio + (radio/2))) {
            velocidadX = -velocidadX;
        }
        return velocidadX;
    }
}