package user.actions

import io.gatling.core.Predef._
import io.gatling.core.body.StringBody
import io.gatling.http.Predef._
import play.api.libs.json.Json
import user.PersonsEndPoints
import user.model.User

object Create {
  def single(user: User) = http("Users - create single")
    .post(PersonsEndPoints.createSingle)
    .header("Content-Type", "application/json")
    .body(user)

  def bulk(list: Seq[User]) = {
    val funnyConversion = StringBody(Json.toJson(list).toString())

    http("Users - create bulk")
      .post(PersonsEndPoints.createBulkPath)
      .header("Content-Type", "application/json")
      .body(funnyConversion)
  }
}