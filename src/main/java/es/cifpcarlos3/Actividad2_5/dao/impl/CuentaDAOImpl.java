package es.cifpcarlos3.Actividad2_5.dao.impl;

import es.cifpcarlos3.Actividad2_5.dao.CuentaDAO;
import es.cifpcarlos3.Actividad2_5.model.Cuenta;
import es.cifpcarlos3.Actividad2_5.util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAOImpl implements CuentaDAO {
    private static DatabaseConnection db = new DatabaseConnection();

    @Override
    public List<String> obtenerDNI() {
        List<String> cuentasDNI = new ArrayList<>();
        String sql = "SELECT dni FROM t_cliente WHERE dni NOT IN (SELECT dni FROM t_usuario);";
        try (var stmt = db.conexion().createStatement();
             var rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cuentasDNI.add(rs.getString("dni"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cuentasDNI;
    }

    @Override
    public List<Cuenta> listarCuenta(String dni, String password) {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.saldo FROM T_CUENTA c JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente JOIN T_USUARIO u ON u.dni = cli.dni WHERE u.dni = ? AND u.password = ?;";
        try (var conn = db.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, dni);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cuentas.add(new Cuenta(rs.getString("numero_cuenta"),rs.getInt("id_cuenta"),  rs.getBigDecimal("saldo")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cuentas;
    }

    @Override
    public List<Cuenta> listarCuentaNoSeguro(String dni, String password) {
        List<Cuenta> cuentas = new ArrayList<>();
        String sql = "SELECT c.id_cuenta, c.numero_cuenta, c.saldo FROM T_CUENTA c JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente JOIN T_USUARIO u ON u.dni = cli.dni WHERE u.dni = '" + dni + "' AND u.password = '" + password + "'";
        try (var stmt = db.conexion().createStatement();
             var rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                cuentas.add(new Cuenta(rs.getString("numero_cuenta"),rs.getInt("id_cuenta"),  rs.getBigDecimal("saldo")));
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cuentas;
    }

    @Override
    public void eliminarCuenta(String dni, String password, int id) {
        String sql = "DELETE c FROM T_CUENTA c JOIN T_CLIENTE cli ON c.id_cliente = cli.id_cliente JOIN T_USUARIO u ON u.dni = cli.dni WHERE u.dni = ? AND u.password = ? AND c.id_cuenta = ?;";
        try (var stmt = db.conexion();
             var sentencia = stmt.prepareStatement(sql)) {
            sentencia.setString(1, dni);
            sentencia.setString(2, password);
            sentencia.setInt(3, id);
            int filas = sentencia.executeUpdate();
            System.out.println("\nCuenta eliminada correctamente. Filas afectadas: " + filas +"\n");
        }catch (SQLException e) {
            System.err.println("\nNo se eliminó ninguna cuenta (credenciales incorrectas o cuenta no pertenece a ese usuario).\n");
        }
    }
}
