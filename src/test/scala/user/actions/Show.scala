package user.actions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import user.PersonsEndPoints

object Show {
  def listPaginated(page: Int) = http("Users - list paginated")
    .get(PersonsEndPoints.listPaginatedPath(1)).asJSON

  def listPaginatedWithDataExtraction(page: Int) = listPaginated(page)
    .check(status.is(200))
    .check(jsonPath("$[*].name").findAll.saveAs("usernames"))

  def findByName(name: String) = http("Users - find by name")
    .get(PersonsEndPoints.findByNamePath((name)))

  val count = http("Users - count").get(PersonsEndPoints.count)

  val clean = http("Users - clean").get(PersonsEndPoints.clean)

  val reset = http("Users - reset").get(PersonsEndPoints.reset)
}