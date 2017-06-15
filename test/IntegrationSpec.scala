import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.libs.ws._
import play.test.WithServer
import play.api.Play.current
import play.api.libs.json.Json
import scala.concurrent.duration._
/**
 * Add your integration spec here.
 * An integration test will fire up a whole play application in a real (or headless) browser.
 */
class IntegrationSpec extends PlaySpec with OneServerPerTest with OneBrowserPerTest with HtmlUnitFactory {


  "Given the game is started & When the token is placed on the board" should {
    "Then the token is on square 1" in new WithServer {
      val response = await(WS.url("http://localhost:9000/snl/checkPlayerToken/ONE").get())(5 second)

      response.status mustBe OK //2
      response.json equals Json.parse("{\"square\":1}")
    }
  }

  "Given the token is on square 1 & When the token is moved 3 spaces" should {
    "Then the token is on square 4" in new WithServer {
      val response = await(WS.url("http://localhost:9000/snl/movePlayerToken/ONE/3").put[String](""))(5 second)

      response.status mustBe OK
      response.json mustBe Json.parse("{\"square\":4}")
    }
  }

  "Given the token is on square 1 & When the token is moved 3 spaces " should {
    "And then it is moved 4 spaces, Then the token is on square 8" in new WithServer {
      val response1 = await(WS.url("http://localhost:9000/snl/movePlayerToken/ONE/3").put[String](""))(5 second)
      val response2 = await(WS.url("http://localhost:9000/snl/movePlayerToken/ONE/4").put[String](""))(5 second)


      response2.status mustBe OK
      response2.json mustBe Json.parse("{\"square\":8}")
    }
  }

  "Given the token is on square 97 & When the token is moved 3 spaces " should {
    "Then the token is on square 100, And the player has won the game" in new WithServer {
      val response1 = await(WS.url("http://localhost:9000/snl/movePlayerToken/ONE/96").put[String](""))(5 second)
      val response2 = await(WS.url("http://localhost:9000/snl/movePlayerToken/ONE/3").put[String](""))(5 second)


      response2.status mustBe OK
      response2.json mustBe Json.parse("{\"square\":100}")
    }
  }

}
