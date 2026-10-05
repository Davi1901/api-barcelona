package com.sistema.barcelona.controllers;

import com.sistema.barcelona.TreinadorRecordDto.TreinadorRecordDto;
import com.sistema.barcelona.models.TreinadorModel;
import com.sistema.barcelona.repositories.TreinadorRepository;
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
public class TreinadorController {

    @Autowired
    TreinadorRepository treinadorRepository;

    @PostMapping("/treinador")
    public ResponseEntity<TreinadorModel> saveTreinador(@RequestBody @Valid TreinadorRecordDto treinadorRecordDto){
        var treinadorModel = new TreinadorModel();
        BeanUtils.copyProperties(treinadorRecordDto, treinadorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(treinadorRepository.save(treinadorModel));
    }

    @GetMapping("/treinador")
    public ResponseEntity<Object> getAllTreinador(Pageable pageable){
        Page<TreinadorModel> treinadorPage = treinadorRepository.findAll(pageable);
        if (!treinadorPage.isEmpty()){
            for (TreinadorModel treinador: treinadorPage){
                Long id = treinador.getId();
                treinador.add(linkTo(methodOn(TreinadorController.class).getOneTreinador(id)).withSelfRel());
            }
            return ResponseEntity.status(HttpStatus.OK).body(treinadorPage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum treinador encontrado");
        }
    }

    @GetMapping("/treinador/{id}")
    public ResponseEntity<Object> getOneTreinador(@PathVariable(value = "id") Long id){
        Optional<TreinadorModel> treinadorR = treinadorRepository.findById(id);
        if (treinadorR.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treinador não encontrado");
        } else {
            treinadorR.get().add(linkTo(methodOn(TreinadorController.class).getAllTreinador(null)).withRel("Lista de Treinadores: "));
            return ResponseEntity.status(HttpStatus.OK).body(treinadorR.get());
        }
    }

    @PutMapping("/treinador/{id}")
    public ResponseEntity<Object> updateTreinador(@PathVariable(value = "id") Long id,
                                                  @RequestBody @Valid TreinadorRecordDto treinadorRecordDto){
        Optional<TreinadorModel> treinadorR = treinadorRepository.findById(id);
        if (treinadorR.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treinador não encontrado");
        } else {
            var treinadorModel = treinadorR.get();
            BeanUtils.copyProperties(treinadorRecordDto, treinadorModel);
            return ResponseEntity.status(HttpStatus.OK).body(treinadorRepository.save(treinadorModel));
        }
    }

    @DeleteMapping("/treinador/{id}")
    public ResponseEntity<Object> deleteTreinador(@PathVariable(value = "id") Long id){
        Optional<TreinadorModel> treinadorR = treinadorRepository.findById(id);
        if (treinadorR.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Treinador não encontrado");
        } else {
            treinadorRepository.delete(treinadorR.get());
            return ResponseEntity.status(HttpStatus.OK).body("Treinador excluído com sucesso");
        }
    }
}