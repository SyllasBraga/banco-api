package br.com.banco.repository;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface TransferenciaRepository extends PagingAndSortingRepository<Transferencia, Long> {

    Page<Transferencia> findByContaId (Conta conta, Pageable pageable);

    Page<Transferencia> findByContaIdAndDataTransferenciaBetween(Conta conta, Timestamp inicio, Timestamp fim, Pageable pageable);
    Page<Transferencia> findByContaIdAndNomeOperadorTransacao(Conta conta, String nome, Pageable pageable);

    Page<Transferencia> findByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween(Conta conta, String nome, Timestamp inicio, Timestamp fim, Pageable pageable);

}
