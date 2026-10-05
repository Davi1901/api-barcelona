package com.sistema.barcelona.controllers;

import com.sistema.barcelona.ContratoRecordDto.ContratoRecordDto;
import com.sistema.barcelona.models.ContratoModel;
import com.sistema.barcelona.repositories.ContratoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ContratoController {

    @Autowired
    ContratoRepository contratoRepository;

    @PostMapping("/contrato")
    public ResponseEntity<ContratoModel> saveContrato(@RequestBody @Valid ContratoRecordDto contratoRecordDto){
        var contratoModel = new ContratoModel();
        BeanUtils.copyProperties(contratoRecordDto, contratoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(contratoRepository.save(contratoModel));
    }

    @GetMapping("/contrato")
    public ResponseEntity<Object> getAllContrato(Pageable pageable){
        Page<ContratoModel> contratoPage = contratoRepository.findAll(pageable);
        if (!contratoPage.isEmpty()){
            for (ContratoModel contrato: contratoPage){
                Long id = contrato.getId();
                contrato.add(linkTo(methodOn(ContratoController.class).getOneContrato(id)).withSelfRel());
            }
            return ResponseEntity.status(HttpStatus.OK).body(contratoPage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum contrato encontrado");
        }
    }

    @GetMapping("/contrato/{id}")
    public ResponseEntity<Object> getOneContrato(@PathVariable(value = "id") Long id){
        Optional<ContratoModel> contratoO = contratoRepository.findById(id);
        if (contratoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado");
        } else {
            contratoO.get().add(linkTo(methodOn(ContratoController.class).getAllContrato(null)).withRel("Lista de contratos: "));
            return ResponseEntity.status(HttpStatus.OK).body(contratoO.get());
        }
    }

    @PutMapping("/contrato/{id}")
    public ResponseEntity<Object> updateContrato(@PathVariable(value = "id") Long id,
                                                 @RequestBody @Valid ContratoRecordDto contratoRecordDto){
        Optional<ContratoModel> contratoO = contratoRepository.findById(id);
        if (contratoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado");
        } else {
            var contratoModel = contratoO.get();
            BeanUtils.copyProperties(contratoRecordDto, contratoModel);
            return ResponseEntity.status(HttpStatus.OK).body(contratoRepository.save(contratoModel));
        }
    }

    @DeleteMapping("/contrato/{id}")
    public ResponseEntity<Object> deleteContrato(@PathVariable(value = "id") Long id){
        Optional<ContratoModel> contratoO = contratoRepository.findById(id);
        if (contratoO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado");
        } else {
            contratoRepository.delete(contratoO.get());
            return ResponseEntity.status(HttpStatus.OK).body("Contrato excluído com sucesso");
        }
    }
}