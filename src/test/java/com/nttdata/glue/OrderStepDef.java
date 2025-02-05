package com.nttdata.glue;

import com.nttdata.steps.OrderStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class OrderStepDef {

    @Steps
    OrderStep orderStep;

    @When("creo un nuevo pedido con los siguientes datos:")
    public void creoUnNuevoPedidoConLosSiguientesDatos(String requestBody) {
        orderStep.crearPedido(requestBody);
    }

    @When("consulto un pedido con orderId {int}")
    public void consultoUnPedidoConOrderId(int orderId) {
        orderStep.consultarPedido(orderId);
    }

    @Then("valido que el codigo de respuesta sea {int}")
    public void validoQueElCodigoDeRespuestaSea(int statusCode) {
        orderStep.validarCodigoRespuesta(statusCode);
    }

    @And("la respuesta debe contener el valor {string} que coincide con {int}")
    public void laRespuestaDebeContenerElValorQueCoincideConOrderId(String compare, int orderIdEsperado) {
        orderStep.validarBodyRespuesta(compare, orderIdEsperado);
    }
}
