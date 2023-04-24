Feature: Crear ruta sin cursos
  Yo como administrador del campus MatCafe
  Quiero crear rutas de aprendizaje con un listado de cursos
  Para tener los cursos divididos en varias rutas

  Scenario Outline: crear rutas de aprendizaje sin cursos
    Given que el administrador crea la ruta de aprendizaje vacia
    When envia el titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then se registrara la ruta y un status <code>

    Examples:
      | title          | description              | duration | code | adminId                    |
      | "Primer Ruta9" | "Esta es mi primer ruta" | "28"     | 201  | "644690dfbb5b685700e7e05f" |
