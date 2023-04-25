Feature: Crear cuenta de usuario
  AS administrador del campus MatCafe
  WANT crear cuentas de usuario
  BECAUSE tener múltiples usuarios

  @prueba
  Scenario: Crear administrador exitoso
    Given que el administrador realiza una petición para crear un usuario con el rol de administrador
    When el administrador envía la petición con el nombre "iñigo lopez" y el correo "suparcorreo@gmail.com"
    Then se creará el usuario correctamente

  @CrearUsuarios
  Scenario: Crear aprendiz exitoso
    Given que el administrador realiza una petición para crear un usuario con el rol de aprendiz
    When el administrador envía la petición con el nombre "david silva" y el correo "david@gmail.com" del aprendiz
    Then se creará el usuario aprendiz correctamente

  @CrearUsuarios
  Scenario Outline: Envío de datos para la creación de usuario administrador vacíos
    Given el administrador desea crear cuentas de usuario
    When  el administrador envía la petición con  el nombre <name> y el email  <email>
    Then no se creará el usuario con datos vacíos y se recibirá un estatus <status>
    Examples:
      | name    | email             | status |
      | ""      | ""                | 400    |
      | "Admin" | ""                | 400    |
      | ""      | "Admin@gmail.com" | 400    |

  @CrearUsuarios
  Scenario Outline: Envío de datos para la creación de usuario aprendiz vacíos
    Given el administrador desea crear cuentas de usuario aprendiz
    When  el administrador envia la petición crear aprendiz con  el nombre <name> y el email  <email>
    Then no se creara el usuario de aprendiz con datos vacíos y se recibirá un estatus <status>
    Examples:
      | name       | email                | status |
      | ""         | ""                   | 400    |
      | "Aprendiz" | ""                   | 400    |
      | ""         | "Aprendiz@gmail.com" | 400    |

  @CrearUsuarios
  Scenario Outline: Creacion usuarios admin con caracteres especiales y numericos en el nombre
    Given el administrador desea crear usuarios con caracteres especiales
    When  el administrador envía la petición con el nombre truncado <name> y el email  <email>
    Then no se creará el usuario con datos especiales y se recibirá un estatus <status>
    Examples:
      | name       | email             | status |
      | "Pepe123"  | "pe@gmail.com"    | 400    |
      | "123Pepe"  | "pep@gmail.com"   | 400    |
      | "_Pepe"    | "pepe@gmail.com"  | 400    |
      | "Pepe-*_*" | "pepe1@gmail.com" | 400    |
      | "Pepe***"  | "pepe2@gmail.com" | 400    |

  @CrearUsuarios
  Scenario Outline: Creacion usuarios aprendiz con caracteres especiales y numericos en el nombre
    Given el administrador desea crear usuarios de aprendiz con caracteres especiales
    When  el administrador envía la petición con el nombre truncado <name> y el email  <email> para aprendiz
    Then no se creará el usuario de aprendiz con datos especiales y se recibirá un estatus <status>
    Examples:
      | name         | email             | status |
      | "Juan123"    | "jo@gmail.com"    | 400    |
      | "123Juan"    | "joj@gmail.com"   | 400    |
      | "_Juan#"     | "jojo@gmail.com"  | 400    |
      | "Juan/&-*_*" | "jojo1@gmail.com" | 400    |
      | "Juan***"    | "jijo2@gmail.com" | 400    |

  @prueba
  Scenario: Crear administrador existente
    Given que el administrador intenta crear un usuario admin ya existente
    When se envia la petición con el nombre "Franco" y el correo "Franco@auirro1ba.com"
    Then no se creara la cuenta de usuario y se recibirá un codigo de status "400"

  @prueba
  Scenario: Crear aprendiz existente
    Given que el administrador intenta crear un usuario aprendiz ya existente
    When se envia la peticion con el nombre "Juanes" y el correo "juanpz@gmail.com"
    Then no se creara la cuenta de aprendiz y se recibirá un codigo de status "400"

  @prueba
  Scenario: Crear administrador con correo de un aprendiz
    Given que el administrador intenta crear un usuario admin con el correo de un estudiante
    When se envia la peticion con el nombre "Franco" y el correo "juanpz@gmail.com" como aprendiz
    Then no se creara la cuenta y se recibirá un codigo de status "400"

  @prueba
  Scenario: Crear aprendiz con correo de un administrador
    Given que el administrador intenta crear un usuario aprendiz con el correo de un admin
    When se envia la peticion con el nombre "Franco" y el correo "Franco@auirro1ba.com" como admin
    Then no se creara la cuenta aprendiz y se recibirá un codigo de status "400"

  @CrearUsuarios
  Scenario: Crear admin con un correo invalido
    Given que el administrador intenta crear un usuario admin con un correo invalido
    When se envia la peticion con el nombre "Andres vega" y el correo invalido "Franco.com"
    Then no se creara la cuenta de admin se recibira un codigo de status "400"

  @CrearUsuarios
  Scenario: Crear aprendiz con un correo invalido
    Given que el administrador intenta crear un usuario aprendiz con un correo invalido
    When se envia la peticion con el nombre "Paulo vega" y el correo invalido "pepe.com" como aprendiz
    Then no se creara la cuenta de aprendiz se recibira un codigo de status "400"