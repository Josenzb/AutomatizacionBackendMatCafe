Feature: Buscar aprendices por email
  AS administrador del campus MatCafe
  WANT buscar las cuentas de los aprendices
  BECAUSE conocer la informacion de la cuenta

  @BuscarAprendiz
  Scenario: Busqueda de aprendiz exitosa
    Given que el administrador realiza una petición para obtener la cuenta de un aprendiz
    When el administrador envía la petición con el correo "juanpz@gmail.com"
    Then se recibira una respuesta con el codigo "200" y una cuenta que contenga el "juanpz@gmail.com"