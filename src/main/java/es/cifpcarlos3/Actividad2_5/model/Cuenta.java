package es.cifpcarlos3.Actividad2_5.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Cuenta {
    int id_cuenta;
    String numero_cuenta;
    int id_cliente;
    String nombre_cliente;
    BigDecimal saldo;

    public Cuenta(String numero_cuenta, int id_cuenta, BigDecimal saldo) {
        this.numero_cuenta = numero_cuenta;
        this.id_cuenta = id_cuenta;
        this.saldo = saldo;
    }
}
