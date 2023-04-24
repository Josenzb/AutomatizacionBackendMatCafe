Feature: Crear curso de aprendizaje para la pagina de MatCafe
  Yo como administrador del campus MatCafe
  Quiero crear cursos
  Para tener variedad academica


  @CrearCursoConCamposVacios
  Scenario Outline: crear cursos de aprendizaje con campos vacios en el campus de MatCafe
    Given  que el administrador desea crear un curso de aprendizaje con campos vacios
    When envia titulo <title>, descripcion <description>, duracion <duration>, requerimiento <requirements>, contenido  <content> y el adminID <adminID> incluyendo campos vacios
    Then se registrara el curso y  se mostrara estatus <estatusCode>

    Examples:
      | title                  | description                | duration | requirements           | content          | adminID                    | estatusCode |
      | ""                     | "Este es mi primer curso"  | "1"      | "Ningun requerimiento" | "Contenido uno"  | "644690dfbb5b685700e7e05f" | 400         |
      | "Titulo de curso dos"  | ""                         | "2"      | "Ningun requerimiento" | "Contenido dos"  | "644690dfbb5b685700e7e05f" | 400         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | ""       | "Ningun requerimiento" | "Contenido tres" | "644690dfbb5b685700e7e05f" | 400         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "4"      | ""                     | "Contenido tres" | "644690dfbb5b685700e7e05f" | 400         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "4"      | "Ningun requerimiento" | ""               | "644690dfbb5b685700e7e05f" | 400         |
