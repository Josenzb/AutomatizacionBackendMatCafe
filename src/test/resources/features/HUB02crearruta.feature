Feature: Crear ruta
  Yo como administrador del campus MatCafe
  Quiero crear rutas de aprendizaje con un listado de cursos
  Para tener los cursos divididos en varias rutas

@CP16_CrearRutaExitosa
  Scenario Outline: crear rutas de aprendizaje de manera exitosa
    Given que el administrador crea la ruta de aprendizaje
    When envia titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then se registrara la ruta y status <code>

    Examples:
      | title          | description              | duration | code | adminId                    |
      | "Primer Ruta0" | "Esta es mi primer ruta" | "28"     | 201  | "644690dfbb5b685700e7e05f" |

@CP17_RutaConCursosNoRegistrados
  Scenario Outline: crear rutas de aprendizaje  agregando cursos que no existen
    Given que el administrador asigna los datos para crear una ruta de aprendizaje con cursos que no existen
    When envia datos de titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then no deberian agregarse cursos que no existen a la ruta y un status <estatus>

    Examples:
      | title         | description              | duration | estatus | adminId                    |
      | "Primer Ruta" | "Esta es mi primer ruta" | "28"     | 201     | "644690dfbb5b685700e7e05f" |

  @CP18_RutaSinCursos
  Scenario Outline: crear rutas de aprendizaje sin cursos
    Given que el administrador crea la ruta de aprendizaje vacia
    When envia el titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then se registrara la ruta y un status <code>

    Examples:
      | title          | description              | duration | code | adminId                    |
      | "Primer Ruta9" | "Esta es mi primer ruta" | "28"     | 201  | "644690dfbb5b685700e7e05f" |

  @CP19_RutaConCamposVacios
    Scenario Outline: crear rutas de aprendizaje con campos vacíos
      Given que el administrador crea la ruta de aprendizaje con campos vacios
      When envia titulo <title>, descripcion <description>, duracion <duration>, cursos, Id admin <adminId>
      Then se registrara la ruta y un codigo de status <code>

      Examples:
        | title          | description               | duration | code | adminId                    |
        | ""             | "Esta es mi primer ruta"  | "28 "    | 400  | "644690dfbb5b685700e7e05f" |
        | "Segunda Ruta" | ""                        | "28"     | 400  | "644690dfbb5b685700e7e05f" |
        | "Tercer  Ruta" | "Esta es mi tercer  ruta" | ""       | 400  | ""                         |
