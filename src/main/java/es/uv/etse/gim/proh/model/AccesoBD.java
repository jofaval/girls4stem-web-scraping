package es.uv.etse.gim.proh.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import com.capgemini.catedrauv.girls4stem.domain.models.Expert;

/**
 * Clase que se encarga de implementar la persistencia del modelo en la base.
 * 
 * @author <a href="mailto:raul.penya@uv.es">Ra&uacute;l Pe&ntilde;a-Ortiz</a>
 * @version 1.0
 */
public final class AccesoBD {

    // Constantes para la conexión a la base de datos

    // El driver JDBC de MariaDB es org.mariadb.jdbc.Driver
    private static String JDBC_DRIVER = "org.mariadb.jdbc.Driver";

    // girls4stem es el nombre de la base de datos que debemos crear con
    // anterioridad.
    private static String DB_URL = "jdbc:mariadb://localhost:3306/girls4stem";

    // El usuario root y su clave son los que se puso al instalar MariaDB.

    /**
     * Usuario conexión a base de datos
     */
    private static String USER = "root";

    /**
     * Contraseña conexión a base de datos
     */
    private static String PASS = "DawLab";

    /**
     * Instancia única del singleton
     */
    private static AccesoBD instanciaUnica = null;

    /**
     * Conexión a la base de datos
     */
    private Connection conexionBD = null;

    /**
     * Devuelve la instancia única del sigleton
     * 
     * @return Instancia única del singleton
     */
    public static AccesoBD getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new AccesoBD();
        }
        return instanciaUnica;
    }

    /**
     * Constructor privado para el singleton
     */
    private AccesoBD() {
        abrirConexionBD();
    }

    /**
     * Abre la conexión a la base de datos
     */
    public void abrirConexionBD() {
        if (conexionBD == null) {

            try {
                Class.forName(JDBC_DRIVER);
                conexionBD = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (Exception e) {
                System.err.println("No se ha podido conectar a la base de datos");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Comprueba si se ha podido abrir la conexión a la base de datos
     * 
     * @return true si se ha podido abrir la conexión a la base de datos
     */
    public boolean comprobarAcceso() {
        abrirConexionBD();
        return (conexionBD != null);
    }

    /**
     * Inserta una experta en la base de datos
     * 
     * @param e Datos de la experta
     * @throws Exception Si no se puede insertar la experta
     */
    public void insertExpert(Expert e) throws Exception {

        // To-Do: Implementar la inserción de una experta en la base de datos

        throw new UnsupportedOperationException("Not yet implemented");

    }

    /**
     * Inserta una lista de expertas en la base de datos
     * 
     * @param l Lista de expertas
     * @return Número de expertas insertadas
     */
    public int insertExperts(List<Expert> e) {

        // To-Do: Implementar la inserción de la lista de expertas en la base de datos

        return 0;
    }
}