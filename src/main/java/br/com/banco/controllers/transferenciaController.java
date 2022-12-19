package br.com.banco.controllers;

import br.com.banco.entities.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/conta")
    public ResponseEntity<List<Transferencia>> getByContaId(@RequestParam(name = "contaId") Long contaId,
                                                            @RequestParam(name = "page") int page,
                                                            @RequestParam(name = "qtdItens") int qtdItens){
        Pageable pageable = PageRequest.of(page, qtdItens);

        return transferenciaService.getByNumberAccount(contaId, pageable);
    }

    @GetMapping
    public ResponseEntity<List<Transferencia>> getAll(@RequestParam(name = "page") int page,
                                                      @RequestParam(name = "qtdItens") int qtdItens){
        Pageable pageable = PageRequest.of(page, qtdItens);

        return transferenciaService.getAll(pageable);
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Transferencia>> getByPeriod(@RequestParam(name = "dataInicio") String dataInicio,
                                                           @RequestParam(name = "dataFim") String dataFim,
                                                           @RequestParam(name = "page") int page,
                                                           @RequestParam(name = "qtdItens") int qtdItens){
        Pageable pageable = PageRequest.of(page, qtdItens);

        return transferenciaService.getByPeriod(dataInicio, dataFim, pageable);
    }

    @GetMapping("/operador")
    public ResponseEntity<List<Transferencia>> getByOperador(@RequestParam(name = "nome") String nome,
                                                             @RequestParam(name = "page") int page,
                                                             @RequestParam(name = "qtdItens") int qtdItens){
        Pageable pageable = PageRequest.of(page, qtdItens);

        return transferenciaService.getByOperador(nome, pageable);
    }

    @GetMapping("/operadorAndPeriodo")
    public ResponseEntity<List<Transferencia>> getByOperadorAndPeriodo (@RequestParam(name = "nome") String nome,
                                                                        @RequestParam(name = "dataInicio") String dataInicio,
                                                                        @RequestParam(name = "dataFim") String dataFim,
                                                                        @RequestParam(name = "page") int page,
                                                                        @RequestParam(name = "qtdItens") int qtdItens){
        Pageable pageable = PageRequest.of(page, qtdItens);

        return transferenciaService.getByPeriodoAndOperador(dataInicio, dataFim, nome, pageable);
    }
}
