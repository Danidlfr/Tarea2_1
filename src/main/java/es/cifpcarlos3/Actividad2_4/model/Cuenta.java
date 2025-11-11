package es.cifpcarlos3.Actividad2_4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    int id_cuenta;
    String numero_cuenta;
    int id_cliente;
    BigDecimal saldo;
}
