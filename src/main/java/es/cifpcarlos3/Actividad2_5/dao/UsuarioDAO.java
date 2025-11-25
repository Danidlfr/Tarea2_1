package es.cifpcarlos3.Actividad2_5.dao;

import java.util.List;

public interface UsuarioDAO {
    void crearTabla();
    void insertarUsuario(List<String> cuentasDNI);
}
