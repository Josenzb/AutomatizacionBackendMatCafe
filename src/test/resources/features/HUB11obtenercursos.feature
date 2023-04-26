Feature:  Obtener todos los cursos disponibles
Yo como Aprendiz del campus MatCafe
Quiero ver las rutas asignadas y cursos asignados
Para Elegir entre los cursos asignados

  @CP_ObtenerTodosLosCursosDisponibles
  Scenario: Obtener cursos disponibles
    Given  que el aprendiz desea obtener todos los cursos
    When envia la solicitud de obtener todos los cursos
    Then se obtiene todos los cursos disponibles  en el campus
