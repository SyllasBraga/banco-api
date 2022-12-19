package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferenciaService {

    TransferenciaRepository transferenciaRepository;
    ContaService contaService;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, ContaService contaService) {
        this.contaService = contaService;
        this.transferenciaRepository = transferenciaRepository;
    }

    public ResponseEntity<List<Transferencia>> getByNumberAccount(Long contaId){
        Conta conta = contaService.getById(contaId);
        return ResponseEntity.ok().body(transferenciaRepository.findByContaId(conta));
    }

    public ResponseEntity<List<Transferencia>> getAll(){
        return ResponseEntity.ok().body(transferenciaRepository.findAll());
    }

}
