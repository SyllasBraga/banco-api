package br.com.banco.repository;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferenciaRepository extends PagingAndSortingRepository<Transferencia, Long> {

    List<Transferencia> findByContaId (Conta conta, Pageable pageable);

    List<Transferencia> findByNomeOperadorTransacao(String nome, Pageable pageable);

}
