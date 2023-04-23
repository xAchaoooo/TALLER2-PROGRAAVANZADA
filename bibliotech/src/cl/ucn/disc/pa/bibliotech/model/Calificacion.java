/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.model;

/**
 * CLASE QUE REPRESENTA LA CALIFICACION DE LOS LIBROS
 */
public class Calificacion {
    /**
     * PROMEDIO
     */
    private double promedio;
    /**
     * CANTIDAD DE CALIFICACIONES POR LIBRO
     */
    private int cantidadCalificaciones;
    /**
     * SUMA DE LAS CALIFICACIONES DEL LIBRO
     */
    private int sumaCalificaciones;

    /**
     * CONSTRUCTOR CALIFICACION BASE
     */
    public Calificacion() {
        promedio = 0;
        sumaCalificaciones = 0;
        cantidadCalificaciones = 0;

    }

    /**
     * OBTIENE EL PROMEDIO
     *
     * @return EL PROMEDIO
     */
    public double getPromedio() {
        return promedio;
    }

    /**
     * SETEA EL PROMEDIO
     *
     * @param promedio PROMEDIO
     */
    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    /**
     * OBTIENE LA CANTIDAD DE CALIFICACIONES
     *
     * @return CANTIDAD CALIFICACIONES
     */
    public int getCantidadCalificaciones() {
        return cantidadCalificaciones;
    }

    /**
     * SETEA LA CANTIDAD DE CALIFICACIONES DE UN LIBRO
     *
     * @param cantidadCalificaciones
     */
    public void setCantidadCalificaciones(int cantidadCalificaciones) {
        this.cantidadCalificaciones = cantidadCalificaciones;
    }

    /**
     * OBTIENE LA SUMA DE LAS CALIFICACIONES DEL LIBRO
     *
     * @return
     */
    public int getSumaCalificaciones() {
        return sumaCalificaciones;
    }

    /**
     * SETEA LA SUMA DE CALIFICACIONES
     *
     * @param sumaCalificaciones
     */
    public void setSumaCalificaciones(int sumaCalificaciones) {
        this.sumaCalificaciones = sumaCalificaciones;
    }

    /**
     * ACTUALIZA LA CALIFICACION DEL LIBRO EN CUESTIÓN
     *
     * @param calificacion CALIFICACIÓN
     */
    public void actualizarCalificacion(int calificacion) {
        cantidadCalificaciones++;
        sumaCalificaciones += calificacion;
        promedio = (double) sumaCalificaciones / cantidadCalificaciones;
    }
}
