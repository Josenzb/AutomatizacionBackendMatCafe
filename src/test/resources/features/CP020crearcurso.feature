Feature: Crear curso de aprendizaje para la pagina de MatCafe
  yo como administrador del campus MatCafe
  Quiero crear cursos
  Para tener variedad academica

  @CrearCurso
  Scenario Outline: crear cursos de aprendizaje de manera exitosa
    Given  que el administrador desea crear un curso de aprendizaje
    When envia titulo <title>, descripcion <description>, duracion <duration>, requerimiento <requirements>, contenido  <content> y el adminID <adminID>
    Then se registrara el curso y estatus <estatusCode>

    Examples:
      | title                  | description                | duration | requirements           | content          | adminID                    | estatusCode |
      | "Titulo de curso uno"  | "Este es mi primer curso"  | "1"      | "Ningun requerimiento" | "Contenido uno"  | "644690dfbb5b685700e7e05f" | 201         |
      | "Titulo de curso dos"  | "Este es mi segundo curso" | "2"      | "Ningun requerimiento" | "Contenido dos"  | "644690dfbb5b685700e7e05f" | 201         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "3"      | "Ningun requerimiento" | "Contenido tres" | "644690dfbb5b685700e7e05f" | 201         |
