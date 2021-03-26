package controllers

import models.{Days, Opening}
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import play.api.libs.json._
import play.api.mvc._

import javax.inject._


/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class OpeningHoursController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def readJson() = Action { implicit request =>
    val body = request.body
    //Parse the Json into Days object
    val days:Option[Days] = request.body.asJson.getOrElse(Json.obj()).asOpt[Days]
    days match {
      case Some(d) => {
        var daysString = formatDays(d)
        println(daysString)
        Ok(views.html.hours(daysString))
      }
      case None => BadRequest(views.html.hours("No data to display"))
    }
  }

  def formatDays(days: Days) = {
    var daysString: String = ""
    var daysList: List[List[Opening]] = List(days.monday, days.tuesday, days.wednesday, days.thursday, days.friday, days.saturday, days.sunday)

    daysString = "Monday: " + getOpeningForDay(days.monday, days.tuesday) + "\n" +
      "Tuesday: " + getOpeningForDay(days.tuesday, days.wednesday) + "\n" +
      "Wednesday: " + getOpeningForDay(days.wednesday, days.thursday) + "\n" +
      "Thursday: " + getOpeningForDay(days.thursday, days.friday) + "\n" +
      "Friday: " + getOpeningForDay(days.friday, days.saturday) + "\n" +
      "Saturday: " + getOpeningForDay(days.saturday, days.sunday) + "\n" +
      "Sunday: " + getOpeningForDay(days.sunday, days.monday) + "\n"
    daysString
  }

  def getOpeningForDay(day: List[Opening], nextDay: List[Opening]): String = {
    var openingHours: String = ""
    val it = day.iterator
    //Iterate over the opening list and check the match between open and close
    while (it.hasNext) {
      val op: Opening = it.next()
      var time: String = DateTimeFormat.forPattern("hh:mm aa").withZone(DateTimeZone.UTC).print(op.value*1000L)
      if (op.`type` == "open") {
        openingHours += (if (!openingHours.isEmpty) ", " else "") + time + " - "
        if (!it.hasNext) {
          openingHours += DateTimeFormat.forPattern("hh:mm aa").withZone(DateTimeZone.UTC).print(nextDay(0).value*1000L)
        }
      }
      if (op.`type` == "close" && !openingHours.isEmpty)
        openingHours += time
    }
    if (openingHours.isEmpty) "Closed" else openingHours
  }
}
