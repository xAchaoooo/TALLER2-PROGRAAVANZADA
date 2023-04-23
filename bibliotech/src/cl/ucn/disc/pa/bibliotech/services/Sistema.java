/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.services;

import cl.ucn.disc.pa.bibliotech.model.Calificacion;
import cl.ucn.disc.pa.bibliotech.model.Libro;
import cl.ucn.disc.pa.bibliotech.model.Socio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.princeton.cs.stdlib.StdOut;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Sistema.
 *
 * @author Programacion Avanzada.
 */
public final class Sistema {

    /**
     * Procesador de JSON.
     */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * The list of Socios.
     */
    private Socio[] socios;

    /**
     * The list of Libros.
     */
    private Libro[] libros;

    private boolean[] librosDisponibles;

    private Calificacion[] calificacionLibros;

    /**
     * Socio en el sistema.
     */
    private Socio socio;

    /**
     * The Sistema.
     */
    public Sistema() throws IOException {

        // no hay socio logeado.
        this.socios = new Socio[0];
        this.libros = new Libro[0];
        this.socio = null;

        // carga de los socios y libros.
        try {
            this.cargarInformacion();
        } catch (FileNotFoundException ex) {
            // no se encontraron datos, se agregar los por defecto.

            // creo un socio
            this.socios = Utils.append(this.socios, new Socio("John", "Doe", "john.doe@ucn.cl", 1, "john123"));

            // creo un libro y lo agrego al arreglo de libros.
            this.libros = Utils.append(this.libros, new Libro("1491910771", "Head First Java: A Brain-Friendly Guide", " Kathy Sierra", "Programming Languages"));

            // creo otro libro y lo agrego al arreglo de libros.
            this.libros = Utils.append(this.libros, new Libro("1491910771", "Effective Java", "Joshua Bloch", "Programming Languages"));

        } finally {
            // guardo la informacion.
            this.guardarInformacion();
            //
            librosDisponibles = new boolean[libros.length];
            calificacionLibros = new Calificacion[libros.length];
            for (int i = 0; i < libros.length; i++) {
                librosDisponibles[i] = true;
                calificacionLibros[i] = new Calificacion();
            }
        }

    }

    /**
     * Activa (inicia sesion) de un socio en el sistema.
     *
     * @param numeroDeSocio a utilizar.
     * @param contrasenia   a validar.
     */
    public void iniciarSession(final int numeroDeSocio, final String contrasenia) {

        // el numero de socio siempre es positivo.
        if (numeroDeSocio <= 0) {
            throw new IllegalArgumentException("El numero de socio no es valido!");
        }

        // Buscar el socio dado su numero y guardar su lugar.
        int lugar = buscarSocio(numeroDeSocio);

        // Verificar su clave
        boolean inicio = verificarClave(lugar, contrasenia);

        // Asigna el Socio encontrado siempre y cuando su contraseña sea correcta.
        if (inicio == true) {
            this.socio = socios[lugar];
        } else {
            throw new IllegalArgumentException("Contraseña Incorrecta!");
        }


    }

    /**
     * Cierra la session del Socio.
     */
    public void cerrarSession() {
        this.socio = null;
    }

    /**
     * Metodo que mueve un libro de los disponibles y lo ingresa a un Socio.
     *
     * @param isbn del libro a prestar.
     */
    public void realizarPrestamoLibro(final String isbn) throws IOException {
        // el socio debe estar activo.
        if (this.socio == null) {
            throw new IllegalArgumentException("Socio no se ha logeado!");
        }

        // busco el libro.
        Libro libro = this.buscarLibro(isbn);

        // si no lo encontre, lo informo.
        if (libro == null) {
            //throw new IllegalArgumentException("Libro con isbn " + isbn + " no existe o no se encuentra disponible.");
            System.out.println("Libro no encontrado.");
            return;
        }


        // agrego el libro al socio.
        this.socio.agregarLibro(libro);

        // Elimina el libro del catálogo.
        this.eliminarLibro(libro);

        // se actualiza la informacion de los archivos
        this.guardarInformacion();

    }

    /**
     * Obtiene un String que representa el listado completo de libros disponibles.
     *
     * @return the String con la informacion de los libros disponibles.
     */
    public String obtegerCatalogoLibros() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.libros.length; i++) {
            if (!this.librosDisponibles[i]) continue;
            sb.append("Titulo    : ").append(this.libros[i].getTitulo()).append("\n");
            sb.append("Autor     : ").append(this.libros[i].getAutor()).append("\n");
            sb.append("ISBN      : ").append(this.libros[i].getIsbn()).append("\n");
            sb.append("Categoria : ").append(this.libros[i].getCategoria()).append("\n");
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Metodo que busca un libro en los libros disponibles.
     *
     * @param isbn a buscar.
     * @return el libro o null si no fue encontrado.l
     */
    public Libro buscarLibro(final String isbn) {
        // recorro el arreglo de libros.
        for (int i = 0; i < this.libros.length; i++) {
            // si lo encontre, retorno el libro.
            if (this.libros[i].getIsbn().equals(isbn) && this.librosDisponibles[i]) {
                return this.libros[i];
            }
        }
        // no lo encontre, retorno null.
        return null;
    }

    /**
     * BUSCA EL INDICE DE UN LIBRO
     *
     * @param isbn PARAMETRO QUE REPRESENTA EL ISBN DEL LIBRO
     * @return RETORNA LA POSICIÓN DEL LIBRO ENCONTRADO
     */
    public int buscarIndiceLibros(String isbn) {
        for (int i = 0; i < this.libros.length; i++) {
            if (libros[i].getIsbn().equals(isbn)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Lee los archivos libros.json y socios.json.
     *
     * @throws FileNotFoundException si alguno de los archivos no se encuentra.
     */
    private void cargarInformacion() throws FileNotFoundException {

        // trato de leer los socios y los libros desde el archivo.
        this.socios = GSON.fromJson(new FileReader("socios.json"), Socio[].class);
        this.libros = GSON.fromJson(new FileReader("libros.json"), Libro[].class);
    }

    /**
     * Guarda los arreglos libros y socios en los archivos libros.json y socios.json.
     *
     * @throws IOException en caso de algun error.
     */
    private void guardarInformacion() throws IOException {

        // guardo los socios.
        try (FileWriter writer = new FileWriter("socios.json")) {
            GSON.toJson(this.socios, writer);
        }

        // guardo los libros.
        try (FileWriter writer = new FileWriter("libros.json")) {
            GSON.toJson(this.libros, writer);
        }

    }

    /**
     * OBTIENE LOS DATOS DEL SOCIO LOGEADO.
     *
     * @return RETORNA LOS DATOS.
     */
    public String obtenerDatosSocioLogeado() {
        if (this.socio == null) {
            throw new IllegalArgumentException("No hay un Socio logeado");
        }

        return "Nombre: " + this.socio.getNombreCompleto() + "\n"
                + "Correo Electronico: " + this.socio.getCorreoElectronico();
    }

    /**
     * BUSCA LA POSICIÓN DEL SOCIO
     *
     * @param numeroSocio NUMERO DEL SOCIO
     * @return RETORNA LA POSICIÓN DEL SOCIO ENCONTRADO
     */
    public int buscarSocio(int numeroSocio) {
        boolean iniciado = false;
        for (int i = 0; i < socios.length; i++) {
            if (this.socios[i] != null && this.socios[i].getNumeroDeSocio() == numeroSocio) {
                return i;
            }
        }
        throw new IllegalArgumentException("El numero de socio no es valido!");
    }

    /**
     * VERIFICA SI LA CLAVE ES LA CORRECTA
     *
     * @param lugar       LUGAR DEL SOCIO
     * @param contrasenia CONTRASEÑA INGRESADA
     * @return RETORNA SI ES VERDADERO O SI ES FALSO
     */
    public boolean verificarClave(int lugar, String contrasenia) {
        if (contrasenia.equals(this.socios[lugar].getContrasenia())) {
            System.out.println("Inicio de sesión correcto !");
            return true;
        }
        return false;
    }

    /**
     * METODO PARA CAMBIAR LA CONTRASEÑA
     *
     * @param password CONTRASEÑA INGRESADA
     */
    public void cambiarPassword(String password) {

        if (password == null || password.length() == 0) {
            System.out.println("Ingrese una contrasenia válida.");
        } else {
            this.socio.setContrasenia(password);
        }
    }

    /**
     * METODO PARA CAMBIAR EL CORREO
     *
     * @param correo CORREO INGRESADO
     */
    public void cambiarCorreo(String correo) {
        try {
            Utils.validarEmail(correo);
            socio.setCorreoElectronico(correo);
            StdOut.println("Correo cambiado con éxito!\n");
        } catch (Exception e) {
            System.out.println("correo no válido.");
        }

    }

    /**
     * METODO PARA ELIMINAR UN LIBRO DEL CATÁLOGO
     *
     * @param libro LIBRO BUSCADO
     */
    public void eliminarLibro(Libro libro) {
        var indice = -1;
        for (int i = 0; i < this.libros.length; i++) {
            if (libro.getIsbn().equals(this.libros[i].getIsbn())) {
                indice = i;
                break;
            }
        }
        if (indice > -1) {
            librosDisponibles[indice] = false;
            System.out.println("Libro eliminado.");
            return;
        }
        System.out.println("Error, libro no encontrado.");
    }

    /**
     * VERIFICA QUE LA CALIFICACIÓN SEA CORRECTA
     *
     * @param calificacion CALIFICACION INGRESADA
     * @throws IOException ERROR EN CASO DE QUE LA CALIFICACION NO SEA VÁLIDA
     */
    public void verificarCalificacion(int calificacion) throws IOException {
        if (calificacion < 1 || calificacion > 5) {
            throw new IOException("Ingrese una calificación válida.");
        }
    }

    /**
     * METODO PARA CALIFICAR UN LIBRO
     *
     * @param isbn         ISBN DEL LIBRO A CALIFICAR
     * @param calificacion CALIFICACIÓN
     */
    public void calificarLibro(String isbn, int calificacion) {
        int indexLibro = buscarIndiceLibros(isbn);
        if (indexLibro >= 0) {

            calificacionLibros[indexLibro].actualizarCalificacion(calificacion);
            System.out.println("Ahora la calificación del libro es: " + calificacionLibros[indexLibro].getPromedio());
        }
    }


}


