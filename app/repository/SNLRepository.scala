package repository

import javax.inject.{Inject, Singleton}

import models.Player
import play.api.libs.json.Json
import play.modules.reactivemongo.ReactiveMongoApi
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json.collection.JSONCollection
import models.Player.playerFormat
import reactivemongo.play.json._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SNLRepository @Inject()(val mongo: ReactiveMongoApi)(implicit ec:ExecutionContext) {

  val repository: Future[JSONCollection] =
    mongo.database.map(_.collection[JSONCollection]("SNL"))


  def createPlayerToken(p:Player) : Future[WriteResult] = {
    repository.flatMap(_.insert(p))
  }

  def updatePlayerToken(p:Player) : Future[WriteResult] = {
    val criteria = Json.obj("id" -> p.id)
    repository.flatMap(_.update(criteria, p))
  }


  def getPlayerToken(playerId: String): Future[Option[Player]] = {
    val criteria = Json.obj("id" -> playerId)
    repository.flatMap(_.find(criteria).one[Player])
  }

  def clearDB(playerId: String): Future[WriteResult] = {
    val criteria = Json.obj("id" -> playerId)
    repository.flatMap(_.remove(criteria))
  }
}
