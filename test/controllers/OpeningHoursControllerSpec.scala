package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.json.Json
import play.api.test.Helpers._
import play.api.test._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class OpeningHoursControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val controller = new GuiceApplicationBuilder()
    .build()
    .injector
    .instanceOf[OpeningHoursController]

  "OpeningHoursController POST" should {
    "return bad request for incorrect Json" in {
      val request = FakeRequest(POST, "/readJson").withJsonBody(Json.parse("""{ "monday": [] }"""))

      val result = controller.readJson().apply(request)
      status(result) mustEqual BAD_REQUEST
    }
    "return bad request for empty Json" in {
      val request = FakeRequest(POST, "/readJson").withJsonBody(Json.parse("{}"))

      val result = controller.readJson().apply(request)
      status(result) mustEqual BAD_REQUEST
    }
    "return ok" in {
      val request = FakeRequest(POST, "/readJson").withJsonBody(Json.parse(scala.io.Source.fromFile("openingHours.json").mkString))
      val result = controller.readJson().apply(request)
      status(result) mustEqual OK
    }
    "return bad request for incorrect Json using map" in {
      val request = FakeRequest(POST, "/readJson").withJsonBody(Json.parse("""{ "monday": [] }"""))

      val result = controller.readJsonMap().apply(request)
      status(result) mustEqual BAD_REQUEST
    }
    "return bad request for empty Json using map" in {
      val request = FakeRequest(POST, "/readJson").withJsonBody(Json.parse("{}"))

      val result = controller.readJsonMap().apply(request)
      status(result) mustEqual BAD_REQUEST
    }
    "return ok using map" in {
      val request = FakeRequest(POST, "/readJson").withJsonBody(Json.parse(scala.io.Source.fromFile("openingHours.json").mkString))
      val result = controller.readJsonMap().apply(request)
      status(result) mustEqual OK
    }
  }
}
