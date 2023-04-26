Feature: Obtener rutas de aprendizaje
  Yo como aprendiz del campus MatCafe
  Quiero ver las rutas asignadas y cursos asignados
  Para elegir entre las rutas asignadas

@CP029_ObtenerTodasLasRutas
  Scenario: obtener todas las rutas de aprendizaje
    Given que el aprendiz desea obtener la informacion de todas la rutas de aprendizaje
    When se envia la peticion para obtener todas las rutas
    Then se mostrara la informacion de todas las rutas de aprendizaje y un estatus 200
