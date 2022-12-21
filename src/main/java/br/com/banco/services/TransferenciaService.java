package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.services.utils.ValidaData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    public ResponseEntity<Page<Transferencia>> getByNumberAccount(Long contaId, int page){
        Conta conta = contaService.getById(contaId);
        Pageable pageable = PageRequest.of(page, 4);
        return ResponseEntity.ok().body(transferenciaRepository.findByContaId(conta, pageable));
    }

    public ResponseEntity<Page<Transferencia>> getAll(int page){
        Pageable pageable = PageRequest.of(page, 4);
        return ResponseEntity.ok().body(transferenciaRepository.findAll(pageable));
    }

    public ResponseEntity<Page<Transferencia>> getByPeriod(String dataInicio, String dataFim, int page){
        Pageable pageable = PageRequest.of(page, 4);

        Page<Transferencia> listTransferencias= transferenciaRepository.findAll(pageable);
        List<Transferencia> transferenciasValidas = new ArrayList<>();

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData(dataInicio);
        Timestamp timestampFim = validaData.formataData(dataFim);

        for (Transferencia transferencia : listTransferencias ){
            if (validaData.verificaPeriodo(timestampInicio, timestampFim, transferencia.getDataTransferencia()))
                transferenciasValidas.add(transferencia);
        }

        Page<Transferencia> listTrasferenciasValidas = new PageImpl<>(transferenciasValidas);
        return ResponseEntity.ok().body(listTrasferenciasValidas);
    }

    public ResponseEntity<Page<Transferencia>> getByOperador(String nome, int page){
        Pageable pageable = PageRequest.of(page, 4);
        return ResponseEntity.ok().body(transferenciaRepository.findByNomeOperadorTransacao(nome, pageable));
    }

    public ResponseEntity<Page<Transferencia>> getByPeriodoAndOperador(String dataInicio, String dataFim, String nome,
                                                                       int page){
        Pageable pageable = PageRequest.of(page, 4);

        Page<Transferencia> listTransferencias= transferenciaRepository.findByNomeOperadorTransacao(nome, pageable);
        List<Transferencia> transferenciasValidas = new ArrayList<>();

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData(dataInicio);
        Timestamp timestampFim = validaData.formataData(dataFim);

        for (Transferencia transferencia : listTransferencias ){
            if (validaData.verificaPeriodo(timestampInicio, timestampFim, transferencia.getDataTransferencia())){
                transferenciasValidas.add(transferencia);
            }
        }

        Page<Transferencia> listTrasferenciasValidas = new PageImpl<>(transferenciasValidas);
        return ResponseEntity.ok().body(listTrasferenciasValidas);
    }
}
