package es.cifpcarlos3.Actividad2_4.dao;

import es.cifpcarlos3.Actividad2_4.model.Cuenta;

import java.util.List;

public interface CuentaDAO {
    List<Cuenta> obtenerCuentas();
    void insertarCuenta();
    int obtenerMaxIdCuenta();
    void actualizarSaldoCuenta();
    Cuenta obtenerCuentaPorId(int id);
    void transferenciaEntreCuentas();
    List<Cuenta> obtenerCuentasPorIdCliente(int idCliente);
}
