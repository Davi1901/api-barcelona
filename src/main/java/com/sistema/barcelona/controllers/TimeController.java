package com.sistema.barcelona.controllers;

import com.sistema.barcelona.TimeRecordDto.TimeRecordDto;
import com.sistema.barcelona.models.TimeModel;
import com.sistema.barcelona.repositories.TimeRepository;
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
public class TimeController {

    @Autowired
    TimeRepository timeRepository;

    @PostMapping("/time")
    public ResponseEntity<TimeModel> saveTime(@RequestBody @Valid TimeRecordDto timeRecordDto){
        var timeModel = new TimeModel();
        BeanUtils.copyProperties(timeRecordDto, timeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(timeRepository.save(timeModel));
    }

    @GetMapping("/time")
    public ResponseEntity<Object> getAllTime(Pageable pageable){
        Page<TimeModel> timePage = timeRepository.findAll(pageable);
        if (!timePage.isEmpty()){
            for (TimeModel time: timePage){
                Long id = time.getId();
                time.add(linkTo(methodOn(TimeController.class).getOneTime(id)).withSelfRel());
            }
            return ResponseEntity.status(HttpStatus.OK).body(timePage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum time encontrado");
        }
    }

    @GetMapping("/time/{id}")
    public ResponseEntity<Object> getOneTime(@PathVariable(value = "id") Long id){
        Optional<TimeModel> timeE = timeRepository.findById(id);
        if (timeE.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado");
        } else {
            timeE.get().add(linkTo(methodOn(TimeController.class).getAllTime(null)).withRel("Lista de times: "));
            return ResponseEntity.status(HttpStatus.OK).body(timeE.get());
        }
    }

    @PutMapping("/time/{id}")
    public ResponseEntity<Object> updateTime(@PathVariable(value = "id") Long id,
                                             @RequestBody @Valid TimeRecordDto timeRecordDto){
        Optional<TimeModel> timeE = timeRepository.findById(id);
        if (timeE.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado");
        } else {
            var timeModel = timeE.get();
            BeanUtils.copyProperties(timeRecordDto, timeModel);
            return ResponseEntity.status(HttpStatus.OK).body(timeRepository.save(timeModel));
        }
    }

    @DeleteMapping("/time/{id}")
    public ResponseEntity<Object> deleteTime(@PathVariable(value = "id") Long id){
        Optional<TimeModel> timeE = timeRepository.findById(id);
        if (timeE.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado");
        } else {
            timeRepository.delete(timeE.get());
            return ResponseEntity.status(HttpStatus.OK).body("Time excluído com sucesso");
        }
    }
}