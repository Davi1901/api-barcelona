package com.sistema.barcelona.controllers;

import com.sistema.barcelona.PatrocinioRecordDto.PatrocinioRecordDto;
import com.sistema.barcelona.models.PatrocinioModel;
import com.sistema.barcelona.repositories.PatrocinioRepository;
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
public class PatrocinioController {

    @Autowired
    PatrocinioRepository patrocinioRepository;

    @PostMapping("/patrocinio")
    public ResponseEntity<PatrocinioModel> savePatrocinio(@RequestBody @Valid PatrocinioRecordDto patrocinioRecordDto){
        var patrocinioModel = new PatrocinioModel();
        BeanUtils.copyProperties(patrocinioRecordDto, patrocinioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(patrocinioRepository.save(patrocinioModel));
    }

    @GetMapping("/patrocinio")
    public ResponseEntity<Object> getAllPatrocinio(Pageable pageable){
        Page<PatrocinioModel> patrocinioPage = patrocinioRepository.findAll(pageable);
        if (!patrocinioPage.isEmpty()){
            for (PatrocinioModel patrocinio: patrocinioPage){
                Long id = patrocinio.getId();
                patrocinio.add(linkTo(methodOn(PatrocinioController.class).getOnePatrocinio(id)).withSelfRel());
            }
            return ResponseEntity.status(HttpStatus.OK).body(patrocinioPage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum patrocínio encontrado");
        }
    }

    @GetMapping("/patrocinio/{id}")
    public ResponseEntity<Object> getOnePatrocinio(@PathVariable(value = "id") Long id){
        Optional<PatrocinioModel> patrocinioO = patrocinioRepository.findById(id);
        if (patrocinioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patrocínio não encontrado");
        } else {
            patrocinioO.get().add(linkTo(methodOn(PatrocinioController.class).getAllPatrocinio(null)).withRel("Lista de patrocínios: "));
            return ResponseEntity.status(HttpStatus.OK).body(patrocinioO.get());
        }
    }

    @PutMapping("/patrocinio/{id}")
    public ResponseEntity<Object> updatePatrocinio(@PathVariable(value = "id") Long id,
                                                   @RequestBody @Valid PatrocinioRecordDto patrocinioRecordDto){
        Optional<PatrocinioModel> patrocinioO = patrocinioRepository.findById(id);
        if (patrocinioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patrocínio não encontrado");
        } else {
            var patrocinioModel = patrocinioO.get();
            BeanUtils.copyProperties(patrocinioRecordDto, patrocinioModel);
            return ResponseEntity.status(HttpStatus.OK).body(patrocinioRepository.save(patrocinioModel));
        }
    }

    @DeleteMapping("/patrocinio/{id}")
    public ResponseEntity<Object> deletePatrocinio(@PathVariable(value = "id") Long id){
        Optional<PatrocinioModel> patrocinioO = patrocinioRepository.findById(id);
        if (patrocinioO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patrocínio não encontrado");
        } else {
            patrocinioRepository.delete(patrocinioO.get());
            return ResponseEntity.status(HttpStatus.OK).body("Patrocínio excluído com sucesso");
        }
    }
}