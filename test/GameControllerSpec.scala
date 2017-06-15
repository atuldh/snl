import controllers.GameController
import models.{Player, Token}
import org.scalatestplus.play.{OneAppPerSuite, PlaySpec}
import org.scalatest.mockito.MockitoSugar
import play.api.http.Status
import play.api.test.FakeRequest
import repository.SNLRepository

import scala.concurrent.ExecutionContext.Implicits.global
import org.mockito.Mockito.when
import org.mockito.Matchers._

import scala.concurrent.Future
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import reactivemongo.api.commands.WriteResult

import scala.concurrent.Future


class GameControllerSpec extends PlaySpec with OneAppPerSuite with MockitoSugar with Results {

  val sNLRepository = mock[SNLRepository]
  val controller = new GameController(sNLRepository)
  val player = mock[Player]
  val token = mock[Token]
  val writeResult = mock[WriteResult]

  "GET /snl/checkPlayerToken" should {
    "return json as {\"square\":1}" in {
      when(sNLRepository.getPlayerToken(anyString())) thenReturn Future.successful(Some(Player("ONE", Token(1))))
      val result = controller.checkPlayerToken("ONE").apply(FakeRequest("GET", "/snl/checkPlayerToken"))
      val bodyText: String = contentAsString(result)
      bodyText mustBe "{\"square\":1}"
    }
  }


}
