Feature: Inscribir aprendices a rutas de aprendizaje
  Yo como administrador de la web Campus
  Quiero inscribir aprendices a distintas rutas de aprendizaje
  Para gestionar el aprendizaje de los aprendices


  @InscribirAprendicesEnUnaRutaDeManeraExitosa
  Scenario Outline: Inscribir aprendices en una ruta de manera exitosa
    Given  el administrador desea inscribir un aprendiz a una ruta de aprendizaje
    When envia el reto con los datos de  id del aprendiz <learnedId> y  id del curso <courseId>
    Then se inscribe el aprendiz a la ruta y se muestra un esatatus <status>

    Examples:
      | learnedId                  | courseId                   | status |
      | "6447512d8cc268d0e07e4d40" | "64495081d5f5ed32cf12a55a" | 201    |
      | "644756609a1062742d432b9e" | "64495081d5f5ed32cf12a55a" | 201    |


  @InscribirConUnCursoQueNoExisteYUnAprendizQueNoExiste
  Scenario Outline: Inscribir aprendices que no estan registrados en rutas que no estan registradas
    Given  el administrador desea inscribir un aprendiz  que no esta registrado en una ruta de aprendizaje que no esta registrada
    When envia el reto con los datos de  id del aprendiz <learnedId> que no esta registrada y  id del de la ruta <rutaId> que no esta registrada
    Then  no se inscribe el aprendiz a la ruta y se muestra un estatus <status>

    Examples:
      | learnedId                  | rutaId                     | status |
      | "1"                        | "64495081d5f5ed32cf12a55a" | 409    |
      | "644756609a1062742d432b9e" | "1"                        | 409    |