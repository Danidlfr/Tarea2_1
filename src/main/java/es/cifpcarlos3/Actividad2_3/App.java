package es.cifpcarlos3.Actividad2_3;

import es.cifpcarlos3.Actividad2_3.dao.impl.ContinenteDAOMariaDB;
import es.cifpcarlos3.Actividad2_3.dao.impl.PaisDAOMariaDB;
import es.cifpcarlos3.Actividad2_3.model.Pais;
import es.cifpcarlos3.Actividad2_3.util.DatabaseConnection;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var dbConnection = new DatabaseConnection();
        PaisDAOMariaDB pDAO =  new PaisDAOMariaDB(dbConnection);
        ContinenteDAOMariaDB cDAO =  new ContinenteDAOMariaDB(dbConnection);
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige la opcion:");
        System.out.println("1) Listar países del continente América con capital que empiece por 'Sa'");
        System.out.println("2) Insertar nuevo continente 'Antártida'");
        System.out.println("3) Actualizar capital del país con id 107 -> 'Capital city'");
        System.out.println("4) Eliminar continente con código '06'");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                List<Pais> paises = pDAO.listarPaises();
                for(Pais pais: paises){
                    System.out.println(pais.getNombre() + " (" + pais.getId() + ") pertenece al continente America (" + pais.getCont_cod() + ")");
                }
                break;
            case 2:
                cDAO.insertarContinente();
                break;
            case 3:
                pDAO.actualizarPais();
                break;
            case 4:
                cDAO.eliminarContinente();
                break;
        }
    }
}
