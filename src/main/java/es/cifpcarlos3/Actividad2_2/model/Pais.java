package es.cifpcarlos3.Actividad2_2.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pais {
    int cont_cod;
    int id;
    String nombre;
    String capital;
}
