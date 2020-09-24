package com.gabriel.totem.controllers;

import com.gabriel.totem.models.Funcionario;
import com.gabriel.totem.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;


    @GetMapping
    public ResponseEntity<Page<Funcionario>> index(Pageable pageable) {
        Page funcionarios = funcionarioService.listAll(pageable);
        return ResponseEntity.ok(funcionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> show(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.listOne(id));
    }

    @PostMapping
    public ResponseEntity<Funcionario> store(@RequestBody Funcionario funcionario,
                                             UriComponentsBuilder uriComponentsBuilder) {

        Funcionario funcionario1 = funcionarioService.save(funcionario);
        URI uri = uriComponentsBuilder.path("/funcionarios/{id}").buildAndExpand(funcionario1.getId()).toUri();
        return ResponseEntity.created(uri).body(funcionario1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        funcionario.setId(id);
        return ResponseEntity.ok(funcionarioService.save(funcionario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
