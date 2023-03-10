package br.com.banco.controllers;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transferencias")
public class transferenciaController {

    TransferenciaService transferenciaService;

    public transferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @GetMapping("/conta")
    public ResponseEntity<Page<Transferencia>> getByContaId(@RequestParam(name = "contaId") Long contaId,
                                                            @RequestParam(name = "page") int page){

        return ResponseEntity.ok(transferenciaService.getByNumberAccount(contaId, page));
    }

    @GetMapping("/periodo")
    public ResponseEntity<Page<Transferencia>> getByPeriod(@RequestParam(name = "contaId") Long contaId,
                                                           @RequestParam(name = "dataInicio") String dataInicio,
                                                           @RequestParam(name = "dataFim") String dataFim,
                                                           @RequestParam(name = "page") int page){

        return ResponseEntity.ok(transferenciaService.getByPeriod(contaId, dataInicio, dataFim, page));
    }

    @GetMapping("/operador")
    public ResponseEntity<Page<Transferencia>> getByOperador(@RequestParam(name = "contaId") Long contaId,
                                                             @RequestParam(name = "nome") String nome,
                                                             @RequestParam(name = "page") int page){

        return ResponseEntity.ok(transferenciaService.getByOperador(contaId, nome, page));
    }

    @GetMapping("/operadorAndPeriodo")
    public ResponseEntity<Page<Transferencia>> getByOperadorAndPeriodo (@RequestParam(name = "contaId") Long contaId,
                                                                        @RequestParam(name = "nome") String nome,
                                                                        @RequestParam(name = "dataInicio") String dataInicio,
                                                                        @RequestParam(name = "dataFim") String dataFim,
                                                                        @RequestParam(name = "page") int page){

        return ResponseEntity.ok(transferenciaService.getByPeriodoAndOperador(contaId, dataInicio, dataFim, nome, page));
    }
}
