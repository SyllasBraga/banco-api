package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.services.utils.CalculaSaldo;
import br.com.banco.services.utils.ValidaData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TransferenciaService {

    TransferenciaRepository transferenciaRepository;
    ContaService contaService;
    CalculaSaldo calculaSaldo;

    public TransferenciaService(TransferenciaRepository transferenciaRepository, ContaService contaService, CalculaSaldo calculaSaldo) {
        this.transferenciaRepository = transferenciaRepository;
        this.contaService = contaService;
        this.calculaSaldo = calculaSaldo;
    }

    public Page<Transferencia> getByNumberAccount(Long contaId, int page){
        Conta conta = contaService.getById(contaId);
        Pageable pageable = PageRequest.of(page, 4);

        conta.setSaldoTotal(calculaSaldo.calculaSaldoConta(conta));

        return transferenciaRepository.findByContaId(conta, pageable);
    }

    public Page<Transferencia> getByPeriod(Long contaId, String dataInicio, String dataFim, int page){
        Pageable pageable = PageRequest.of(page, 4);
        Conta conta = contaService.getById(contaId);

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData(dataInicio);
        Timestamp timestampFim = validaData.formataData(dataFim);

        conta.setSaldoTotal(calculaSaldo.calculaSaldoConta(conta));
        conta.setSaldoPeriodo(calculaSaldo.calculaSaldoPeriodo(conta, timestampFim));

        return transferenciaRepository.findByContaIdAndDataTransferenciaBetween(conta,
                timestampInicio, timestampFim, pageable);
    }

    public Page<Transferencia> getByOperador(Long contaId, String nome, int page){
        Pageable pageable = PageRequest.of(page, 4);
        Conta conta = contaService.getById(contaId);

        conta.setSaldoTotal(calculaSaldo.calculaSaldoConta(conta));

        return transferenciaRepository.findByContaIdAndNomeOperadorTransacao(conta, nome, pageable);
    }

    public Page<Transferencia> getByPeriodoAndOperador(Long contaId, String dataInicio, String dataFim, String nome,
                                                                       int page){
        Pageable pageable = PageRequest.of(page, 4);
        Conta conta = contaService.getById(contaId);

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData(dataInicio);
        Timestamp timestampFim = validaData.formataData(dataFim);

        conta.setSaldoTotal(calculaSaldo.calculaSaldoConta(conta));
        conta.setSaldoPeriodo(calculaSaldo.calculaSaldoPeriodo(conta, timestampFim));

        return transferenciaRepository.
                findByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween(conta, nome, timestampInicio,
                        timestampFim, pageable);
    }
}
