Feature: Crear ruta de aprendizaje
  Yo como administrador del campus MatCafe
  Quiero crear rutas de aprendizaje con un listado de cursos
  Para tener los cursos divididos en varias rutas

  @CP16_CrearRutaExitosa
  Scenario Outline: crear rutas de aprendizaje de manera exitosa
    Given que el administrador crea la ruta de aprendizaje
    When envia titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then se registrara la ruta y status <code>

    Examples:
      | title                | description              | duration   | code | adminId                    |
      | "Ruta aprendizaje 1" | "Esta es mi primer ruta" | "28 dias"  | 201  | "644739c75681b2a2806c11bb" |
      | "ruta 3.5"           | "Ruta aprendizaje 9"     | "28 horas" | 201  | "644739c75681b2a2806c11bb" |
      | "ruta 345678"        | "Ruta aprendizaje 10"    | "28 horas" | 201  | "644739c75681b2a2806c11bb" |
      | "ruta 34,56"         | "Ruta aprendizaje 10"    | "28 horas" | 201  | "644739c75681b2a2806c11bb" |

  @CP17_RutaConCursosNoRegistrados
  Scenario Outline: crear rutas de aprendizaje  agregando cursos que no existen
    Given que el administrador asigna los datos para crear una ruta de aprendizaje con cursos que no existen
    When envia datos de titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then no deberian agregarse cursos que no existen a la ruta y un status <estatus>

    Examples:
      | title                | description              | duration | estatus | adminId                    |
      | "Ruta aprendizaje 2" | "Esta es mi primer ruta" | "28"     | 409     | "644739c75681b2a2806c11bb" |

  @CP18_RutaSinCursos
  Scenario Outline: crear rutas de aprendizaje sin cursos
    Given que el administrador crea la ruta de aprendizaje vacia
    When envia el titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then no se registrara la ruta y retornara un status <code>

    Examples:
      | title                | description              | duration | code | adminId                    |
      | "Ruta aprendizaje 3" | "Esta es mi primer ruta" | "28"     | 409  | "644739c75681b2a2806c11bb" |

  @CP19_RutaConCamposVacios
  Scenario Outline: crear rutas de aprendizaje con campos vacios
    Given que el administrador crea la ruta de aprendizaje con campos vacios
    When envia titulo <title>, descripcion <description>, duracion <duration>, cursos, Id admin <adminId>
    Then se registrara la ruta y un codigo de status <code>

    Examples:
      | title                | description               | duration | code | adminId                    |
      | ""                   | "Esta es mi primer ruta"  | "28 "    | 409  | "644739c75681b2a2806c11bb" |
      | "Ruta aprendizaje 4" | ""                        | "28"     | 409  | "644739c75681b2a2806c11bb" |
      | "Ruta aprendizaje 5" | "Esta es mi tercer  ruta" | ""       | 409  | ""                         |

  @CP34_CamposConCaracteresEspeciales
  Scenario Outline: crear rutas de aprendizaje con caracteres especiales
    Given que el administrador crea la ruta de aprendizaje con caracteres especiales
    When envia un titulo <title>, una descripcion <description>, duracion <duration>, Id admin <adminId>
    Then se registrara la ruta y con un status <code>

    Examples:
      | title    | description          | duration   | code | adminId                    |
      | "*++"    | "Ruta aprendizaje 6" | "28 horas" | 409  | "644739c75681b2a2806c11bb" |
      | " ????_" | "Ruta aprendizaje 7" | "28 horas" | 409  | "644739c75681b2a2806c11bb" |
      | "????_a" | "Ruta aprendizaje 8" | "28 horas" | 409  | "644739c75681b2a2806c11bb" |
      | "4000.5" | "Ruta aprendizaje 9" | "28 horas" | 409  | "644739c75681b2a2806c11bb" |
      | " a???_" | "a???_"              | "a???_"    | 409  | "644739c75681b2a2806c11bb" |
      | " b???_" | "7999"               | "789999"   | 409  | "644739c75681b2a2806c11bb" |
      | "ruta2"  | "ruta2"              | "789999"   | 409  | "644739c75681b2a2806c11bb" |
  @CP35_CrearRutaExistente
  Scenario Outline: crear rutas de aprendizaje que ya existe
    Given que el administrador crea la ruta de aprendizaje registrada
    When envia datos titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then no registrara la ruta y retorna un status <code>

    Examples:
      | title                | description              | duration  | code | adminId                    |
      | "Ruta aprendizaje 1" | "Esta es mi primer ruta" | "28 dias" | 409  | "644739c75681b2a2806c11bb" |
