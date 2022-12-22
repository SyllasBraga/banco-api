package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.services.utils.ValidaData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransferenciaService {

    TransferenciaRepository transferenciaRepository;
    ContaService contaService;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, ContaService contaService) {
        this.contaService = contaService;
        this.transferenciaRepository = transferenciaRepository;
    }

    public ResponseEntity<Page<Transferencia>> getByNumberAccount(Long contaId, int page){
        Conta conta = contaService.getById(contaId);
        Pageable pageable = PageRequest.of(page, 4);
        return ResponseEntity.ok().body(transferenciaRepository.findByContaId(conta, pageable));
    }

    public ResponseEntity<Page<Transferencia>> getAll(int page){
        Pageable pageable = PageRequest.of(page, 4);
        return ResponseEntity.ok().body(transferenciaRepository.findAll(pageable));
    }

    public ResponseEntity<Page<Transferencia>> getByPeriod(Long contaId, String dataInicio, String dataFim, int page){
        Pageable pageable = PageRequest.of(page, 4);
        Conta conta = contaService.getById(contaId);

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData(dataInicio);
        Timestamp timestampFim = validaData.formataData(dataFim);

        return ResponseEntity.ok().body(transferenciaRepository.findByContaIdAndDataTransferenciaBetween(conta, timestampInicio, timestampFim, pageable));
    }

    public ResponseEntity<Page<Transferencia>> getByOperador(Long contaId, String nome, int page){
        Pageable pageable = PageRequest.of(page, 4);
        Conta conta = contaService.getById(contaId);

        return ResponseEntity.ok().body(transferenciaRepository.findByContaIdAndNomeOperadorTransacao(conta, nome, pageable));
    }

    public ResponseEntity<Page<Transferencia>> getByPeriodoAndOperador(Long contaId, String dataInicio, String dataFim, String nome,
                                                                       int page){
        Pageable pageable = PageRequest.of(page, 4);
        Conta conta = contaService.getById(contaId);

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData(dataInicio);
        Timestamp timestampFim = validaData.formataData(dataFim);

        return ResponseEntity.ok().body(transferenciaRepository.
                findByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween(conta, nome, timestampInicio,
                        timestampFim, pageable));
    }
}
