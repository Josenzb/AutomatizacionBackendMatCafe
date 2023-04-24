Feature: Yo como administrador del campus MatCafe
  Quiero crear rutas de aprendizaje con un listado de cursos
  Para tener los cursos divididos en varias rutas

  Scenario Outline: crear rutas de aprendizaje  agregando cursos que no existen
    Given que el administrador asigna los datos para crear una ruta de aprendizaje con cursos que no existen
    When envia datos de titulo <title>, descripcion <description>, duracion <duration>, Id admin <adminId>
    Then no deberian agregarse cursos que no existen a la ruta y un status <estatus>

    Examples:
      | title         | description              | duration | estatus | adminId                    |
      | "Primer Ruta" | "Esta es mi primer ruta" | "28"     | 201     | "644690dfbb5b685700e7e05f" |
