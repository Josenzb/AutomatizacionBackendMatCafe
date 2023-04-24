Feature: crear ruta
  Yo como administrador del campus MatCafe
  Quiero crear rutas de aprendizaje con un listado de cursos
  Para tener los cursos divididos en varias rutas

  Scenario Outline: crear rutas de aprendizaje de manera exitosa
    Given que el administrador crea la ruta de aprendizaje
    When envia titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then se registrara la ruta y status <code>

    Examples:
      | title          | description              | duration | code | adminId                    |
      | "Primer Ruta0" | "Esta es mi primer ruta" | "28"     | 201  | "644690dfbb5b685700e7e05f" |
