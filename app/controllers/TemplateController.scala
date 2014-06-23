package controllers

import play.api._
import play.api.mvc._

object TemplateController extends Controller {
  def show = Action {
    val list = List[String]("lemon","mikan","nanao")
    Ok(views.html.show("Hello Scala Templates!",list))
  }
}
