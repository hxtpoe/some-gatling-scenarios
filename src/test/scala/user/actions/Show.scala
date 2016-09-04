package user.actions

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import user.PersonsEndPoints

object Show {
  def listPaginated(page: Int) = http("list paginated")
    .get(PersonsEndPoints.listPaginatedPath(page)).asJSON

  def listPaginatedWithDataExtraction(page: Int) = listPaginated(page)
    .check(status.is(200))
    .check(jsonPath("$[*].name").findAll.saveAs("usernames"))

  def findByName(name: String) = http("find by name")
    .get(PersonsEndPoints.findByNamePath((name)))

  val count = http("count").get(PersonsEndPoints.count)

  val clean = http("clean").get(PersonsEndPoints.clean)

  val reset = http("reset").get(PersonsEndPoints.reset)
}