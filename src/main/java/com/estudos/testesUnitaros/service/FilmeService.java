package com.estudos.testesUnitaros.service;


import com.estudos.testesUnitaros.model.Filme;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    public Filme obterFilme(Long codigo) {
        if (codigo > 10) {
            return null;
        }
        return new Filme(
                codigo,
                "Star Wars",
                "O imperio contra ataca"
        );
    }

}
