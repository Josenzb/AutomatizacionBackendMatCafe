Feature: Buscar aprendices por email
  AS administrador del campus MatCafe
  WANT buscar las cuentas de los aprendices
  BECAUSE conocer la informacion de la cuenta

  @BuscarAprendiz
  Scenario: Busqueda de aprendiz exitosa
    Given que el administrador realiza una peticion para obtener la cuenta de un aprendiz
    When el administrador envia la peticion con el correo "juanpz@gmail.com"
    Then se recibira una respuesta con el codigo "200" y una cuenta que contenga el "juanpz@gmail.com"

  @BuscarAprendiz
  Scenario Outline: Busqueda de aprendiz fallida
    Given que el administrador realiza una peticion para obtener la cuenta de un aprendiz
    When el administrador envia la peticion con correos no registrados <correo>
    Then se recibira una respuesta con el codigo <status> y un mensaje de correo no encontrado
    Examples:
      | correo          | status |
      | "abel@abel.com" | 404    |
      | "correojejej"   | 404    |
      | "4654613264"    | 404    |