package es.cifpcarlos3.Actividad2_2.dao.impl;

import es.cifpcarlos3.Actividad2_2.dao.ContinenteDAO;
import es.cifpcarlos3.Actividad2_2.dao.PaisDAO;

import java.sql.*;

public class PaisDAOMariaDB implements PaisDAO {
    private static final String URL = "jdbc:mariadb://localhost:3306/mapa_mundi";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void paisesSinCapital() {
        String consulta = "SELECT nombre_pais FROM t_pais WHERE capital IS NULL";
        int lineas = 0;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(consulta)){
            while (rs.next()){
                System.out.println(rs.getString("nombre_pais"));
                lineas++;
            }
            System.out.println("Lineas leidas: "+ lineas);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void paisesEuropa() {
        String consulta = "SELECT nombre_pais " +
                "FROM t_pais WHERE cod_continente = (SELECT codigo FROM t_continente WHERE nombre_continente = 'Europa')";
        int lineas = 0;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(consulta)){
            while (rs.next()){
                System.out.println(rs.getString("nombre_pais"));
                lineas++;
            }
            System.out.println("Lineas leidas: "+ lineas);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void mostrarCapitalesEmpezadasSan() {
        String consulta = "SELECT capital FROM t_pais WHERE capital LIKE 'San%'";
        int lineas = 0;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(consulta)){
            while (rs.next()){
                System.out.println(rs.getString("capital"));
                lineas++;
            }
            System.out.println("Lineas leidas: "+ lineas);
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }
}
