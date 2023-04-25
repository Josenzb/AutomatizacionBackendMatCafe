Feature: Editar curso del campus MatCafe
  Yo como administrador del campus MatCafe
  Quiero editar  un curso del catalogo del campus
  Para tener la informacion correcta del curso en el campus

  @CP_0EditarCursoExitosamente
  Scenario Outline:  Editar cursos de aprendizaje de manera exitosa
    Given  que el administrador desea editar un curso de aprendizaje
    When envia titulo <title>, descripcion <description>, duracion <duration>, requerimiento <requirements>, contenido  <content> y el adminID <adminID>, con el id del curso a editar <cursoId>
    Then se editara el curso junto con un  estatus <estatusCode>

    Examples:
      | title            | description               | duration          | requirements           | content         | adminID                    | cursoId                     | estatusCode |
      | "Antiguo titulo" | "Este es mi primer curso" | "28 dias "        | "Ningun requerimiento" | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 200         |
      | "Nuevo titulo"   | "Este es mi primer curso" | "28 dias "        | "Ningun requerimiento" | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 200         |
      | "Nuevo titulo"   | "Nueva Descripcion"       | "28 dias "        | "Ningun requerimiento" | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 200         |
      | "Nuevo titulo"   | "Nueva Descripcion"       | "Nueva duracion " | "Ningun requerimiento" | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 200         |
      | "Nuevo titulo "  | "Nueva Descripcion"       | "Nueva duracion " | "Nuevo requerimiento"  | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 200         |


  @CP_0EditarCursoDejandoCamposVacios
  Scenario Outline:  Editar cursos de aprendizaje dejando campos vacios
    Given  que el administrador deja campos vacios cuando edita la informacion de un curso
    When envia  alguno de los siguientes campos vavios titulo <title>, descripcion <description>, duracion <duration>, requerimiento <requirements>, contenido  <content> y el adminID <adminID>, con el id del curso a editar <cursoId>
    Then se generara un mensaje de error junto con un  estatus <estatusCode>

    Examples:
      | title          | description               | duration          | requirements           | content         | adminID                    | cursoId                     | estatusCode |
      | ""             | "Este es mi primer curso" | "28 dias "        | "Ningun requerimiento" | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 400         |
      | "Nuevo titulo" | ""                        | "28 dias "        | "Ningun requerimiento" | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 400         |
      | "Nuevo titulo" | "Nueva Descripcion"       | " "               | "Ningun requerimiento" | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 400         |
      | "Nuevo titulo" | "Nueva Descripcion"       | "Nueva duracion " | ""                     | "Contenido uno" | "644739c75681b2a2806c11bb" | "/6447f9d1e11f94e014380c34" | 400         |
