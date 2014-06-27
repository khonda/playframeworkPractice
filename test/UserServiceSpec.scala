import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import play.api._
import play.api.mvc._
import play.api.db._
import play.api.Play.current
import anorm._
import services.UserService
import org.specs2.specification.{ BeforeExample, AfterExample, Scope }
//1.クラス定義
class UserServieSpec extends Specification with BeforeExample with AfterExample {
  //2.FakeApplication作成
  def fakeApp = FakeApplication(
    additionalConfiguration = Map(
      "db.default.driver" -> "com.mysql.jdbc.Driver",
      "db.default.url" -> "jdbc:mysql://localhost/playpractice",
      "db.default.user" -> "play",
      "db.default.password" -> "play",
      "db.default.partitionCount" -> 2,
      "db.default.maxConnectionsPerPartition" -> 5,
      "db.default.minConnectionsPerPartition" -> 5))
  def before = {
    //テスト用ユーザーを登録
    running(fakeApp) {
      DB.withConnection { implicit c =>
        Logger.info("before!")
        println("before!")
        SQL(
          """ insert into User(id,name,email,password) values({id},{name},{email},{password})
              """).on('id -> 1, 'name -> "taro", 'email -> "taro@taro.com", 'password -> "taropass").executeInsert()
      }
    }
  }
  def after = {
    //テーブルのデータを削除
    running(fakeApp) {
      DB.withConnection { implicit c =>
        SQL(""" delete from Post """).executeUpdate()
        SQL(""" delete from User """).executeUpdate()
        println("after!")
        Logger.info("after!")
      }
    }
  }
  //3.テスト定義
  "UserService" should {
    "insert user info" in {
      running(fakeApp) {
        UserService.entry("test", "test@test.com", "testpass") match {
          case Some(id) => ok("ok")
          case None => failure("entry failure")
        }
      }
    }
  }
  "UserService" should {
    "get user by id" in {
      running(fakeApp) {
        val user = UserService.findByPk(1)
        user should not be (None)
        user.get.name must equalTo("taro")
      }
    }
  }
}