package user

import faker._
import io.gatling.core.Predef._
import user.actions.{Show, Create}
import user.model.User
import scala.util.Random
import scala.concurrent.duration._

class PersonSimulation extends Simulation with GlobalHttpConf {
  val pauseBetweenPagination = 40 seconds
  val usersScenarios = scenario("Users")
    .exec(bulkInsertNTime(10))
    .exec(Show.listPaginated(1)).pause(pauseBetweenPagination)
    .exec(Show.listPaginated(2)).pause(pauseBetweenPagination)
    .exec(Show.listPaginated(3)).pause(pauseBetweenPagination)
    .exec(Show.listPaginated(4)).pause(pauseBetweenPagination)
    .exec(Show.listPaginated(5)).pause(pauseBetweenPagination)

    .exec(Show.listPaginatedWithDataExtraction(1)).pause(5 seconds)
    .foreach("${usernames}", "name") {
      exec(Show.findByName("${name}")).pause(pauseBetweenPagination)
    }

  setUp(
    usersScenarios.inject(rampUsers(200) over (2 minutes)).protocols(httpConfig())
  ).assertions(
    global.responseTime.max.lessThan(2000),
    global.successfulRequests.percent.greaterThan(99)
  )

  def bulkInsertNTime(n: Int) = repeat(n, "n") {
    exec(Create.bulk(listOfUsers(20))).pause(30 seconds, 60 seconds)
  }

  def listOfUsers(n: Int) = Seq.fill(n)(User(Internet.user_name(Name.name), 20 + Random.nextInt(70)))
}