@ProbarFeatureOrder
Feature: Pedidos

  @crearNuevoPedido
  Scenario Outline: Crear un nuevo pedido
    When creo un nuevo pedido con los siguientes datos:
    """
      {
        "id": <orderId>,
        "petId": <petId>,
        "quantity": <quantity>,
        "status": "<status>",
        "complete": <complete>
      }
      """
    Then valido que el codigo de respuesta sea 200
    And la respuesta debe contener el valor "id" que coincide con <orderId>

    Examples:
      | orderId | petId | quantity | status    | complete |
      | 100105  | 10    | 2        | approved  | true     |

  @consultarPedido
  Scenario Outline: Consultar un pedido
    When consulto un pedido con orderId <orderId>
    Then valido que el codigo de respuesta sea 200
    And la respuesta debe contener el valor "id" que coincide con <orderId>

    Examples:
      | orderId |
      | 100105  |
