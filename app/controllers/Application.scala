package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.tasks)
      }
    )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }

  def sample1(id: Long)=TODO
  def sample2(id: Long) = Action {
    Ok(views.html.sample(id))
  }
  def sample3(id: Long) = Action {
    Ok(views.html.sample(id))
  }
  def sample4(id: Option[Int]) = Action {
    val res = id match{
      case Some(x) => x
      case None => 99
    }
    Ok(views.html.sample(res))
  }
  val taskForm = Form(
    "label" -> nonEmptyText
  )
}

