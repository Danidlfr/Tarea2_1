package es.cifpcarlos3.Actividad2_4.dao.impl;

import es.cifpcarlos3.Actividad2_4.dao.ClienteDAO;
import es.cifpcarlos3.Actividad2_4.model.Cliente;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ClienteDAOImpl implements ClienteDAO {
    final DatabaseConnection db;

    @Override
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String consulta = "SELECT id_cliente, dni, nombre, telefono, email FROM t_cliente";

        try(var conn = db.getConn();
            var sentencia =conn.createStatement();
            var resultado = sentencia.executeQuery(consulta);){

            while (resultado.next()) {
                clientes.add(new Cliente(resultado.getInt("id_cliente"),
                                    resultado.getString("dni"),
                                    resultado.getString("nombre"),
                                    resultado.getString("telefono"),
                                    resultado.getString("email")));
            }
        }catch(SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
            System.err.println("Estado SQL: " + e.getSQLState());
            System.err.println("Código error: " + e.getErrorCode());
        }
        return clientes;
    }
}
