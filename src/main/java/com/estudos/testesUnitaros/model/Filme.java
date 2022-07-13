package com.estudos.testesUnitaros.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Filme {

    private Long codigo;
    private String titulo;
    private String descricao;
}
