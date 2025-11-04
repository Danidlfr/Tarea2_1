package es.cifpcarlos3.Actividad2_2.dao.impl;

import es.cifpcarlos3.Actividad2_2.dao.ContinenteDAO;
import es.cifpcarlos3.Actividad2_2.dao.PaisDAO;

import java.sql.*;

public class ContinenteDAOMariaDB implements ContinenteDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/mapa_mundi";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void mostrarPaisesPorContinente() {
        String consulta = "SELECT count(*) AS 'cantidad', nombre_continente " +
                "FROM t_pais p JOIN t_continente c ON c.codigo = p.cod_continente " +
                "GROUP BY nombre_continente";
        int lineas = 0;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(consulta)){
            while (rs.next()){
                System.out.println(rs.getString("nombre_continente") + ": " + rs.getInt("cantidad"));
                lineas++;
            }
            System.out.println("Lineas leidas: "+ lineas);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
