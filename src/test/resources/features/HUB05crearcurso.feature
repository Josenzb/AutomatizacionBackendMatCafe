Feature: Crear curso de aprendizaje para la pagina de MatCafe
  yo como administrador del campus MatCafe
  Quiero crear cursos
  Para tener variedad academica

  @CrearCursoCP_020
  Scenario Outline: crear cursos de aprendizaje de manera exitosa
    Given  que el administrador desea crear un curso de aprendizaje
    When envia titulo <title>, descripcion <description>, duracion <duration>, requerimiento <requirements>, contenido  <content> y el adminID <adminID>
    Then se registrara el curso y estatus <estatusCode>

    Examples:
      | title                  | description                | duration | requirements           | content          | adminID                    | estatusCode |
      | "Titulo de curso uno"  | "Este es mi primer curso"  | "1"      | "Ningun requerimiento" | "Contenido uno"  | "644690dfbb5b685700e7e05f" | 201         |
      | "Titulo de curso dos"  | "Este es mi segundo curso" | "2"      | "Ningun requerimiento" | "Contenido dos"  | "644690dfbb5b685700e7e05f" | 201         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "3"      | "Ningun requerimiento" | "Contenido tres" | "644690dfbb5b685700e7e05f" | 201         |


  @CrearCursoConCamposVaciosCP_021
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


  @CrearCursoConUnIdDeAdministradorInexistenteScenarioCP_022
  Scenario Outline:  crear cursos de aprendizaje con un id de admin inexistente
    Given un usuario que no es administrador desea crear un curso
    When envia titulo <title>, descricion <description>, duracion <duration>, requerimientos <requirements>, contenido <content> y el adminID incorrecto <adminID>
    Then se generara un mensaje de error junto con es estatus <estatusCode>

    Examples:
      | title                  | description                | duration | requirements           | content          | adminID  | estatusCode |
      | "Titulo de curso uno"  | "Este es mi primer curso"  | "1"      | "Ningun requerimiento" | "Contenido uno"  | "1234"   | 500         |
      | "Titulo de curso dos"  | "Este es mi segundo curso" | "2"      | "Ningun requerimiento" | "Contenido dos"  | "12345"  | 500         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "3"      | "Ningun requerimiento" | "Contenido tres" | "123456" | 500         |
