Feature: Obtener ruta de aprendizaje por id
  Yo como administrador del campus MatCafe
  Quiero ver la ruta de aprendizaje
  Para tener la ruta de aprendizaje con la información correcta

  @CP030_ObtenerRutaPorIdRegistrados
  Scenario Outline: obtener ruta de aprendizaje por id
    Given que el administrador esta en el servicio de rutas
    When se envia la peticion para obtener  ruta por <id>
    Then se mostrara la informacion de la ruta de aprendizaje junto con el estatus <estatus>

    Examples:
      | id                         | estatus |
      | "64473eaff3ba6e5168dfd938" | 200     |
      | "64483afbad4196e782e8324b" | 200     |

  @CP031_ObtenerRutaPorIdNoRegistrados
  Scenario Outline: Envio de un id  que no estaregistrado
    Given el administrador esta en el servicio de rutas
    When se envia la peticion para obtener  ruta por <id> de ruta no registrada
    Then se mostrara un mensaje de error junto con el estatus <estatus>

    Examples:
      | id        | estatus |
      | "644739e" | 400     |
      | "9l00"    | 400     |
      | "1234"    | 400     |
      | "***"     | 400     |
