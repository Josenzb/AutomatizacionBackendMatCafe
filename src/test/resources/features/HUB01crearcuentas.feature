Feature: Crear cuenta de usuario
  AS administrador del campus MatCafe
  WANT crear cuentas de usuario
  BECAUSE tener múltiples usuarios

  @CrearUsuarios
  Scenario: Crear administrador exitoso
    Given que el administrador realiza una peticion para crear un usuario con el rol de administrador
    When el administrador envia la peticion con el nombre "iñigo lopez" y el correo "suparcorreo@gmail.com"
    Then se creara el usuario correctamente

  @CrearUsuarios
  Scenario: Crear aprendiz exitoso
    Given que el administrador realiza una peticion para crear un usuario con el rol de aprendiz
    When el administrador envia la peticion con el nombre "david silva" y el correo "david@gmail.com" del aprendiz
    Then se creara el usuario aprendiz correctamente

  @CrearUsuarios
  Scenario Outline: Envio de datos para la creacion de usuario administrador vacios
    Given el administrador desea crear cuentas de usuario
    When  el administrador envia la peticion con  el nombre <name> y el email  <email>
    Then no se creara el usuario con datos vacios y se recibira un estatus <status>
    Examples:
      | name    | email             | status |
      | ""      | ""                | 409    |
      | "Admin" | ""                | 409    |
      | ""      | "Admin@gmail.com" | 409    |

  @CrearUsuarios
  Scenario Outline: Envio de datos para la creacion de usuario aprendiz vacios
    Given el administrador desea crear cuentas de usuario aprendiz
    When  el administrador envia la peticion crear aprendiz con  el nombre <name> y el email  <email>
    Then no se creara el usuario de aprendiz con datos vacios y se recibira un estatus <status>
    Examples:
      | name       | email                | status |
      | ""         | ""                   | 409    |
      | "Aprendiz" | ""                   | 409    |
      | ""         | "Aprendiz@gmail.com" | 409    |

  @CrearUsuarios
  Scenario Outline: Creacion usuarios admin con caracteres especiales y numericos en el nombre
    Given el administrador desea crear usuarios con caracteres especiales
    When  el administrador envia la peticion con el nombre truncado <name> y el email  <email>
    Then no se creara el usuario con datos especiales y se recibira un estatus <status>
    Examples:
      | name       | email             | status |
      | "Pepe123"  | "pe@gmail.com"    | 409    |
      | "123Pepe"  | "pep@gmail.com"   | 409    |
      | "_Pepe"    | "pepe@gmail.com"  | 409    |
      | "Pepe-*_*" | "pepe1@gmail.com" | 409    |
      | "Pepe***"  | "pepe2@gmail.com" | 409    |

  @CrearUsuarios
  Scenario Outline: Creacion usuarios aprendiz con caracteres especiales y numericos en el nombre
    Given el administrador desea crear usuarios de aprendiz con caracteres especiales
    When  el administrador envia la peticion con el nombre truncado <name> y el email  <email> para aprendiz
    Then no se creara el usuario de aprendiz con datos especiales y se recibira un estatus <status>
    Examples:
      | name         | email             | status |
      | "Juan123"    | "jo@gmail.com"    | 409    |
      | "123Juan"    | "joj@gmail.com"   | 409    |
      | "_Juan#"     | "jojo@gmail.com"  | 409    |
      | "Juan/&-*_*" | "jojo1@gmail.com" | 409    |
      | "Juan***"    | "jijo2@gmail.com" | 409    |

  @CrearUsuarios
  Scenario: Crear administrador existente
    Given que el administrador intenta crear un usuario admin ya existente
    When se envia la peticion con el nombre "Franco" y el correo "Franco@auirro1ba.com"
    Then no se creara la cuenta de usuario y se recibira un codigo de status "409"

  @CrearUsuarios
  Scenario: Crear aprendiz existente
    Given que el administrador intenta crear un usuario aprendiz ya existente
    When se envia la peticion con el nombre "Juanes" y el correo "juanpz@gmail.com" como aprendiz
    Then no se creara la cuenta de aprendiz y se recibira un codigo de status "409"

  @CrearUsuarios
  Scenario: Crear administrador con correo de un aprendiz
    Given que el administrador intenta crear un usuario admin con el correo de un estudiante
    When se envia la peticion con el nombre "Franco" y el correo "juanpz@gmail.com" con rol aprendiz
    Then no se creara la cuenta y se recibira un codigo de status "409"

  @CrearUsuarios
  Scenario: Crear aprendiz con correo de un administrador
    Given que el administrador intenta crear un usuario aprendiz con el correo de un admin
    When se envia la peticion con el nombre "Franco" y el correo "Franco@auirro1ba.com" como admin
    Then no se creara la cuenta aprendiz y se recibira un codigo de status "409"

  @CrearUsuarios
  Scenario: Crear admin con un correo invalido
    Given que el administrador intenta crear un usuario admin con un correo invalido
    When se envia la peticion con el nombre "Andres vega" y el correo invalido "Franco.com"
    Then no se creara la cuenta de admin se recibira un codigo de status "409"

  @CrearUsuarios
  Scenario: Crear aprendiz con un correo invalido
    Given que el administrador intenta crear un usuario aprendiz con un correo invalido
    When se envia la peticion con el nombre "Paulo vega" y el correo invalido "pepe.com" como aprendiz
    Then no se creara la cuenta de aprendiz se recibira un codigo de status "409"