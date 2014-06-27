package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

object JsonController extends Controller {
  def simpleJson = Action {
    val result = Map("status" -> "success")
    val json = Json.toJson(result)
    Ok(json)
  }

  def simpleJson2 = Action{
    val jsonStr = """{ "status": "success" }"""
    Ok(jsonStr).withHeaders(CONTENT_TYPE -> "application/json")
  }
}
