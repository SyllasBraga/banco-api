package br.com.banco.services.utils;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class CalculaSaldo {

    TransferenciaRepository transferenciaRepository;

    public CalculaSaldo(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    public float calculaSaldoConta(Conta conta){

        Pageable pageable = PageRequest.of(0,1);

        int totalTranferencias =(int) transferenciaRepository.findByContaId(conta, pageable).getTotalElements();
        Pageable pageableTransferencias = PageRequest.of(0, totalTranferencias);

        Page<Transferencia> transferencias = transferenciaRepository.findByContaId(conta, pageableTransferencias);

        float saldo = 0;

        for (Transferencia transferenciaObj : transferencias){
            saldo += transferenciaObj.getValor();
        }

        return saldo;
    }

    public float calculaSaldoPeriodo(Conta conta, Timestamp timestampFim){

        ValidaData validaData = new ValidaData();
        Timestamp timestampInicio = validaData.formataData("0000-00-00 00:00:00");
        Pageable pageable = PageRequest.of(0,1);

        int totalTranferenciasPeriodo =(int) transferenciaRepository.
                findByContaIdAndDataTransferenciaBetween(conta, timestampInicio, timestampFim, pageable).getTotalElements();
        Pageable pageableTransferenciasPeriodo = PageRequest.of(0, totalTranferenciasPeriodo);

        Page<Transferencia> transferencias = transferenciaRepository.
                findByContaIdAndDataTransferenciaBetween(conta, timestampInicio, timestampFim, pageableTransferenciasPeriodo);

        float saldo = 0;

        for (Transferencia transferenciaObj : transferencias){
            saldo += transferenciaObj.getValor();
        }

        return saldo;
    }

}
