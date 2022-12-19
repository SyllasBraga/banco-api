package br.com.banco.repository;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {

    List<Transferencia> findAll(Pageable pageable);

    List<Transferencia> findByContaId (Conta conta, Pageable pageable);

    List<Transferencia> findByNomeOperadorTransacao(String nome, Pageable pageable);

}
