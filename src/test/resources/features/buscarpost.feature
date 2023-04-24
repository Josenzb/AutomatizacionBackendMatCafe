Feature: Obtener posts
  AS usuario de la pagina de JsonPlaceHolder
  WANT Consultar y obtener posts
  BECAUSE Leer los post de la pagina

  @GetPosts
  Scenario Outline: Buscar posts por id
    Given El usuario se encuentra en la web de JsonPlaceHolder
    When El usuario envia una solicitud con el <id> del post deseado
    Then El usuario debe recibir un respuesta de status <code> y el post solicitado
    Examples:
      | id    | code |
      | "1"   | 200  |
      | "2"   | 200  |
      | "3"   | 200  |