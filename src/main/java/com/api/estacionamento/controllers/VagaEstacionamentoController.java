package com.api.estacionamento.controllers;
import com.api.estacionamento.dto.VagasEstacionamentoDto;
import com.api.estacionamento.models.VagaEstacionamentoModel;
import com.api.estacionamento.services.VagasEstacionamentoServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/controller")
public class VagaEstacionamentoController {

    @Autowired   // Inseção: Controller > Services > Repository
    VagasEstacionamentoServices vagasEstacionamentoServices;


    // Salvar
    @PostMapping
    public ResponseEntity<Object> saveVagasEstacionamento(@RequestBody @Valid VagasEstacionamentoDto vagasEstacionamentoDto) {

        var model = new VagaEstacionamentoModel();
        BeanUtils.copyProperties(vagasEstacionamentoDto, model);
        model.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(vagasEstacionamentoServices.save(model));
    }


    // Mostra as vagas cadastradas                                                  // Páginação
    @GetMapping
    public ResponseEntity<Page<VagaEstacionamentoModel>> getAllVagasEstacionamento(@PageableDefault(page = 0, size = 10,
            sort = "id", direction = Sort.Direction.ASC)Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(vagasEstacionamentoServices.findAll(pageable));
    }


    // Deletar um vaga pelo seu ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVagasEstacionamento(@PathVariable(value= "id")UUID id) {
        Optional<VagaEstacionamentoModel> vagaEstacionamentoModelOptional = vagasEstacionamentoServices.findById(id);
        if (!vagaEstacionamentoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não existe.");
        }
        vagasEstacionamentoServices.delete(vagaEstacionamentoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga foi deletada com sucesso!");
    }


    // mudar os registros de dados salvos
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVagasEstacionamento(@PathVariable(value="id") UUID id,
                                                            @RequestBody @Valid VagasEstacionamentoDto vagasEstacionamentoDto) {

        Optional<VagaEstacionamentoModel> vagaEstacionamentoModelOptional = vagasEstacionamentoServices.findById(id);
        if (!vagaEstacionamentoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não existe.");
        }
        var vagasModel = new VagaEstacionamentoModel();
        BeanUtils.copyProperties(vagasEstacionamentoDto, vagasModel);
        vagasModel.setId(vagaEstacionamentoModelOptional.get().getId());
        vagasModel.setDataRegistro(vagaEstacionamentoModelOptional.get().getDataRegistro());
        return ResponseEntity.status(HttpStatus.OK).body(vagasEstacionamentoServices.save(vagasModel));

    }


}
