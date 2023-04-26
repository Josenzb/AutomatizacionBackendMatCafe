Feature: Buscar administrador por email
  AS administrador del campus MatCafe
  WANT buscar las cuentas de los administradores
  BECAUSE conocer la informacion de la cuenta

  @BuscarAdmistrador
  Scenario: Busqueda de administrador exitosa
    Given que el administrador realiza una peticion para obtener la cuenta de un administrador
    When el administrador envia la peticion con el correo del admin "Franco@auirro1ba.com"
    Then se recibira una respuesta con el codigo "200" y una cuenta del admin que contenga el "Franco@auirro1ba.com"

  @BuscarAdmistrador
  Scenario Outline: Busqueda de admin fallida
    Given que el administrador realiza una peticion para obtener la cuenta de un admin
    When el administrador envia la peticion con correos no registrados <correo> para buscar admins
    Then se recibira una respuesta con el codigo <status> y un mensaje de correo no encontrado en admin
    Examples:
      | correo          | status |
      | "abel@abel.com" | 404    |
      | "correojejej"   | 404    |
      | "4654613264"    | 404    |