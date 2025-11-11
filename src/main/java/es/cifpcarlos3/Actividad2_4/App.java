package es.cifpcarlos3.Actividad2_4;

import es.cifpcarlos3.Actividad2_3.model.Pais;
import es.cifpcarlos3.Actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.Actividad2_4.dao.impl.ClienteDAOImpl;
import es.cifpcarlos3.Actividad2_4.model.Cliente;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        ClienteDAOImpl clienteDAO = new ClienteDAOImpl(db);

        Scanner sc = new Scanner(System.in);
        int opcion = 999;
        while(opcion != 0){
            mostrarMenu();
            opcion = leerInt(sc);
            switch (opcion) {
                case 1:
                    List<Cliente> clientes = clienteDAO.obtenerClientes();
                    for(Cliente cliente : clientes) {
                        System.out.println(cliente.toString());
                    }
                    System.out.println("("+ clientes.size() + " Clientes)");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }

    }

    public static void mostrarMenu(){
        System.out.println("Elige la opcion:");
        System.out.println("1) Listar todos los clientes");
        System.out.println("2) Listar todas las cuentas con su titular");
        System.out.println("3) Insertar nuevo cliente");
        System.out.println("4) Insertar nueva cuenta para un cliente");
        System.out.println("5) Actualizar saldo de una cuenta");
        System.out.println("6) Transferir saldo entre dos cuentas");
        System.out.println("7) Eliminar cliente (si no tiene cuentas)");
        System.out.println("0) Salir");
    }

    public static int leerInt(Scanner sc){
        int opcion = 10;
        try{
            opcion = Integer.parseInt(sc.nextLine());
        }catch(NumberFormatException e){
            System.out.println("Introduzca un numero");
        }
        return opcion;
    }
    public static BigDecimal leerBigDecimal(Scanner sc){
        BigDecimal decimal = BigDecimal.ZERO;
        try{
            decimal = sc.nextBigDecimal();
        }catch(NumberFormatException e){
            System.out.println("Introduzca un int");
        }
        return decimal;
    }
}
