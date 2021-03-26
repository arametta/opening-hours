package models

import play.api.libs.json._

case class Days(monday: List[Opening],
                tuesday: List[Opening],
                wednesday: List[Opening],
                thursday: List[Opening],
                friday: List[Opening],
                saturday: List[Opening],
                sunday: List[Opening])

object Days {
  implicit val da = Json.format[Days]
}