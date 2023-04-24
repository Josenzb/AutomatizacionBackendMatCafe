Feature: Yo como administrador del campus MatCafe
  Quiero crear rutas de aprendizaje con un listado de cursos
  Para tener los cursos divididos en varias rutas

  Scenario Outline: crear rutas de aprendizaje con campos vacíos
    Given que el administrador crea la ruta de aprendizaje con campos vacios
    When envia titulo <title>, descripcion <description>, duracion <duration>, cursos <courses>, Id admin <adminId>
    Then se registrara la ruta y un codigo de status <code>

    Examples:
      | title          | description               | duration | code | adminId                    |
      | ""             | "Esta es mi primer ruta"  | "28 "    | 400  | "644690dfbb5b685700e7e05f" |
      | "Segunda Ruta" | ""                        | "28"     | 400  | "644690dfbb5b685700e7e05f" |
      | "Tercer  Ruta" | "Esta es mi tercer  ruta" | ""       | 400  | ""                         |
