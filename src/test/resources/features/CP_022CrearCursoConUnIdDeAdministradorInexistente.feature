Feature: Crear curso de aprendizaje para la pagina de MatCafe
  Yo como administrador del campus MatCafe
  Quiero crear cursos
  Para tener variedad academica

  @CrearCursoConUnIdDeAdministradorInexistenteScenario
  Scenario Outline:  crear cursos de aprendizaje con un id de admin inexistente
    Given un usuario que no es administrador desea crear un curso
    When envia titulo <title>, descricion <description>, duracion <duration>, requerimientos <requirements>, contenido <content> y el adminID incorrecto <adminID>
    Then se generara un mensaje de error junto con es estatus <estatusCode>

    Examples:
      | title                  | description                | duration | requirements           | content          | adminID  | estatusCode |
      | "Titulo de curso uno"  | "Este es mi primer curso"  | "1"      | "Ningun requerimiento" | "Contenido uno"  | "1234"   | 400         |
      | "Titulo de curso dos"  | "Este es mi segundo curso" | "2"      | "Ningun requerimiento" | "Contenido dos"  | "12345"  | 400         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "3"      | "Ningun requerimiento" | "Contenido tres" | "123456" | 400         |
