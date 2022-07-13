package com.estudos.testesUnitaros.controller;


import com.estudos.testesUnitaros.model.Filme;
import com.estudos.testesUnitaros.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    public FilmeService filmeService;

    @GetMapping("/{codigo}")
    public ResponseEntity<Filme> obterFilme(@PathVariable Long codigo) {
        if (codigo < 0) {
            return ResponseEntity.badRequest().build();
        }

        Filme filme = filmeService.obterFilme(codigo);

        if (filme == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filme);
    }


}
