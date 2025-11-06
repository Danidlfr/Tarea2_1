package es.cifpcarlos3.Actividad2_3.dao.impl;

import es.cifpcarlos3.Actividad2_3.dao.ContinenteDAO;
import es.cifpcarlos3.Actividad2_3.util.DatabaseConnection;
import lombok.Data;

import java.sql.SQLException;

@Data
public class ContinenteDAOMariaDB implements ContinenteDAO {
    private final DatabaseConnection bd;
    public ContinenteDAOMariaDB(DatabaseConnection bd) {
        this.bd = bd;
    }

    @Override
    public void insertarContinente() {
        String consulta = "INSERT INTO t_continente (codigo, nombre_continente) " +
                "VALUES ('06', 'Antártida');";
        try(var conn = bd.getConn();
            var sentencia = conn.prepareStatement(consulta)){
            int filas = sentencia.executeUpdate();
            System.out.println("Insertado continente Antártida (06). Filas afectadas: " + filas);
        }catch (SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }
    }

    @Override
    public void eliminarContinente() {
        String consulta1 = "DELETE FROM t_pais WHERE cod_continente = '06';";
        String consulta2 = "DELETE FROM t_continente WHERE codigo = '06';";

        try(var conn = bd.getConn();
            var sentencia1 = conn.prepareStatement(consulta1);
            var sentencia2 = conn.prepareStatement(consulta2)){
            int filas = sentencia1.executeUpdate();
            int filas2 = sentencia2.executeUpdate();
            System.out.println("Eliminado continente 06. Filas afectadas -> paises: " + filas + ", continente: " + filas2);

        }catch (SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }
    }
}
