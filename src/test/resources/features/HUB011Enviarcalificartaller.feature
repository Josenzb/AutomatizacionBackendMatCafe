Feature: Entregar reto
  Yo como aprendiz del campus MatCafe
  Quiero entregar mi reto
  Para que pueda ser calificado

  @CalificarTallerDeManeraExitosa
  Scenario Outline: Entregar reto de manera exitosa
    Given  el aprendiz quiere enviar su reto para ser revisado
    When envia el reto con los datos de  id del aprendiz <learnedId>, id del curso <courseId>, el link del repositorio <github> y un comentario <coment>
    Then se envia el taller y se retorna un estatus <status>

    Examples:
      | learnedId                  | courseId                   | github                                                    | coment                                  | status |
      | "6447512d8cc268d0e07e4d40" | "644742df914f8e42f02eeebd" | "https://github.com/Josenzb/AutomatizacionBackendMatCafe" | "Esta es la entrega de mi primer reto"  | 201    |
      | "6447512d8cc268d0e07e4d40" | "644742df914f8e42f02eeebd" | "https://github.com/Josenzb/AutomatizacionBackendMatCafe" | "Esta es la entrega de mi segundo reto" | 201    |
      | "6447512d8cc268d0e07e4d40" | "644742df914f8e42f02eeebd" | "https://github.com/Josenzb/AutomatizacionBackendMatCafe" | "Esta es la entrega de mi tercer reto"  | 201    |

  @CalificarTallerConUnIdDeCursoYDeAprendizQueNoExiste
  Scenario Outline: Intento de entregar reto con un id de curso y de aprendiz que no existe
    Given  el aprendiz quiere enviar un reto sin un id indicado y sin un id de curso indicado
    When envia el reto con los datos de  id del aprendiz <learnedId>, id del curso <courseId>, incorrectos el link del repositorio <github> y un comentario <coment>
    Then  no se envia el taller y se retorna un estatus <status>

    Examples:

      | learnedId                  | courseId                   | github                                                    | coment                                 | status |
      | "1"                        | "644742df914f8e42f02eeebd" | "https://github.com/Josenzb/AutomatizacionBackendMatCafe" | "Este reto no debe entregarse"         | 409    |
      | "6447512d8cc268d0e07e4d40" | "2"                        | "https://github.com/Josenzb/AutomatizacionBackendMatCafe" | "Este reto no debe entregarse"         | 409    |
      | "6447512d8cc268d0e07e4d40" | "644742df914f8e42f02eeebd" | ""                                                        | "Esta es la entrega de mi tercer reto" | 409    |
      | "6447512d8cc268d0e07e4d40" | "644742df914f8e42f02eeebd" | "https://github.com/Josenzb/AutomatizacionBackendMatCafe" | ""                                     | 409    |


