Feature: Obtener ruta de aprendizaje por id
  Yo como administrador del campus MatCafe
  Quiero ver la ruta de aprendizaje
  Para tener la ruta de aprendizaje con la informacion correcta

  @CP030_ObtenerRutaPorIdRegistrados
  Scenario Outline: obtener ruta de aprendizaje por id
    Given que el administrador esta en el servicio de rutas
    When se envia la peticion para obtener  ruta por <id>
    Then se mostrara la informacion de la ruta de aprendizaje junto con el estatus <estatus>

    Examples:
      | id                         | estatus |
      | "64495081d5f5ed32cf12a55a" | 200     |
      | "644843360be4ee3caef787c7" | 200     |

  @CP031_ObtenerRutaPorIdNoRegistrados
  Scenario Outline: Envio de un id  que no estaregistrado
    Given el administrador esta en el servicio de rutas
    When se envia la peticion para obtener  ruta por <id> de ruta no registrada
    Then se mostrara un mensaje de error junto con el estatus <estatus>

    Examples:
      | id        | estatus |
      | "644739e" | 409     |
      | "9l00"    | 409     |
      | "1234"    | 409     |
      | "***"     | 409     |
