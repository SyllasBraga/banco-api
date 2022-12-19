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

    @GetMapping
    public ResponseEntity<List<Transferencia>> getAll(){
        return transferenciaService.getAll();
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Transferencia>> getByPeriod(@RequestParam(name = "dataInicio") String dataInicio,
                                                           @RequestParam(name = "dataFim") String dataFim) {
        return transferenciaService.getByPeriod(dataInicio, dataFim);
    }

    @GetMapping("/operador")
    public ResponseEntity<List<Transferencia>> getByOperador(@RequestParam(name = "nome") String nome){
        return transferenciaService.getByOperador(nome);
    }

    @GetMapping("/operadorAndPeriodo")
    public ResponseEntity<List<Transferencia>> getByOperadorAndPeriodo (@RequestParam(name = "nome") String nome,
                                                                        @RequestParam(name = "dataInicio") String dataInicio,
                                                                        @RequestParam(name = "dataFim") String dataFim){
        return transferenciaService.getByPeriodoAndOperador(dataInicio, dataFim, nome);
    }

}
