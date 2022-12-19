package br.com.banco.entities;

import br.com.banco.enums.TipoTransferenciaEnums;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp dataTransferencia;

    private float valor;

    private TipoTransferenciaEnums tipoTransferenciaEnums;
}
