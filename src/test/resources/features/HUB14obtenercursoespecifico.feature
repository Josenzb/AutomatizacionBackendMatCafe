Feature:Obtener un curso especifico
  Yo como Aprendiz del campus MatCafe
  Quiero ver el curso
  Para acceder  al curso asignado

  @CP_ObtenerCursoEspecificoDeFormaExitosa
  Scenario Outline:  obtener curso con un id especifico de forma exitosa
    Given un aprendiz quiere obtener un curso por id
    When  envia el  id <CursoId> y drealiza la busqueda titulo <title>, descricion <description>, duracion <duration>, requerimientos <requirements>, contenido <content> y el adminID incorrecto <adminID>
    Then se obtendra el curso junto con un estatus <estatusCode>

    Examples:
      | title                  | description                | duration   | requirements           | content          | adminID                    | CursoId                     | estatusCode |
      | "Titulo de curso uno"  | "Este es mi primer curso"  | "28 dias " | "Ningun requerimiento" | "Contenido uno"  | "644739c75681b2a2806c11bb" | "/6447ee26e11f94e014380bf0" | 200         |
      | "Titulo de curso dos"  | "Este es mi segundo curso" | "28 dias " | "Ningun requerimiento" | "Contenido dos"  | "644739c75681b2a2806c11bb" | "/6447ee28e11f94e014380bf4" | 200         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "28 dias " | "Ningun requerimiento" | "Contenido tres" | "644739c75681b2a2806c11bb" | "/6447ee29e11f94e014380bf8" | 200         |


  @CP_ObtenerCursoEspecificoConUnIdQueNoExisteyEnBlanco
  Scenario Outline:  obtener curso con un id especifico que no existe
    Given un aprendiz quiere obtener un curso por un id que no existe
    When  envia  un id que no existe  <CursoId> y drealiza la busqueda titulo <title>, descricion <description>, duracion <duration>, requerimientos <requirements>, contenido <content> y el adminID incorrecto <adminID>
    Then se obtendra un mensaje de error  junto con un estatus <estatusCode>

    Examples:
      | title                  | description                | duration   | requirements           | content          | adminID                    | CursoId | estatusCode |
      | "Titulo de curso uno"  | "Este es mi primer curso"  | "28 dias " | "Ningun requerimiento" | "Contenido uno"  | "644739c75681b2a2806c11bb" | "1"     | 400         |
      | "Titulo de curso dos"  | "Este es mi segundo curso" | "28 dias " | "Ningun requerimiento" | "Contenido dos"  | "644739c75681b2a2806c11bb" | "2"     | 400         |
      | "Titulo de curso tres" | "Este es mi tercer  curso" | "28 dias " | "Ningun requerimiento" | "Contenido tres" | "644739c75681b2a2806c11bb" | ""      | 400         |
