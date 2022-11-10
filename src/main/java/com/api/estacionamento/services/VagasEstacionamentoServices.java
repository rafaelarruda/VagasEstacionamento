package com.api.estacionamento.services;

import com.api.estacionamento.models.VagaEstacionamentoModel;
import com.api.estacionamento.repositories.VagaEstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.Optional;
import java.util.UUID;

@Service
public class VagasEstacionamentoServices {

    @Autowired
    VagaEstacionamentoRepository vagaEstacionamentoRepository;

    public VagaEstacionamentoModel save(VagaEstacionamentoModel model) {
        return vagaEstacionamentoRepository.save(model);
    }

    public Page<VagaEstacionamentoModel> findAll(Pageable pageable) {
        return vagaEstacionamentoRepository.findAll(pageable);
    }

    public Optional<VagaEstacionamentoModel> findById(UUID id) {
        return vagaEstacionamentoRepository.findById(id);
    }

    @Transactional
    public void delete(VagaEstacionamentoModel vagaEstacionamentoModel) {
        vagaEstacionamentoRepository.delete(vagaEstacionamentoModel);
    }
}
