package es.cifpcarlos3.Actividad2_3.dao;

import es.cifpcarlos3.Actividad2_3.model.Pais;

import java.util.List;

public interface PaisDAO {
    List<Pais> listarPaises();
    void actualizarPais();
}
