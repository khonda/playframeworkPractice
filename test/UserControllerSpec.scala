import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
class UserControllerSpec extends Specification {
  "respond to the entryInit Action" in {
    running(FakeApplication()) {
      //ルーティング情報を使う
      val Some(result) = route(FakeRequest(GET, "/user/entry"))
      //コントローラを直接使う
      //val result = controllers.UserController.entryInit()(FakeRequest())
      status(result) must equalTo(OK)
      contentType(result) must beSome("text/html")
      charset(result) must beSome("utf-8")
      contentAsString(result) must contain("Entry user")
    }
  }
}