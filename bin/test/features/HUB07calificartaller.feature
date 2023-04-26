Feature: Calificar taller
  AS administrador del campus MatCafe
  WANT calificar y comentar el reto entregado
  BECAUSE medir el conocimiento del aprendiz

  @Calificacion
  Scenario Outline: Calificar reto exitoso
    Given que el administrador realiza una peticion de calificacion de reto
    When el administrador envia la peticion con la <calificacion> el comentario <comentario> el idcurso <courseid> y la idaprendiz <idlearner>
    Then se calificara correctamente
    Examples:
      | calificacion | comentario     | courseid                   | idlearner                  |
      | 6            | "debe mejorar" | "644739d05681b2a2806c11bf" | "644756609a1062742d432b9e" |

  @Calificacion
  Scenario Outline: Calificar reto con valores limite
    Given que el administrador realiza una peticion de calificacion de reto con valores limite
    When el administrador envia la peticion con al limite <calificacion> el comentario <comentario> el idcurso <courseid> y la idaprendiz <idlearner>
    Then se calificara correctamente y se generara un <status>
    Examples:
      | calificacion | comentario     | courseid                   | idlearner                  | status |
      | 0            | "debe mejorar" | "644739d05681b2a2806c11bf" | "644756609a1062742d432b9e" | 201    |
      | 12           | "debe mejorar" | "644739d05681b2a2806c11bf" | "644756609a1062742d432b9e" | 201    |
      | -1           | "debe mejorar" | "644739d05681b2a2806c11bf" | "644756609a1062742d432b9e" | 400    |
      | 13           | "debe mejorar" | "644739d05681b2a2806c11bf" | "644756609a1062742d432b9e" | 400    |

  @Calificacion
  Scenario Outline: Calificar reto con id de curso incorrecta
    Given que el administrador realiza una peticion de calificacion de reto con una id de curso erronea
    When el administrador envia la peticion con la <calificacion> el comentario <comentario> el idcurso erronea <courseid> y la idaprendiz <idlearner>
    Then no se calificara y se generara un mensaje de error junto a un <status>
    Examples:
      | calificacion | comentario     | courseid                   | idlearner                  | status |
      | 6            | "debe mejorar" | "644739d05681b2a2806c11bf" | "644756609a1062742d432b9e" | 400    |