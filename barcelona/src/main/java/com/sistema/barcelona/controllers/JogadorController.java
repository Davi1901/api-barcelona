package com.sistema.barcelona.controllers;

import com.sistema.barcelona.JogadorRecordDto.JogadorRecordDto;
import com.sistema.barcelona.enums.PosicaoEnum;
import com.sistema.barcelona.models.ContratoModel;
import com.sistema.barcelona.models.JogadorModel;
import com.sistema.barcelona.models.TimeModel;
import com.sistema.barcelona.repositories.ContratoRepository;
import com.sistema.barcelona.repositories.JogadorRepository;
import com.sistema.barcelona.repositories.TimeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class JogadorController {

    @Autowired
    JogadorRepository jogadorRepository;

    @Autowired
    ContratoRepository contratoRepository;

    @Autowired
    TimeRepository timeRepository;

    @PostMapping("/jogador")
    public ResponseEntity<Object> saveJogadors(@RequestBody @Valid JogadorRecordDto jogadorRecordDto){
        Optional<ContratoModel> contratoO = contratoRepository.findById(jogadorRecordDto.contratoId());
        if (contratoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contrato não encontrado");
        }

        Optional<TimeModel> timeO = timeRepository.findById(jogadorRecordDto.timeId());
        if (timeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado");
        }

        var jogadorModel = new JogadorModel();
        jogadorModel.setName(jogadorRecordDto.name());
        jogadorModel.setNumberShirt(jogadorRecordDto.numberShirt());
        jogadorModel.setPosition(jogadorRecordDto.position());
        jogadorModel.setContrato(contratoO.get());
        jogadorModel.setTime(timeO.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(jogadorRepository.save(jogadorModel));
    }

    // LISTAGEM COM PAGINAÇÃO
    @GetMapping("/jogador")
    public ResponseEntity<Object> getAllJogadors(Pageable pageable){
        Page<JogadorModel> jogadorPage = jogadorRepository.findAll(pageable);
        if (!jogadorPage.isEmpty()){
            for (JogadorModel jogador: jogadorPage){
                Long id = jogador.getId();
                jogador.add(linkTo(methodOn(JogadorController.class).getOneJogador(id)).withSelfRel());
            }
            return ResponseEntity.status(HttpStatus.OK).body(jogadorPage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum jogador encontrado");
        }
    }

    // CONSULTA PERSONALIZADA POR POSIÇÃO
    @GetMapping("/jogador/posicao/{position}")
    public ResponseEntity<Object> getJogadoresByPosition(@PathVariable(value = "position") PosicaoEnum position) {
        List<JogadorModel> jogadores = jogadorRepository.findByPosition(position);
        if (jogadores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum jogador encontrado para esta posição.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(jogadores);
    }

    @GetMapping("/jogador/{id}")
    public ResponseEntity<Object> getOneJogador(@PathVariable(value = "id") Long id){
        Optional<JogadorModel> jogadorO = jogadorRepository.findById(id);
        if (jogadorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
        } else {
            jogadorO.get().add(linkTo(methodOn(JogadorController.class).getAllJogadors(null)).withRel("Lista de Jogadores: "));
            return ResponseEntity.status(HttpStatus.OK).body(jogadorO.get());
        }
    }

    @PutMapping("/jogador/{id}")
    public ResponseEntity<Object> updateJogador(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid JogadorRecordDto jogadorRecordDto){
        Optional<JogadorModel> jogadorO = jogadorRepository.findById(id);
        if (jogadorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
        } else {
            var jogadorModel = jogadorO.get();
            jogadorModel.setName(jogadorRecordDto.name());
            jogadorModel.setNumberShirt(jogadorRecordDto.numberShirt());
            jogadorModel.setPosition(jogadorRecordDto.position());
            return ResponseEntity.status(HttpStatus.OK).body(jogadorRepository.save(jogadorModel));
        }
    }

    @DeleteMapping("/jogador/{id}")
    public ResponseEntity<Object> deleteJogador(@PathVariable (value = "id") Long id){
        Optional<JogadorModel> jogadorO = jogadorRepository.findById(id);
        if (jogadorO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador não encontrado");
        } else {
            jogadorRepository.delete(jogadorO.get());
            return ResponseEntity.status(HttpStatus.OK).body("Jogador excluído com sucesso");
        }
    }
}