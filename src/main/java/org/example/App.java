package org.example;

import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    private static final String URL = "jdbc:mariadb://localhost:3306/mapa_mundi";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige la opcion:");
        System.out.println("1) Listar países sin capital");
        System.out.println("2) Nº de países por continente");
        System.out.println("3) Países de Europa");
        System.out.println("4) Capitales que empiezan por “San”");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                consulta1();
                break;
            case 2:
                consulta2();
                break;
            case 3:
                consulta3();
                break;
            case 4:
                consulta4();
                break;
        }
    }
    public static void consulta1(){
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
    public static void consulta2(){
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
    public static void consulta3(){
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
    public static void consulta4(){
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