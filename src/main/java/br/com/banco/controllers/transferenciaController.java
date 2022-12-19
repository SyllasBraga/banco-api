package br.com.banco.controllers;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class transferenciaController {

    TransferenciaService transferenciaService;

    public transferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping(path = "/{contaId}")
    public ResponseEntity<List<Transferencia>> getByContaId(@PathVariable Long contaId){
        return transferenciaService.getByNumberAccount(contaId);
    }

}
