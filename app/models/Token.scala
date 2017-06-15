package models

import play.api.libs.json.Json


case class Token(square: Int)

object Token {
  implicit val tokenFormat = Json.format[Token]
}
