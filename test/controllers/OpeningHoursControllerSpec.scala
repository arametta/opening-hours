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
  }
/*
  "OpeningHoursController GET" should {

    "render the index page from a new instance of controller" in {
      val controller = new OpeningHoursController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    "render the index page from the application" in {
      val controller = inject[OpeningHoursController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("Welcome to Play")
    }
  }
 */
}