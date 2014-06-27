package controllers

import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.cache.Cache

import java.util._

object CacheController extends Controller {

  def setCache =Action{ implicit request =>
    val now = new Date()
    val data = now.toString()
    val until = now.getTime() + 10000
    val until2 = new Date(until).toString()

    Cache.set("Date", data, 10)
    Cache.set("Until", until2, 15)

    Logger.info(data)
    Logger.info(until.toString())
    Logger.info(until2)
    Redirect(routes.CacheController.getCache)
  }

  def getCache = Action { implicit request =>
    val now = new Date()
    Cache.getAs[String]("Date").map{ date =>
      Ok(views.html.cache(date, Cache.getAs[String]("Until").getOrElse("None"), now.toString))
    }.getOrElse{
      Ok(views.html.cache("None","None",now.toString))
    }
  }
}
