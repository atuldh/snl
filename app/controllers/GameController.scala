package controllers

import javax.inject._

import cats.data.{EitherT, OptionT}
import exceptions.UnexpectedState
import models.{Board, Player, Token, Win}
import play.api.mvc._
import reactivemongo.api.commands.WriteResult
import repository.SNLRepository

import scala.concurrent.{ExecutionContext, Future}
import cats.instances.all._
import play.api.libs.json.Json

@Singleton
class GameController @Inject()(val sNLRepository: SNLRepository)(implicit ec: ExecutionContext) extends Controller {

  def checkPlayerToken(playerId: String) = Action.async {
    OptionT(sNLRepository.getPlayerToken(playerId)).toRight(UnexpectedState("Problem occurred while retrieving Player's token")).fold(
      error => InternalServerError(error.errorMsg),
      player => Ok(Json.toJson(player.token))
    )
  }

  def movePlayerToken(playerId: String, spaces: Int) = Action.async {

    (for {
      currentPlayerToken <- OptionT(sNLRepository.getPlayerToken(playerId)).toRight(UnexpectedState("Problem occurred while retrieving Player's token"))
      _    <- EitherT.right[Future, UnexpectedState, WriteResult](sNLRepository.updatePlayerToken(Player(playerId, Token(currentPlayerToken.token.square + spaces))))
      newPlayerToken <- OptionT(sNLRepository.getPlayerToken(playerId)).toRight(UnexpectedState("Problem occurred while retrieving Player's token"))
    } yield newPlayerToken).fold(
      error => {
        InternalServerError(error.errorMsg)
      },
      newPlayerToken => Ok(Json.toJson(newPlayerToken.token))
    )
  }
}
