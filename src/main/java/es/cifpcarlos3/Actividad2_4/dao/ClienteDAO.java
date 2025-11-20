package es.cifpcarlos3.Actividad2_4.dao;

import es.cifpcarlos3.Actividad2_4.model.Cliente;

import java.util.List;

public interface ClienteDAO {
    List<Cliente> obtenerClientes();
    void insertarCliente();
    int obtenerMaxIdCliente();
    Cliente obtenerClientePorDNI(String dni);
    Cliente obtenerClientePorId(int id);
    void eliminarCliente();
}
