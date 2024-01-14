package com.mballem.tarefas.web.controller;

import com.mballem.internal.entity.Contato;
import com.mballem.internal.service.ContatoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("tarefas/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping()
    // EXERCICIO 1
    public Object create(@RequestBody Contato contatoBody) {
        Contato contato = contatoService.save(contatoBody);

        return ResponseEntity.ok().body(contato);
    }

    @GetMapping("/{id}")
    // EXERCICIO 2
    public Object getContatoById(@PathVariable Long id) {
        Contato contato = contatoService.getById(id);

        return ResponseEntity.ok().body(contato);
    }

    @GetMapping("/nomes/{nome}")
    // EXERCICIO 3
    public Object getContatoByNome(@PathVariable String nome) {
        Contato contato = contatoService.getContatoByNome(nome);

        return ResponseEntity.ok().body(contato);
    }

    @GetMapping
    // EXERCICIO 4
    public Object getQuantidadeDeContatos() {
        return ResponseEntity.ok().body(contatoService.getAll());
    }

    @GetMapping("/idades/{nascimento}")
    // EXERCICIO 5
    public Object getContatosByDataNascimento(@PathVariable LocalDate nascimento) {
        List<Contato> contatos = new ArrayList<>();
        contatos = contatoService.getByDataNascimento(nascimento);

        return ResponseEntity.ok().body(contatos);
    }

    @PatchMapping("/{id}")
    // EXERCICIO 6
    public Object updateContatoById(@PathVariable Long id, @RequestBody Contato contatoBody) {
        Contato contato = contatoService.update(id, contatoBody);

        return ResponseEntity.ok().body(contato);
    }

    @DeleteMapping("/{id}")
    // EXERCICIO 7
    public Object deleteById(@PathVariable Long id) {
        String contato = contatoService.delete(id);

        return ResponseEntity.ok().body(contato);
    }
}
