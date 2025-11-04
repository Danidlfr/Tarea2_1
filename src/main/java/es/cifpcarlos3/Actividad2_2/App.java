package es.cifpcarlos3.Actividad2_2;

import es.cifpcarlos3.Actividad2_2.dao.impl.ContinenteDAOMariaDB;
import es.cifpcarlos3.Actividad2_2.dao.impl.PaisDAOMariaDB;

import java.sql.*;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        PaisDAOMariaDB pDAO =  new PaisDAOMariaDB();
        ContinenteDAOMariaDB cDAO =  new ContinenteDAOMariaDB();
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige la opcion:");
        System.out.println("1) Listar países sin capital");
        System.out.println("2) Nº de países por continente");
        System.out.println("3) Países de Europa");
        System.out.println("4) Capitales que empiezan por “San”");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                pDAO.paisesSinCapital();
                break;
            case 2:
                cDAO.mostrarPaisesPorContinente();
                break;
            case 3:
                pDAO.paisesEuropa();
                break;
            case 4:
                pDAO.mostrarCapitalesEmpezadasSan();
                break;
        }
    }
}
