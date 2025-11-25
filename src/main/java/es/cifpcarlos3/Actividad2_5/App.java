package es.cifpcarlos3.Actividad2_5;

import es.cifpcarlos3.Actividad2_5.dao.impl.CuentaDAOImpl;
import es.cifpcarlos3.Actividad2_5.dao.impl.UsuarioDAOImpl;
import es.cifpcarlos3.Actividad2_5.model.Cuenta;
import es.cifpcarlos3.Actividad2_5.util.DatabaseConnection;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        CuentaDAOImpl cuentaDAO = new CuentaDAOImpl();
        String dni;
        String password;
        try {
            int opcion;
            Scanner leer = new Scanner(System.in);
            do {
                System.out.println("Elige opción:");
                System.out.println("1) Crear tabla de usuarios");
                System.out.println("2) Importar usuarios a partir de clientes");
                System.out.println("3) Listar cuentas de un usuario con dni y password (SEGURO)");
                System.out.println("4) Listar cuentas de un usuario con dni y password (INSEGURO)");
                System.out.println("5) Eliminar cuenta de un usuario con dni, password e Id");
                System.out.println("0) Salir");
                System.out.print("Opción: ");
                opcion = leer.nextInt();
                switch (opcion) {
                    case 1:
                        usuarioDAO.crearTabla();
                        break;
                    case 2:
                        usuarioDAO.insertarUsuario(cuentaDAO.obtenerDNI());
                        break;
                    case 3:
                        System.out.println("DNI: ");
                        dni = sc.nextLine();
                        System.out.println("Password: ");
                        password = sc.nextLine();
                        List<Cuenta> cuentas = cuentaDAO.listarCuenta(dni, password);
                        if(cuentas.isEmpty()){
                            System.out.println("No se encontraron cuentas para ese usuario o las credenciales no son válidas.");
                        }else{
                            System.out.printf("%-5s %-12s %-21s %-10s", "ID", "DNI", "Nº CUENTA", "SALDO");
                            System.out.println("\n-------------------------------------------------");
                            for (Cuenta cuenta: cuentas){
                                System.out.printf("%-5d %-12s %-21s %10.2f%n", cuenta.getId_cuenta(), dni, cuenta.getNumero_cuenta(), cuenta.getSaldo());
                            }
                            System.out.println("\n(" + cuentas.size() + " cuentas)");
                        }
                        break;
                    case 4:
                        System.out.println("DNI: ");
                        dni = sc.nextLine();
                        System.out.println("Password: ");
                        password = sc.nextLine();
                        List<Cuenta> cuentas2 = cuentaDAO.listarCuentaNoSeguro(dni, password);
                        if(cuentas2.isEmpty()){
                            System.out.println("No se encontraron cuentas para ese usuario o las credenciales no son válidas.");
                        }else{
                            System.out.printf("%-5s %-12s %-21s %-10s", "ID", "DNI", "Nº CUENTA", "SALDO");
                            System.out.println("\n-------------------------------------------------");
                            for (Cuenta cuenta: cuentas2){
                                System.out.printf("%-5d %-12s %-21s %10.2f%n", cuenta.getId_cuenta(), dni, cuenta.getNumero_cuenta(), cuenta.getSaldo());
                            }
                            System.out.println("\n(" + cuentas2.size() + " cuentas)\n");
                        }
                        break;
                    case 5:
                        System.out.println("DNI: ");
                        dni = sc.nextLine();
                        System.out.println("Password: ");
                        password = sc.nextLine();
                        System.out.println("ID de la cuenta a eliminar: ");
                        int id = sc.nextInt();
                        cuentaDAO.eliminarCuenta(dni, password, id);
                        break;
                    case 0:
                        System.out.println("Saliendo de la aplicación...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } while (opcion != 0);
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
