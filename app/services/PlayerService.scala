package services

import javax.inject.{Inject, Singleton}

import models._
import play.api.Logger
import repository.SNLRepository
import play.api.cache._
import play.api.inject.ApplicationLifecycle

import scala.concurrent.ExecutionContext



trait PlayerService {
  def createPlayer(): Unit
}
@Singleton
class PlayerServiceImpl @Inject()(val appLifecycle: ApplicationLifecycle, val snlRepository: SNLRepository, val cache: CacheApi)(implicit ec:ExecutionContext) extends PlayerService {

  def createPlayer = {

    val squares: Map[Int, Chance] = List.range(1, 99).map ( key => key -> Safe).toMap
    //Add Winning Square
    squares + (100 -> Win)

    cache.set("board.key", Board(squares))
    Logger.debug("Board Created")

    snlRepository.createPlayerToken(Player("ONE", Token(1))).map(_ => Logger.debug("Player Created"))
  }


  appLifecycle.addStopHook { () =>
    snlRepository.clearDB("ONE").map(_ => Logger.debug("Player Deleted"))
  }
  createPlayer
}
