package br.com.banco.services;

import br.com.banco.entities.Conta;
import br.com.banco.repository.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta getById(Long id){
        return contaRepository.getById(id);
    }

}
