package dbserver

import akka.event.NoLogging
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest._

class DbServerSpec extends FlatSpec with Matchers with ScalatestRouteTest with Service {
  override def testConfigSource = "akka.loglevel = WARNING"

  override def config = testConfig

  override val logger = NoLogging
  "The service" should "return a greeting for GET requests to the root path" in {
    Post(s"/table_contents", Table("test")) ~> routes ~> check {
      status shouldBe OK
      contentType shouldBe `application/json`
      responseAs[DummyResponse] shouldBe DummyResponse("Going fine.... Table: test")
    }
  }
}
