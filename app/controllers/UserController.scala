package controllers
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models._
import services.UserService

object UserController extends Controller {
  /** Form定義 */
  val userForm = Form(
    tuple(
      "name" -> nonEmptyText,
      "email" -> email,
      "password" -> nonEmptyText))

  /** 初期画面関数 */
  def entryInit = Action { implicit request =>
    val filledForm = userForm.fill("user name", "email address", "password")
    Ok(views.html.user.entry(flash.get("result").getOrElse(""), filledForm))
  }
  /** ユーザー登録関数 */
  def entrySubmit = Action { implicit request =>
    userForm.bindFromRequest.fold(
      errors => {
        BadRequest(views.html.user.entry("error", errors))
      },
      success => {
        val (name, email, password) = success
        UserService.entry(name,email,password) match {
          case Some(id) => {
            UserService.findByPk(id) match {
              case Some(u) => Ok(views.html.user.entrySubmit(u))
              case None => Redirect("/user/entry").flashing("result" -> "user not found")
            }
          }
          case None => Redirect("/user/entry").flashing("result" -> "entry failure")
        }
      })
  }
}
