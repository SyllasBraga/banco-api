package br.com.banco.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConta;

    private String nomeResponsavel;

    @Transient
    private float saldoTotal;
    @Transient
    private float saldoPeriodo;
}
