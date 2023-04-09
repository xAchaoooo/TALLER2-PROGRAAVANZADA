public class MyLine {
    /**
     * coordenadas de las lineas y velocidades
     */
    private double x;
    private double y;
    private double x1;
    private double y1;
    private double velocidadX;
    private double velocidadY;
    private double velocidadX1;
    private double velocidadY1;


    /**
     * constructor
     * @param x
     * @param y
     * @param x1
     * @param y1
     * @param velocidadX
     * @param velocidadY
     */
    public MyLine(double x, double y, double x1, double y1, double velocidadX, double velocidadY) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.velocidadX1 = velocidadX;
        this.velocidadY1 = velocidadY;
    }

    /**
     * Metodo que actualiza la posicion y velocidad de las lineas
     */
    public void updateMyValues() {
        setVelocidadX(main3.detectarColision(x, velocidadX));
        setX(x+velocidadX);
        setVelocidadY(main3.detectarColision(y, velocidadY));
        setY(y+velocidadY);
        setVelocidadX1(main3.detectarColision(x1, velocidadX1));
        setX1(x1+velocidadX1);
        setVelocidadY1(main3.detectarColision(y1, velocidadY1));
        setY1(y1+velocidadY1);
    }

    /**
     * Metodo que actualiza la posición inicial de la linea
     */
    public void updateMyPosition() {
        setVelocidadX(main3.detectarColision(x, velocidadX));
        setX(x-velocidadX*20);
        setVelocidadY(main3.detectarColision(y, velocidadY));
        setY(y-velocidadY*20);
        setVelocidadX1(main3.detectarColision(x1, velocidadX1));
        setX1(x1-velocidadX1*20);
        setVelocidadY1(main3.detectarColision(y1, velocidadY1));
        setY1(y1-velocidadY1*20);
    }

    /**
     * getters y setters
     * @return
     */
    public double getX() {
        return this.x;
    }

    public void setX(double value)   {
        this.x = value;
    }

    public double getY() {
        return y;
    }

    public void setY(double value) {
        this.y = value;
    }

    public double getX1() {
        return x1;
    }

    public void setX1(double value) {
        this.x1 = value;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double value) {
        this.y1 = value;
    }

    public double getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(double value) {
        this.velocidadX = value;
    }

    public double getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(double value) {
        this.velocidadY = value;
    }

    public double getVelocidadX1() {
        return velocidadX1;
    }

    public double getVelocidadY1() {
        return velocidadY1;
    }

    public void setVelocidadX1(double value) {
        this.velocidadX1 = value;
    }

    public void setVelocidadY1(double value) {
        this.velocidadY1 = value;
    }
}
