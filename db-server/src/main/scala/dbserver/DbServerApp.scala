package dbserver

import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.{Config, ConfigFactory}
import spray.json.DefaultJsonProtocol
import scala.concurrent.ExecutionContextExecutor

case class DummyResponse(text: String)
case class Table(name: String)

trait Protocols extends DefaultJsonProtocol {
  implicit val dummyFormat = jsonFormat1(DummyResponse)
  implicit val tableFormat = jsonFormat1(Table)
}

trait Service extends Protocols {
  implicit val system: ActorSystem

  implicit def executor: ExecutionContextExecutor

  implicit val materializer: Materializer

  def config: Config

  val logger: LoggingAdapter

  val routes = {
    logRequestResult("Query *") {
      pathPrefix("table_contents") {
        (post & entity(as[Table])) { tb =>
          complete {
            val sqldb = Database.forConfig("dummy_db.postgres")
            DummyResponse(s"Going fine.... Table: ${tb.name}")
          }
        }
      }
    }
  }
}

object DbServerApp extends App with Service {
  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()
  override val config = ConfigFactory.load()
  override val logger = Logging(system, getClass)

  Http().bindAndHandle(routes, config.getString("http.interface"), config.getInt("http.port"))
}
