package com.montanha.isolada;

import com.montanha.factory.UsuarioDataFactory;
import com.montanha.factory.ViagemDataFatory;
import com.montanha.pojo.Usuario;
import com.montanha.pojo.Viagem;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ViagensTest {
    private String token;

    @Before
    public void setUp() {
        // Configurações RestAssured
        baseURI = "http://localhost";
        port = 8089;
        basePath = "/api";

        Usuario usuarioAdministrador = UsuarioDataFactory.criarUsuarioAdministrador();

        this.token = given()
            .contentType(ContentType.JSON)
            .body(usuarioAdministrador)
        .when()
            .post("/v1/auth")
        .then()
            .extract()
                .path("data.token");
    }
        @Test
        public void cadastroDeViagemValidaRetornaSucesso () throws IOException {
            Viagem viagemValida = ViagemDataFatory.criarViagemValida();

            given()
                    .contentType(ContentType.JSON)
                    .body(viagemValida)
                    .header("Authorization", token)
                    .when()
                    .post("/v1/viagens")
                    .then()
                    .assertThat()
                    .statusCode(201)
                    .body("data.regiao", equalToIgnoringCase("nordeste"));
        }

    @Test
    public void cadastroDeViagemValidaErroSemLocalDeDestino () throws IOException {
        Viagem viagemSemLocalDeDestino = ViagemDataFatory.criarViagemSemLocalDeDestino();

        given()
            .contentType(ContentType.JSON)
            .body(viagemSemLocalDeDestino)
            .header("Authorization", token)
        .when()
            .post("/v1/viagens")
        .then()
            .log()
                .all()
            .assertThat()
                .statusCode(400);
                //.body("errors.defaultMessage", equalToIgnoringCase("Local de Destino deve estar entre 3 e 40 caracteres"));
    }

}
