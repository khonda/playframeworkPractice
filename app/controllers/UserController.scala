package controllers
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._

object UserController extends Controller {
  /** Form定義 */
  val userForm = Form(
    mapping(
      "name" -> text,
      "email" -> text,
      "age" -> number)(User.apply)(User.unapply))

  /** 初期画面関数 */
  def entryInit = Action {
    val filledForm = userForm.fill(User("user name", "email address", 30))
    Ok(views.html.user.entry(filledForm))
  }
  /** ユーザー登録関数 */
  def entrySubmit = Action { implicit request =>
    val user = userForm.bindFromRequest.get
    println(user)
    Ok(views.html.user.entrySubmit())
  }
}
