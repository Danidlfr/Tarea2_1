package es.cifpcarlos3.Actividad2_4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    int id_cliente;
    String dni;
    String nombre;
    String telefono;
    String email;
}
