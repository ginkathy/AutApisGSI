package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Assert;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class OrderStep {

    private static String CREATE_ORDER = "https://petstore.swagger.io/v2/store/order";
    private static String SEARCH_ORDER = "https://petstore.swagger.io/v2/store/order";

    private Response response;
    private RequestSpecBuilder builder;
    private RequestSpecification requestSpecification;

    public void crearPedido(String requestBody) {
        SerenityRest.given()
                .contentType("application/json")
                .relaxedHTTPSValidation()
                .body(requestBody)
                .log().all()
                .post(CREATE_ORDER)
                .then()
                .log().all()
        ;
    }

    public void validarCodigoRespuesta(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    public void consultarPedido(int orderId) {
        SerenityRest.given()
                .baseUri(SEARCH_ORDER)
                .relaxedHTTPSValidation()
                .log().all()
                .when()
                .get("/" + String.valueOf(orderId))
                .then()
                .log().all()
        ;
    }

    public void sendPostRequest(String api) {
        response = given().spec(requestSpecification).when().post(api);
    }

    public void inicializoParametrosRequestPost() {
        RestAssured.baseURI = CREATE_ORDER;
        builder = new RequestSpecBuilder();
        requestSpecification = builder.build();
    }

    public void validarBodyRespuesta(String compare, int orderIdEsperado) {
        int respuesta = SerenityRest.lastResponse().getBody().path(compare);
        Assert.assertEquals(orderIdEsperado, respuesta);
    }
}
