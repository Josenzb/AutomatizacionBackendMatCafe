Feature:Eliminar curso del catalogo del campus
  Yo como administrador del campus MatCafe
  Quiero eliminar un curso del catalogo del campus
  Para eliminar cursos obsoletos del campus

  @CP_EliminarCursoExitosamente
  Scenario Outline: Eliminar curso de manera exitosa
    Given  que el administrador desea eliminar un curso
    When envia la solicitud  con el  id del curso que desea eliminar <idCurso>
    Then se obtiene un estatus de respuesta <status>

    Examples:
      | idCurso                     | status |
      | "/64482b1be11f94e014380d9c" | 200    |


  @CP_EliminarCursoConUnIdQueNoExiste
  Scenario Outline: Eliminar curso con un Id que no existe
    Given  que el administrador desea eliminar un curso que no existe
    When envia la solicitud  con el  id  que no existe  <idCurso>
    Then  no se borrarara ningun curso y se obtiene un estatus de respuesta <status>

    Examples:
      | idCurso   | status |
      | "/12345"  | 400    |
      | "/ABCD"   | 400    |
      | "/ABC123" | 400    |
      | "/abc123" | 400    |
      | "/123abc" | 400    |
      | "/."      | 400    |
      | "/*"      | 400    |
      | "//"      | 400    |
      | "/<"      | 400    |
      | "/>"      | 400    |
      | "/#"      | 400    |
      | "/''"     | 400    |
      | "/()"     | 400    |
      | "/[]"     | 400    |

  @CP_EliminarUnCursoQueSeEncuentraEnUnaRutaDeAprendizaje