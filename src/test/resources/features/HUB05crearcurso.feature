Feature: Crear curso de aprendizaje para la pagina de MatCafe
  yo como administrador del campus MatCafe
  Quiero crear cursos
  Para tener variedad academica

  @CP_020CrearCursoExitosamente
  Scenario Outline: crear cursos de aprendizaje de manera exitosa
    Given  que el administrador desea crear un curso de aprendizaje
    When envia titulo <title>, descripcion <description>, duracion <duration>, requerimiento <requirements>, contenido  <content> y el adminID <adminID>
    Then se registrara el curso y estatus <estatusCode>

    Examples:
      | title                     | description                      | duration   | requirements           | content          | adminID                    | estatusCode |
      | "Titulo de curso diez"    | "Este es mi primer super curso"  | "28 dias " | "Ningun requerimiento" | "Contenido uno"  | "644739c75681b2a2806c11bb" | 201         |
      | "Titulo de curso veinte"  | "Este es mi segundo super curso" | "28 dias " | "Ningun requerimiento" | "Contenido dos"  | "644739c75681b2a2806c11bb" | 201         |
      | "Titulo de curso treinta" | "Este es mi tercer super curso"  | "28 dias " | "Ningun requerimiento" | "Contenido tres" | "644739c75681b2a2806c11bb" | 201         |


  @CP_021CrearCursoConCamposVacios
  Scenario Outline: crear cursos de aprendizaje con campos vacios en el campus de MatCafe
    Given  que el administrador desea crear un curso de aprendizaje con campos vacios
    When envia titulo <title>, descripcion <description>, duracion <duration>, requerimiento <requirements>, contenido  <content> y el adminID <adminID> incluyendo campos vacios
    Then se registrara el curso y  se mostrara estatus <estatusCode>

    Examples:
      | title                  | description                | duration | requirements           | content          | adminID                    | estatusCode |
      | ""                     | "Este es mi primer curso"  | "1"      | "Ningun requerimiento" | "Contenido uno"  | "644739c75681b2a2806c11bb" | 409         |
      | "Titulo de curso dos"  | ""                         | "2"      | "Ningun requerimiento" | "Contenido dos"  | "644739c75681b2a2806c11bb" | 409         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | ""       | "Ningun requerimiento" | "Contenido tres" | "644739c75681b2a2806c11bb" | 409         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "4"      | ""                     | "Contenido tres" | "644739c75681b2a2806c11bb" | 409         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "4"      | "Ningun requerimiento" | ""               | "644739c75681b2a2806c11bb" | 409         |


  @CP_022CrearCursoConUnIdDeAdministradorInexistenteScenario
  Scenario Outline:  crear cursos de aprendizaje con un id de admin inexistente
    Given un usuario que no es administrador desea crear un curso
    When envia titulo <title>, descricion <description>, duracion <duration>, requerimientos <requirements>, contenido <content> y el adminID incorrecto <adminID>
    Then se generara un mensaje de error junto con es estatus <estatusCode>

    Examples:
      | title                  | description                | duration | requirements           | content          | adminID  | estatusCode |
      | "Titulo de curso uno"  | "Este es mi primer curso"  | "1"      | "Ningun requerimiento" | "Contenido uno"  | "1234"   | 409         |
      | "Titulo de curso dos"  | "Este es mi segundo curso" | "2"      | "Ningun requerimiento" | "Contenido dos"  | "12345"  | 409         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "3"      | "Ningun requerimiento" | "Contenido tres" | "123456" | 409         |
