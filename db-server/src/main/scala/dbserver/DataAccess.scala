package dbserver

import slick.jdbc.PostgresProfile.api._


trait DataAccess[U, Id] {
  def count: Int

  def list: List[U]

  def findById(id: Id): Option[U]

  def deleteById(id: Id): Boolean

  def update(e: U): Option[U]
}


class SlickDAO extends DataAccess[Subscriber, Long] {

  val sqldb = Database.forConfig("dummy_db.postgres")

  override def count: Int = ???

  override def list: List[Subscriber] = ???

  override def findById(id: Long): Option[Subscriber] = ???

  override def deleteById(id: Long): Boolean = ???

  override def update(e: Subscriber): Option[Subscriber] = ???
}

case class Subscriber(id: Long, name: String, city: String)