Feature: Buscar cuentas por email
  AS administrador del campus MatCafe
  WANT buscar las cuentas registradas
  BECAUSE conocer la informacion de la cuenta

  @BuscarCuentas
  Scenario Outline: Busqueda de cuenta exitosa
    Given que el administrador realiza una peticion para obtener una cuenta
    When el administrador envia la peticion con el correo <correo> que desea buscar
    Then se recibira una respuesta con <status> y una cuenta que contenga el <correo>
    Examples:
      | correo                 | status |
      | "Franco@auirro1ba.com" | 200    |
      | "juanpz@gmail.com"     | 200    |

  @BuscarCuentas
  Scenario Outline: Busqueda de cuentas fallida
    Given que el administrador realiza una peticion para obtener una cuenta invalida
    When el administrador envaa la peticion con el correo invalido <correo>
    Then se recibira un codigo <status> y un mensaje de correo no encontrado
    Examples:
      | correo          | status |
      | "abel@abel.com" | 409    |
      | "correojejej"   | 409    |
      | "4654613264"    | 409    |