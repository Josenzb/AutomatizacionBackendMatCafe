Feature: Actualizar ruta de aprendizaje
  Yo como administrador del campus MatCafe
  Quiero editar la ruta de aprendizaje
  Para tener la ruta de aprendizaje con la información correcta

  @CP037_ActualizarRuta
  Scenario Outline: actualizar rutas de aprendizaje de manera exitosa
    Given que el administrador esta en el servicio de actualizar rutas
    When envia  datos de actualizacion id <id>, titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then se actualizara la ruta junto con un status <code>

    Examples:
      | title              | description           | duration  | code | adminId                    | id                         |  |
      | "Ruta Actualizada" | "Mi ruta actualizada" | "5 horas" | 200  | "644739c75681b2a2806c11bb" | "64481f7de11f94e014380d64" |  |

  @CP038_ActualizarRutaConCaracteres
  Scenario Outline: actualizar rutas de aprendizaje enviando caracteres especiales
    Given el administrador esta en el servicio de actualizar rutas
    When envia  datos para actualizar id <id>, titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then no se actualizara la ruta junto con un status <code>

    Examples:
      | title    | description           | duration | code | adminId                    | id                         |  |
      | "345569" | "Mi ruta actualizada" | "5 ??"   | 400  | "644739c75681b2a2806c11bb" | "64481f7de11f94e014380d64" |  |
      | "345569" | "Mi ruta actualizada" | "5 ??"   | 400  | "644739c75681b2a2806c11bb" | "64481f7de11f94e014380d64" |  |
      | "345 "   | "m1 rut4"             | "5 ??"   | 400  | "644739c75681b2a2806c11bb" | "64481f7de11f94e014380d64" |  |
      | "++++ p" | ""                    | "5 ?9"   | 400  | "644739c75681b2a2806c11bb" | "64481f7de11f94e014380d64" |  |

  @CP039_ActualizarRutaConCamposVacios
  Scenario Outline: actualizar rutas de aprendizaje enviando campos vacios con admin Id y Id validos
    Given el administrador ingresa servicio de actualizar rutas
    When envia  datos con campos vacios id <id>, titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then no se actualizara la ruta con los campos enviados y retorna un status <code>

    Examples:
      | title                  | description           | duration  | code | adminId                    | id                         |  |
      | " "                    | "Mi ruta actualizada" | "5 horas" | 400  | "644739c75681b2a2806c11bb" | "64481f7de11f94e014380d64" |  |
      | "Ruta de prueba "      | " "                   | "5 horas" | 400  | "644739c75681b2a2806c11bb" | "64481f7de11f94e014380d64" |  |
      | "Ruta de prueba vol.1" | "Actualizando ruta"   | " "       | 400  | "644739c75681b2a2806c11bb" | "64481f7de11f94e014380d64" |  |
      | ""                     | ""                    | ""        | 400  | "644739c75681b2a2806c11bb" | "644739c75681b2a2806c11bb" |  |
  @CP040_ActualizarRutaConIdInvalidos
  Scenario Outline: actualizar rutas de aprendizaje con id no registrados
    Given el administrador esta en el servicio para actualizar rutas de aprendizaje
    When envia  datos con campos de id <id> no registrados, titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then no se actualizara la ruta  y retorna un status <code>

    Examples:
      | title                        | description           | duration  | code | adminId                   | id                        |  |
      | "Nueva ruta de aprendizaje " | "Mi ruta actualizada" | "5 horas" | 400  | "644739c75681b2a2806c11b" | "64481f7de11f94e014380d4" |  |
      | "Ruta de prueba "            | " "                   | "5 horas" | 400  | "644739c75681b22806c11bb" | "6448f7de11f94e014380d64" |  |