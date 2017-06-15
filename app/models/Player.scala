package models

import play.api.libs.json.Json


case class Player(id: String, token: Token)

object Player {
  implicit val playerFormat = Json.format[Player]
}
