package es.cifpcarlos3.Actividad2_5.dao.impl;

import es.cifpcarlos3.Actividad2_5.dao.UsuarioDAO;
import es.cifpcarlos3.Actividad2_5.util.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO {
    private static DatabaseConnection db = new DatabaseConnection();
    @Override
    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS T_USUARIO (dni VARCHAR(9)   PRIMARY KEY, password VARCHAR(100) NOT NULL, CONSTRAINT fk_usuario_cliente FOREIGN KEY (dni) REFERENCES T_CLIENTE(dni)) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
        try (var conn = db.conexion();
             PreparedStatement ps = conn.prepareStatement(sql)){
            ps.executeUpdate();
            System.out.println("Tabla T_USUARIO creada (o ya existía).\n");
        }catch (SQLException e) {
            System.err.println("Error al crear tabla: " + e.getMessage()+"\n");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertarUsuario(List<String> cuentasDNI) {
        String sql = "INSERT INTO T_USUARIO (dni, password) VALUES (?, ?);";
        int filas = 0;
        for (String dni : cuentasDNI) {
            try (var conn = db.conexion();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, dni);
                preparedStatement.setString(2, "1234");

                filas +=  preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Error al insertar usuario: " + e.getMessage() + "\n");
                throw new RuntimeException(e);
            }
        }
        System.out.println("Se han importado " + filas + " usuarios nuevos a T_USUARIO.\n");
    }
}
