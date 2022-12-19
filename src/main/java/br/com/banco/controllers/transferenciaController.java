package br.com.banco.controllers;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @GetMapping
    public ResponseEntity<List<Transferencia>> getAll(){
        return transferenciaService.getAll();
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Transferencia>> getByPeriod(@RequestParam("dataInicio") String dataInicio,
                                                           @RequestParam("dataFim") String dataFim) {
        return transferenciaService.getByPeriod(dataInicio, dataFim);
    }

    @GetMapping("/operador")
    public ResponseEntity<List<Transferencia>> getByOperador(@RequestParam(name = "nome") String nome){
        return transferenciaService.getByOperador(nome);
    }

}
