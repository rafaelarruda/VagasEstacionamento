package com.api.estacionamento.repositories;

import com.api.estacionamento.models.VagaEstacionamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// O que eu pretendo usar do JpaRepository = Atualizar, Salvar, Deletar
@Repository
public interface VagaEstacionamentoRepository extends JpaRepository<VagaEstacionamentoModel, UUID> {

}
