package br.com.banco.entities;

import br.com.banco.enums.TipoTransferenciaEnums;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Timestamp dataTransferencia;

    private float valor;

    private TipoTransferenciaEnums tipo;

    private String nomeOperadorTransacao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta contaId;
}
