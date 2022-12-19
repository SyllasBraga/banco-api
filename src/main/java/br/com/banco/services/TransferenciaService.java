package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.services.utils.ValidaData;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferenciaService {

    TransferenciaRepository transferenciaRepository;
    ContaService contaService;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, ContaService contaService) {
        this.contaService = contaService;
        this.transferenciaRepository = transferenciaRepository;
    }

    public ResponseEntity<List<Transferencia>> getByNumberAccount(Long contaId, Pageable pageable){
        Conta conta = contaService.getById(contaId);
        return ResponseEntity.ok().body(transferenciaRepository.findByContaId(conta, pageable));
    }

    public ResponseEntity<List<Transferencia>> getAll(Pageable pageable){
        return ResponseEntity.ok().body(transferenciaRepository.findAll(pageable));
    }

    public ResponseEntity<List<Transferencia>> getByPeriod(String dataInicio, String dataFim, Pageable pageable){
        List<Transferencia> listTransferencias= transferenciaRepository.findAll(pageable);
        List<Transferencia> transferenciasValidas = new ArrayList<>();

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData(dataInicio);
        Timestamp timestampFim = validaData.formataData(dataFim);

        for (Transferencia transferencia : listTransferencias ){
            if (validaData.verificaPeriodo(timestampInicio, timestampFim, transferencia.getDataTransferencia()))
                transferenciasValidas.add(transferencia);
        }
        return ResponseEntity.ok().body(transferenciasValidas);
    }

    public ResponseEntity<List<Transferencia>> getByOperador(String nome, Pageable pageable){
        return ResponseEntity.ok().body(transferenciaRepository.findByNomeOperadorTransacao(nome, pageable));
    }

    public ResponseEntity<List<Transferencia>> getByPeriodoAndOperador(String dataInicio, String dataFim, String nome,
                                                                       Pageable pageable){
        List<Transferencia> listTransferencias= transferenciaRepository.findByNomeOperadorTransacao(nome, pageable);
        List<Transferencia> transferenciasValidas = new ArrayList<>();

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData(dataInicio);
        Timestamp timestampFim = validaData.formataData(dataFim);

        for (Transferencia transferencia : listTransferencias ){
            if (validaData.verificaPeriodo(timestampInicio, timestampFim, transferencia.getDataTransferencia()))
                transferenciasValidas.add(transferencia);
        }
        return ResponseEntity.ok().body(transferenciasValidas);
    }
}
