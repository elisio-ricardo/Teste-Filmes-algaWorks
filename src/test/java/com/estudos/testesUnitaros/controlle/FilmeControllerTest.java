package com.estudos.testesUnitaros.controlle;


import com.estudos.testesUnitaros.controller.FilmeController;
import com.estudos.testesUnitaros.model.Filme;
import com.estudos.testesUnitaros.service.FilmeService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

@WebMvcTest
public class FilmeControllerTest {

    @Autowired
    public FilmeController filmeController;

    @MockBean
    private FilmeService filmeService;

    @BeforeEach
    public void setup() {
        //Esse metodo garante que só vai ser chamado o controller de filmes
        standaloneSetup(filmeController);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarFilme() {

        Mockito.when(filmeService.obterFilme(1L))
                .thenReturn(new Filme(
                        1L,
                        "Star Wars",
                        "O imperio contra ataca"
                ));
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/filmes/{codigo}", 1L)
                .then()
                .statusCode(HttpStatus.OK.value());

        Mockito.verify(filmeService, Mockito.times(1)).obterFilme(1L);
    }

    @Test
    public void deveRetornarNaoEncontrado_QuandoBuscarFilme() {
        Mockito.when(filmeService.obterFilme(5L)).thenReturn(null);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/filmes/{codigo}", 5L)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

        Mockito.verify(filmeService, Mockito.times(1)).obterFilme(5l);
    }

    @Test
    public void deveRetornarBadRequest() {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/filmes/{codigo}", -1L)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());

        Mockito.verify(filmeService, Mockito.never()).obterFilme(-1L);
    }

}
