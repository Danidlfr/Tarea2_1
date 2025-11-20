package es.cifpcarlos3.Actividad2_4;

import es.cifpcarlos3.Actividad2_3.model.Pais;
import es.cifpcarlos3.Actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.Actividad2_4.dao.impl.ClienteDAOImpl;
import es.cifpcarlos3.Actividad2_4.dao.impl.CuentaDAOImpl;
import es.cifpcarlos3.Actividad2_4.model.Cliente;
import es.cifpcarlos3.Actividad2_4.model.Cuenta;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        ClienteDAOImpl clienteDAO = new ClienteDAOImpl(db);
        CuentaDAOImpl  cuentaDAO = new CuentaDAOImpl(db);

        Scanner sc = new Scanner(System.in);
        int opcion = 999;
        while(opcion != 0){
            mostrarMenu();
            opcion = leerInt(sc);
            switch (opcion) {
                case 1:
                    List<Cliente> clientes = clienteDAO.obtenerClientes();
                    System.out.printf("%-5s %-11s %-15s %-12s %-6s", "ID", "DNI", "NOMBRE", "TELÉFONO", "EMAIL");
                    System.out.println("\n-------------------------------------------------------------------------");
                    for (Cliente cliente : clientes) {
                        System.out.printf("%-5d %-11s %-15s %-12s %-24s%n", cliente.getId_cliente(), cliente.getDni(), cliente.getNombre(), cliente.getTelefono(), cliente.getEmail());
                    }
                    break;
                case 2:
                    List<Cuenta> cuentas = cuentaDAO.obtenerCuentas();
                    System.out.printf("%-5s %-20s %-10s %-5s", "ID", "Nº CUENTA", "TITULAR", "SALDO");
                    System.out.println("\n-------------------------------------------------------------------------");
                    for (Cuenta cuenta : cuentas) {
                        System.out.printf("%-5s %-20s %-10s %6s%n", cuenta.getId_cuenta(), cuenta.getNumero_cuenta(), cuenta.getId_cliente(), cuenta.getSaldo());
                    }
                    System.out.println("\n(" + cuentas.size() + " cuentas)\n");
                    break;
                case 3:
                    clienteDAO.insertarCliente();
                    break;
                case 4:
                    cuentaDAO.insertarCuenta();
                    break;
                case 5:
                    cuentaDAO.actualizarSaldoCuenta();
                    break;
                case 6:
                    cuentaDAO.transferenciaEntreCuentas();
                    break;
                case 7:
                    clienteDAO.eliminarCliente();
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
