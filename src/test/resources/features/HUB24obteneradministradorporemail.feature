Feature: Buscar administrador por email
  AS administrador del campus MatCafe
  WANT buscar las cuentas de los administradores
  BECAUSE conocer la informacion de la cuenta

  @BuscarAdmistrador
  Scenario: Busqueda de administrador exitosa
    Given que el administrador realiza una petición para obtener la cuenta de un administrador
    When el administrador envía la peticion con el correo del admin "Franco@auirro1ba.com"
    Then se recibira una respuesta con el codigo "200" y una cuenta del admin que contenga el "Franco@auirro1ba.com"