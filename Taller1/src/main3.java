//IGNACIO ALEJANDRO VIELMA DURAN
//21.624.649-7

import edu.princeton.cs.stdlib.StdDraw;

import java.awt.*;

//CLASE PRINCIPAL
public class main3 {
    public static void main(String[] args) {
        //DEFINIR VALORES INICIALES DEL CANVA
        double min = -1.0;
        double max = 1.0;
        StdDraw.setXscale(min, max);
        StdDraw.setYscale(min, max);
        //GENERAR UNA VELOCIDAD AL AZAR
        double variacionVel = generateVelocity();
        //GENERAR LINEAS
        MyLine line = new MyLine(generateRandomValue(min, max), generateRandomValue(min, max), generateRandomValue(min, max), generateRandomValue(min, max),0.005*variacionVel,0.008*variacionVel);
        MyLine line1 = copyLine(line);
        MyLine line2 = copyLine(line1);
        MyLine line3 = copyLine(line2);
        MyLine line4 = copyLine(line3);
        MyLine line5 = copyLine(line4);
        StdDraw.enableDoubleBuffering();

        //CICLO INFINITO
        while (true) {
            StdDraw.clear();
            //DEFINIR COLOR DEL FONDO
            StdDraw.setPenColor(Color.black);
            StdDraw.filledSquare(min, max, 2.0);
            //DIBUJAR LINEAS
            StdDraw.setPenColor(Color.green);
            StdDraw.line(line.getX(), line.getY(), line.getX1(), line.getY1());
            StdDraw.setPenColor(Color.cyan);
            StdDraw.line(line1.getX(), line1.getY(), line1.getX1(), line1.getY1());
            StdDraw.setPenColor(Color.red);
            StdDraw.line(line2.getX(), line2.getY(), line2.getX1(), line2.getY1());
            StdDraw.setPenColor(Color.yellow);
            StdDraw.line(line3.getX(), line3.getY(), line3.getX1(), line3.getY1());
            StdDraw.setPenColor(Color.pink);
            StdDraw.line(line4.getX(), line4.getY(), line4.getX1(), line4.getY1());
            StdDraw.setPenColor(Color.white);
            StdDraw.line(line5.getX(), line5.getY(), line5.getX1(), line5.getY1());

            //ACTUALIZAR VALORES DE X E Y
            line.updateMyValues();
            line1.updateMyValues();
            line2.updateMyValues();
            line3.updateMyValues();
            line4.updateMyValues();
            line5.updateMyValues();
            //MOSTRAR PANTALLA
            StdDraw.show();
        }

    }

    /**
     * Generar número al azar
     * @param min valor mínimo
     * @param max valor máximo
     * @return
     */
    public static double generateRandomValue(double min, double max) {
        return min + ((max-0.4) - min) * Math.random();
    }

    /**
     * Generar velocidad al azar
     * @return
     */
    public static double generateVelocity() {
        return Math.random();
    }

    /**
     * detecta colision futura
     * @param value posicion
     * @param speed velocidad
     * @return
     */
    public static double detectarColision(double value, double speed) {
        if (Math.abs(value+speed) >= 1) {
            speed = -speed;
        }
        return speed;
    }

    /**
     * crea una copia de la linea y la desplaza
     * @param line linea a copiar
     * @return
     */
    public static MyLine copyLine(MyLine line) {
        MyLine newLine = new MyLine(line.getX(), line.getY(), line.getX1(), line.getY1(), line.getVelocidadX(), line.getVelocidadY());
        newLine.updateMyPosition();
        return newLine;
    }

}
