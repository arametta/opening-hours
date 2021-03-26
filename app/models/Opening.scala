package models

import play.api.libs.json.Json

case class Opening(`type`: String, value:Int)

object Opening {
  implicit val op = Json.format[Opening]
}
