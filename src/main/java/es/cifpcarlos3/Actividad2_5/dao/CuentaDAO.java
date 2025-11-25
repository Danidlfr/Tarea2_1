package es.cifpcarlos3.Actividad2_5.dao;

import es.cifpcarlos3.Actividad2_5.model.Cuenta;

import java.util.List;

public interface CuentaDAO {
    List<String> obtenerDNI();
    List<Cuenta> listarCuenta(String dni, String password);
    List<Cuenta> listarCuentaNoSeguro(String dni, String password);
    void eliminarCuenta(String dni, String  password, int id);
}
