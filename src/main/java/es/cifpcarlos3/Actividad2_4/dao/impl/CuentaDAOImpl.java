package es.cifpcarlos3.Actividad2_4.dao.impl;

import es.cifpcarlos3.Actividad2_4.dao.CuentaDAO;
import es.cifpcarlos3.Actividad2_4.util.DatabaseConnection;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuentaDAOImpl implements CuentaDAO {
    final DatabaseConnection db;

}
