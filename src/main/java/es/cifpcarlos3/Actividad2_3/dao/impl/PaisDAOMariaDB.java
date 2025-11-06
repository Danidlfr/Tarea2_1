package es.cifpcarlos3.Actividad2_3.dao.impl;

import es.cifpcarlos3.Actividad2_3.dao.PaisDAO;
import es.cifpcarlos3.Actividad2_3.model.Pais;
import es.cifpcarlos3.Actividad2_3.util.DatabaseConnection;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Data
public class PaisDAOMariaDB implements PaisDAO {
    private final DatabaseConnection bd;
    public PaisDAOMariaDB(DatabaseConnection bd) {
        this.bd = bd;
    }

    @Override
    public List<Pais> listarPaises() {
        List<Pais> paises = new ArrayList<>();
        String consulta ="SELECT p.identificador, p.nombre_pais, p.capital," +
                " c.codigo AS cod_continente," +
                " c.nombre_continente AS nombre_continente" +
                " FROM t_pais p"+
                " JOIN t_continente c ON p.cod_continente = c.codigo" +
                " WHERE c.nombre_continente = 'América'" +
                " AND p.capital LIKE 'Sa%'" +
                " ORDER BY p.nombre_pais;";
        try(var conn = bd.getConn();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(consulta)){
            while(rs.next()){
                Pais pais = new Pais(rs.getInt("cod_continente"), rs.getInt("identificador"), rs.getString("nombre_pais"), rs.getString("capital"));
                paises.add(pais);
            }
        }catch (SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }
        return paises;
    }

    @Override
    public void actualizarPais() {
        String consulta = "UPDATE t_pais " +
                "SET capital = 'Capital city' " +
                "WHERE identificador = 107;";

        try(var conn = bd.getConn();
            var sentencia = conn.prepareStatement(consulta)){
            int filas = sentencia.executeUpdate();
            System.out.println("Actualizado país id=107. Filas afectadas: " + filas);
        }catch (SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }
    }
}
