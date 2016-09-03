package user.actions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import user.PersonsEndPoints
import user.model.User

object Create {
  def single(user: User) = http("Users - create single")
    .post(PersonsEndPoints.createSingle)
    .header("Content-Type", "application/json")
    .body(user)

  def bulk(list: List[User]) = http("Users - create bulk")
    .post(PersonsEndPoints.createBulkPath)
}