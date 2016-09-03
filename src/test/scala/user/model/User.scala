package user.model

import io.gatling.core.Predef._
import play.api.libs.json.{Format, Json}

case class User(name: String, age: Int)

object User {
  implicit val fmt: Format[User] = Json.format[User]

  implicit def toStringBody(user: User) = StringBody(Json.toJson(user).toString())
}